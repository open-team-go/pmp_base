package com.arz.pmp.base.api.service.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arz.pmp.base.api.bo.excel.UserDataExport;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.api.bo.excel.UserImportResp;
import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserEditorReq;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.service.admin.AdminService;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpUserEducationEntity;
import com.arz.pmp.base.entity.PmpUserEntity;
import com.arz.pmp.base.entity.PmpUserPayTypeEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import com.arz.pmp.base.mapper.PmpUserEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpUserExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ma.glasnost.orika.MapperFacade;

/**
 * description 学员业务实现
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private PmpUserExMapper pmpUserExMapper;
    @Autowired
    private PmpUserEntityMapper pmpUserEntityMapper;
    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AdminService adminService;

    @Override
    public PageInfo<List<UserDataResp>> getUserListPage(RestRequest<UserSearchReq> req) {

        RequestHeader requestHeader = req.getHeader();
        UserSearchReq search = req.getBody();

        // 判斷是否是教务人员
        search.setEducationAdminId(
            adminService.getRoleAdminId(req.getHeader().getAuthentication(), SysPermEnumClass.RoleEnum.EDUCATION));

        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
            .doSelectPage(() -> {
                pmpUserExMapper.selectUserList(search);
            }).toPageInfo();
        return pageInfo;

    }

    @Override
    public Long addOrUpUser(UserEditorReq data, boolean addOn, String authentication) {

        String name = data.getUserName();
        Long userId = data.getUserId();
        String phoneNo = data.getPhoneNo();
        if (StringUtils.isNotBlank(name)) {

            Long id = pmpUserExMapper.selectUserByName(name, phoneNo);
            boolean flag = id == null || (!addOn && id.equals(userId));
            Assert.isTrue(flag, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI, "已存在同名同手机号学员信息");
        }
        PmpUserEntity entity = mapperFacade.map(data, PmpUserEntity.class);
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);

        // 新增
        if (addOn) {
            Assert.isTrue(StringUtils.isNotBlank(name), CommonCodeEnum.PARAM_ERROR);
            entity.setCreateTime(curTimeSec);
            entity.setCreateManager(operatorId);
            entity.setDelOn(false);
            pmpUserEntityMapper.insert(entity);
            userId = entity.getUserId();

        } else {
            entity.setUpdateTime(curTimeSec);
            entity.setUpdateManager(operatorId);
            pmpUserEntityMapper.updateByPrimaryKeySelective(entity);
        }

        return userId;
    }

    @Override
    public void deleteUser(Long userId, String authentication) {
        PmpUserEntity entity = new PmpUserEntity();
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);
        entity.setUserId(userId);
        entity.setUpdateTime(curTimeSec);
        entity.setUpdateManager(operatorId);
        entity.setDelOn(true);
        pmpUserEntityMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<PmpUserEducationEntity> getEducationList() {
        return pmpUserExMapper.selectEducationList();
    }

    @Override
    public List<PmpUserPayTypeEntity> getPayTypeList() {
        return pmpUserExMapper.selectUserPayTypeList();
    }

    @Override
    public List<UserDataExport> getExportUserList(UserSearchReq search, String authentication) {
        // 判斷是否是教务人员
        search.setEducationAdminId(adminService.getRoleAdminId(authentication, SysPermEnumClass.RoleEnum.EDUCATION));
        List<UserDataResp> list = pmpUserExMapper.selectExportUserList(search);
        List<UserDataExport> exportList = new ArrayList<>();
        mapperFacade.mapAsCollection(list, exportList, UserDataExport.class);
        return exportList;
    }

    @Override
    public UserDataResp getUserDetailByKey(Long userId) {
        return pmpUserExMapper.selectUserDetail(userId, null, null);
    }

    @Override
    public UserImportResp insertUserBatch(List<UserDataImport> list) {
        UserImportResp result = new UserImportResp();
        // 数据入库
        List<PmpUserEntity> userList = new ArrayList<PmpUserEntity>();
        mapperFacade.mapAsCollection(list,userList,PmpUserEntity.class);
        int i=0;
        int j=0;
        for(PmpUserEntity item : userList){

            String userName = item.getUserName();
            String phoneNo = item.getPhoneNo();
            if(StringUtils.isBlank(userName) || StringUtils.isBlank(phoneNo)){
                logger.info("用户数据导入不处理信息====user=={}",item);
                continue;
            }
            Long userId = pmpUserExMapper.selectUserByName(userName,phoneNo);
            if(userId != null){
                // 修改
                item.setUserId(userId);
                pmpUserEntityMapper.updateByPrimaryKeySelective(item);
                j++;
            }else{
                pmpUserEntityMapper.insert(item);
                i++;
            }

        }
        result.setAddCount(i);
        result.setUpdateCount(j);
        return result;
    }
}