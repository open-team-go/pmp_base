package com.arz.pmp.base.api.service.admin;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public AdminLoginResp goLogin(String userName, String password) {
        // 验证登录信息
        PmpAdminEntity user = validUser(userName, password);
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
            String encryptPaw = createSysUserPsw(password, salt);
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
    public List<PmpAdminEntity> getAdminList(AdminSearchReq data) {

        return pmpAdminExMapper.selectAdminList(data);
    }

    private String cacheRedisAdmin(AdminLoginResp resp) {

        // 缓存token
        PmpAdminEntity user = resp.getUserInfo();
        if (user == null) {
            return null;
        }
        String token = createAuthToken();

        redisService.setOperator(token, user);

        return token;
    }

    private String createAuthToken() {

        return UUID.randomUUID().toString();
    }

    /**
     * description 验证登录信息
     * 
     * @author chen wei
     * @date 2019/11/12
     * @return com.arz.pmp.base.entity.PmpAdminEntity
     */
    public PmpAdminEntity validUser(String userName, String password) {
        PmpAdminEntity user = pmpAdminExMapper.selectAdminByName(userName);

        Assert.isTrue(user != null, CommonCodeEnum.PARAM_ERROR_LOGIN_USERNAME);
        // 验证密码
        Assert.isTrue(doCredentialsMatch(password, user.getSalt(), user.getPassword()),
            CommonCodeEnum.PARAM_ERROR_LOGIN_PASSWORD);
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    private boolean doCredentialsMatch(String password, String salt, String targetPsw) {

        if (StringUtils.isBlank(password) || StringUtils.isBlank(targetPsw)) {
            return false;
        }
        if (StringUtils.isBlank(salt)) {

            password = createSysUserPsw(password);
        } else {

            password = createSysUserPsw(password, salt);
        }

        return password.equals(targetPsw);
    }

    /**
     * description 获取hash散列值
     *
     * @param password
     * @return java.lang.String
     * @author chen wei
     * @date 2019/7/11
     */
    private String createSysUserPsw(String password) {

        return EncryptUtils.md5(password);
    }

    /**
     * description 加盐获取hash散列值
     *
     * @param password
     * @param salt
     * @return java.lang.String
     * @author chen wei
     * @date 2019/7/11
     */
    private String createSysUserPsw(String password, String salt) {

        return EncryptUtils.md5Salt(password, salt);
    }

}
