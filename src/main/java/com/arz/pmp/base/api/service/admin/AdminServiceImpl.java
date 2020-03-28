package com.arz.pmp.base.api.service.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arz.pmp.base.api.bo.adminn.AdminEditorReq;
import com.arz.pmp.base.api.bo.adminn.AdminLoginResp;
import com.arz.pmp.base.api.bo.adminn.AdminSearchReq;
import com.arz.pmp.base.api.service.permission.PermissionService;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.constants.Constants;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.commons.utils.EncryptUtils;
import com.arz.pmp.base.framework.commons.utils.NumberUtil;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import com.arz.pmp.base.mapper.PmpAdminEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpAdminExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ma.glasnost.orika.MapperFacade;

/**
 * description admin
 *
 * @author chen wei
 * @date 2019/11/12
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PmpAdminEntityMapper pmpAdminEntityMapper;
    @Autowired
    private PmpAdminExMapper pmpAdminExMapper;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MapperFacade mapperFacade;
    @Value("${service.project.login.fail.max}")
    private int loginFailMax;

    @Override
    public AdminLoginResp goLogin(String userName, String password) {
        // 验证登录信息
        PmpAdminEntity user = validUserLogin(userName, password);
        // 获取角色权限
        Long roleId = user.getRoleId();
        AdminLoginResp resp = new AdminLoginResp();
        resp.setUserInfo(user);
        if (roleId == null) {
            // 登录信息缓存
            String authentication = cacheRedisAdmin(resp);
            resp.setAuthentication(authentication);
            return resp;
        }
        PmpRoleEntity roleEntity = permissionService.getRoleById(roleId);
        resp.setRoleInfo(roleEntity);
        // 超级管理员
        List<PmpPermissionEntity> perms = permissionService.getPermListByRoleId(roleId);
        resp.setPermissionList(perms);
        // 登录信息缓存
        String authentication = cacheRedisAdmin(resp);
        resp.setAuthentication(authentication);
        return resp;
    }

    @Override
    public void goLogOut(String authentication) {

        redisService.delOperator(authentication);
    }

    @Override
    public PageInfo<List<AdminLoginResp>> getAdminListPage(RestRequest<AdminSearchReq> data) {
        RequestHeader requestHeader = data.getHeader();
        AdminSearchReq search = data.getBody();
        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
            .doSelectPage(() -> {
                pmpAdminExMapper.selectAdminInfoList(search);
            }).toPageInfo();
        return pageInfo;
    }

    @Override
    public Long addOrUpAdmin(AdminEditorReq data, boolean addOn, String authentication) {
        String userName = data.getUserName();
        PmpAdminEntity user = pmpAdminExMapper.selectAdminByName(userName);
        Long adminId = data.getAdminId();
        boolean flag = user == null || (!addOn && user.getAdminId().equals(adminId));
        Assert.isTrue(flag, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
        Assert.isTrue(((adminId != null && adminId != Constants.ADMIN_DEFAULT_ID) || adminId == null)
            && data.getRoleId() != Constants.ADMIN_DEFAULT_ID, CommonCodeEnum.PARAM_ERROR_ADMIN);
        PmpAdminEntity pmpAdminEntity = mapperFacade.map(data, PmpAdminEntity.class);
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);
        // 密码设置
        String password = data.getPassword();
        // 默认密码
        if (StringUtils.isBlank(password) && addOn) {
            password = Constants.ADMIN_DEFAULT_PASSWORD;
        }
        // 加密处理
        if (StringUtils.isNotBlank(password)) {
            String salt = EncryptUtils.createSalt();
            String encryptPaw = EncryptUtils.createSysUserPsw(password, salt);
            pmpAdminEntity.setSalt(salt);
            pmpAdminEntity.setPassword(encryptPaw);
        }

        // 新增
        if (addOn) {
            pmpAdminEntity.setCreateTime(curTimeSec);
            pmpAdminEntity.setCreateManager(operatorId);
            pmpAdminEntity.setDelOn(false);
            pmpAdminEntityMapper.insert(pmpAdminEntity);
            adminId = pmpAdminEntity.getAdminId();

        } else {
            pmpAdminEntity.setUpdateTime(curTimeSec);
            pmpAdminEntity.setUpdateManager(operatorId);
            pmpAdminEntityMapper.updateByPrimaryKeySelective(pmpAdminEntity);
        }

        return adminId;
    }

    @Override
    public void deleteAdmin(Long adminId, String authentication) {
        Assert.isTrue(NumberUtil.isPositive(adminId), CommonCodeEnum.PARAM_ERROR);
        Assert.isTrue(adminId != Constants.ADMIN_DEFAULT_ID, CommonCodeEnum.PARAM_ERROR_ADMIN);
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);
        PmpAdminEntity pmpAdminEntity = new PmpAdminEntity();
        pmpAdminEntity.setAdminId(adminId);
        pmpAdminEntity.setUpdateManager(operatorId);
        pmpAdminEntity.setUpdateTime(DateUtil.getCurSecond());
        pmpAdminEntity.setDelOn(true);
        pmpAdminEntityMapper.updateByPrimaryKeySelective(pmpAdminEntity);
    }

    @Override
    public List<PmpAdminEntity> getAdminList(AdminSearchReq data, String authentication) {
        PmpAdminEntity adminEntity = redisService.getOperatorByToken(authentication);
        if (adminEntity != null && adminEntity.getRoleId().equals(data.getRoleId())) {
            List<PmpAdminEntity> list = new ArrayList();
            PmpAdminEntity admin = new PmpAdminEntity();
            admin.setAdminId(adminEntity.getAdminId());
            admin.setNickname(adminEntity.getNickname());
            admin.setAvatar(adminEntity.getAvatar());
            admin.setUserName(adminEntity.getUserName());
            list.add(adminEntity);
            return list;
        }
        return pmpAdminExMapper.selectAdminList(data);
    }

    @Override
    public Long getRoleAdminId(String authentication, SysPermEnumClass.RoleEnum roleEnum) {
        // 判断当前管理员角色
        PmpAdminEntity adminEntity = redisService.getOperatorByToken(authentication);
        if (adminEntity == null) {
            return null;
        }
        PmpRoleEntity roleEntity = permissionService.getRoleById(adminEntity.getRoleId());
        if (roleEntity != null && roleEnum.getCode().equalsIgnoreCase(roleEntity.getRoleCode())) {
            return adminEntity.getAdminId();
        }
        return null;
    }

    @Override
    public void updatePassword(String newPassword, String authentication) {
        Assert.isTrue(StringUtils.isNotBlank(newPassword), CommonCodeEnum.PARAM_ERROR);
        Long adminId = redisService.getOperatorIdByToken(authentication);
        PmpAdminEntity adminEntity = new PmpAdminEntity();
        String salt = EncryptUtils.createSalt();
        String encryptPaw = EncryptUtils.createSysUserPsw(newPassword, salt);
        adminEntity.setSalt(salt);
        adminEntity.setPassword(encryptPaw);
        adminEntity.setUpdateManager(adminId);
        adminEntity.setUpdateTime(DateUtil.getCurSecond());
        adminEntity.setAdminId(adminId);
        pmpAdminEntityMapper.updateByPrimaryKeySelective(adminEntity);
    }

    private String cacheRedisAdmin(AdminLoginResp resp) {
        // 缓存token
        PmpAdminEntity user = resp.getUserInfo();
        if (user == null) {
            return null;
        }
        String token = createAuthToken();
        // 缓存凭证
        redisService.setOperator(token, user);
        // 单点登录凭证缓存
        redisService.setAdminUserUniqueLogin(user.getUserName(), token);

        return token;
    }

    private String createAuthToken() {

        return UUID.randomUUID().toString();
    }

    /**
     * description 验证登录信息
     *
     * @return com.arz.pmp.base.entity.PmpAdminEntity
     * @author chen wei
     * @date 2019/11/12
     */
    public PmpAdminEntity validUserLogin(String userName, String password) {
        // 登录错误处理
        int failNum = validLoginRefuse(userName);
        // 验证密码
        PmpAdminEntity user = pmpAdminExMapper.selectAdminByName(userName);
        Assert.isTrue(user != null, CommonCodeEnum.PARAM_ERROR_LOGIN_USERNAME);
        if (!EncryptUtils.doCredentialsMatch(password, user.getSalt(), user.getPassword())) {
            // 更新登录失败统计
            redisService.updateLoginFailNum(userName, failNum + 1, true);
            Assert.isTrue(false, CommonCodeEnum.PARAM_ERROR_LOGIN_PASSWORD);
        }
        // 登录成功清除错误次数统计
        if (failNum > 0) {
            redisService.delLoginFailNum(userName, true);
        }
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    /**
     * description 校验用户名是否被禁止登录
     * 
     * @param userName
     * @author chen wei
     * @date 2020/3/28
     */
    private int validLoginRefuse(String userName) {

        int failNum = redisService.getLoginFailNum(userName, true);
        Assert.isTrue(failNum < loginFailMax, CommonCodeEnum.PERMISSION_ERROR_LOGIN_REFUSE);
        return failNum;
    }

}
