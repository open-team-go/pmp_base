package com.arz.pmp.base.api.service.user;

import java.util.ArrayList;
import java.util.List;

import com.arz.pmp.base.framework.commons.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.arz.pmp.base.api.bo.excel.UserDataExport;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.api.bo.excel.UserImportResp;
import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserEditorReq;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.bo.user.front.UserCheckReq;
import com.arz.pmp.base.api.bo.user.front.UserPerfectData;
import com.arz.pmp.base.api.bo.user.front.UserPerfectReq;
import com.arz.pmp.base.api.bo.user.front.UserRegistReq;
import com.arz.pmp.base.api.service.admin.AdminService;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.*;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import com.arz.pmp.base.mapper.PmpUserEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpAdminExMapper;
import com.arz.pmp.base.mapper.ex.PmpCourseExMapper;
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
    @Autowired
    private PmpAdminExMapper pmpAdminExMapper;
    @Autowired
    private PmpCourseExMapper pmpCourseExMapper;

    @Override
    public PageInfo<List<UserDataResp>> getUserListPage(RestRequest<UserSearchReq> req) {

        RequestHeader requestHeader = req.getHeader();
        UserSearchReq search = req.getBody();

        // 判斷是否是教务人员
        Long educationAdminId =
            adminService.getRoleAdminId(req.getHeader().getAuthentication(), SysPermEnumClass.RoleEnum.EDUCATION);
        if (educationAdminId != null) {
            search.setEducationAdminId(educationAdminId);
        }
        Long salesAdminId =
            adminService.getRoleAdminId(req.getHeader().getAuthentication(), SysPermEnumClass.RoleEnum.SALES);
        if (salesAdminId != null) {
            search.setSalesAdminId(salesAdminId);
        }

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
        String identityNo = data.getIdentityNo();
        if (StringUtils.isNotBlank(name)) {
            Long id = validUserUnique(name, identityNo, phoneNo);
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
            pmpUserEntityMapper.insertSelective(entity);
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
        return pmpUserExMapper.selectEducationList(false);
    }

    @Override
    public List<PmpUserPayTypeEntity> getPayTypeList() {
        return pmpUserExMapper.selectUserPayTypeList(false);
    }

    @Override
    public List<PmpUserResourceTypeEntity> getResourceTypeList() {

        return pmpUserExMapper.selectResourceTypeList(false);
    }

    @Override
    public List<UserDataExport> getExportUserList(UserSearchReq search, String authentication) {
        // 判斷是否是教务人员
        Long educationAdminId = adminService.getRoleAdminId(authentication, SysPermEnumClass.RoleEnum.EDUCATION);
        if (educationAdminId != null) {
            search.setEducationAdminId(educationAdminId);
        }
        Long salesAdminId = adminService.getRoleAdminId(authentication, SysPermEnumClass.RoleEnum.SALES);
        if (salesAdminId != null) {
            search.setSalesAdminId(salesAdminId);
        }
        List<UserDataResp> list = pmpUserExMapper.selectExportUserList(search);
        List<UserDataExport> exportList = new ArrayList<>();
        mapperFacade.mapAsCollection(list, exportList, UserDataExport.class);
        return exportList;
    }

    private Long getPayIdByName(List<PmpUserPayTypeEntity> list, String name){
        if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)){
            return null;
        }
        for(PmpUserPayTypeEntity item:list){
            if(item.getPayName().equalsIgnoreCase(name)){
                return item.getPayId();
            }
        }
        return null;
    }

    private Long getResourceIdByName(List<PmpUserResourceTypeEntity> list, String name){
        if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)){
            return null;
        }
        for(PmpUserResourceTypeEntity item:list){
            if(item.getResourceName().equalsIgnoreCase(name)){
                return item.getResourceId();
            }
        }
        return null;
    }

    private Long getEducationIdByName(List<PmpUserEducationEntity> list, String name){
        if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)){
            return null;
        }
        for(PmpUserEducationEntity item:list){
            if(item.getEducationName().equalsIgnoreCase(name)){
                return item.getEducationId();
            }
        }
        return null;
    }

    private Long getAdminIdByName(List<PmpAdminEntity> list, String name){
        if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)){
            return null;
        }
        for(PmpAdminEntity item:list){
            if(item.getNickname().equalsIgnoreCase(name)){
                return item.getAdminId();
            }
        }
        return null;
    }

    private Long getCourseIdByName(List<PmpCourseEntity> list, String name){
        if(CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)){
            return null;
        }
        for(PmpCourseEntity item:list){
            if(item.getCourseName().equalsIgnoreCase(name)){
                return item.getCourseId();
            }
        }
        return null;
    }

    @Override
    public UserDataResp getUserDetailByKey(Long userRefCourseId) {
        return pmpUserExMapper.selectUserDetail(userRefCourseId);
    }

    @Override
    public UserImportResp insertUserBatch(List<UserDataImport> list) {


        UserImportResp result = new UserImportResp();
        if (CollectionUtils.isEmpty(list)) {
            return result;
        }
        int i = 0;
        int j = 0;
        List<PmpUserPayTypeEntity> payTypeList = pmpUserExMapper.selectUserPayTypeList(null);
        List<PmpUserResourceTypeEntity> resourceTypeList = pmpUserExMapper.selectResourceTypeList(null);
        List<PmpUserEducationEntity> educationList = pmpUserExMapper.selectEducationList(null);
        List<PmpAdminEntity> adminList = pmpAdminExMapper.selectAdminAll();
        List<PmpCourseEntity> courseList = pmpCourseExMapper.selectCourseAll();
        // 导入失败记录
        List<UserDataImport> errorList = null;
        // 填充配置数据、数据校验
        for (UserDataImport item : list) {
            String userName = item.getUserName();
            String identityNo = null;
            String phoneNo = item.getPhoneNo();
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(phoneNo) || !Constants.REGEX_PHONE_NO.matcher(phoneNo).matches()) {
                // || StringUtils.isBlank(identityNo)
                // || !identityNo.matches(Constants.REGEX_IDENTITY_NO)
                logger.info("用户数据导入不处理信息====user=={}", item);
                if (errorList == null) {
                    errorList = new ArrayList<>();
                    result.setErrorList(errorList);
                }
                errorList.add(item);
                continue;
            }
            // 获取支付方式ID
            item.setPayId(getPayIdByName(payTypeList,item.getPayName()));
            // 获取来源ID
            item.setResourceId(getResourceIdByName(resourceTypeList,item.getResourceName()));
            //  获取学历ID
            item.setEducationId(getEducationIdByName(educationList,item.getEducationName()));
            // 获取顾问ID
            item.setAdminId(getAdminIdByName(adminList,item.getSalesAdminName()));
            // 获取课程ID
            item.setCourseId(getCourseIdByName(courseList,item.getCourseName()));

            // 数据入库
            PmpUserEntity user = mapperFacade.map(item,PmpUserEntity.class);
            // 用户唯一性确定
            Long userId = validUserUnique(userName, identityNo, item.getPhoneNo());
            Long curTime = DateUtil.getCurSecond();
            try {
                if (userId != null) {
                    // 修改
                    user.setUserId(userId);
                    user.setUpdateTime(curTime);
                    pmpUserEntityMapper.updateByPrimaryKeySelective(user);
                    j++;
                } else {
                    user.setCreateTime(curTime);
                    pmpUserEntityMapper.insertSelective(user);
                    i++;
                }
            }catch (Exception e){
                logger.error("here is exception====",e);
                errorList.add(item);
            }

            // TODO 选课唯一性确定
        }
        result.setAddCount(i);
        result.setUpdateCount(j);
        return result;
    }

    @Override
    public Long insertUserRegister(UserRegistReq data) {

        PmpUserEntity userEntity = mapperFacade.map(data, PmpUserEntity.class);

        Long hasUserId = validUserUnique(data.getUserName(), data.getIdentityNo(), data.getPhoneNo());
        Assert.isTrue(hasUserId == null, CommonCodeEnum.PARAM_ERROR_USER_MULTI_ERROR);
        Long curTime = DateUtil.getCurSecond();
        userEntity.setCreateTime(curTime);
        pmpUserEntityMapper.insertSelective(userEntity);
        logger.info("新注册学员====userId=={}", userEntity.getUserId());
        return userEntity.getUserId();
    }

    @Override
    public void updateUserRegister(UserPerfectReq data) {
        List<Long> idList = pmpUserExMapper.selectUserIds(data.getUserName(), data.getIdentityNo());
        Assert.isTrue(!CollectionUtils.isEmpty(idList), CommonCodeEnum.PARAM_ERROR_LOGIN_USERNAME);
        PmpUserEntity userEntity = mapperFacade.map(data, PmpUserEntity.class);
        for (Long id : idList) {
            userEntity.setUserId(id);
            pmpUserEntityMapper.updateByPrimaryKeySelective(userEntity);
        }
    }

    @Override
    public UserPerfectData getFrontUser(UserCheckReq data) {
        return pmpUserExMapper.selectFrontUserData(data.getUserName(), data.getIdentityNo());
    }

    private Long validUserUnique(String userName,String identityNo,String phoneNo){

        PmpUserEntity userEntity = pmpUserExMapper.selectUserByName(userName,identityNo,phoneNo);
        if(userEntity == null){
            return null;
        }
        return userEntity.getUserId();
    }
}
