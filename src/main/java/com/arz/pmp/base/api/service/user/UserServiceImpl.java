package com.arz.pmp.base.api.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.arz.pmp.base.api.bo.excel.UserDataExport;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.api.bo.excel.UserImportResp;
import com.arz.pmp.base.api.bo.room.RoomSearchReq;
import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserEditorReq;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.bo.user.front.*;
import com.arz.pmp.base.api.service.admin.AdminService;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.*;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.constants.Constants;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.commons.utils.EncryptUtils;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import com.arz.pmp.base.mapper.PmpUserCourseApplyEntityMapper;
import com.arz.pmp.base.mapper.PmpUserEntityMapper;
import com.arz.pmp.base.mapper.PmpUserRefCourseEntityMapper;
import com.arz.pmp.base.mapper.PmpUserTouristsEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpAdminExMapper;
import com.arz.pmp.base.mapper.ex.PmpCourseExMapper;
import com.arz.pmp.base.mapper.ex.PmpRoomExMapper;
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
    @Autowired
    private PmpUserRefCourseEntityMapper pmpUserRefCourseEntityMapper;
    @Autowired
    private PmpRoomExMapper pmpRoomExMapper;
    @Autowired
    private PmpUserCourseApplyEntityMapper pmpUserCourseApplyEntityMapper;
    @Autowired
    private PmpUserTouristsEntityMapper pmpUserTouristsEntityMapper;

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
        Long userRefCourseId = data.getUserRefCourseId();
        Long courseId = data.getCourseId();
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
            // 新增选课信息
            PmpUserRefCourseEntity userRefCourseEntity = mapperFacade.map(data, PmpUserRefCourseEntity.class);
            userRefCourseEntity.setUserId(userId);
            userRefCourseEntity.setCreateManager(operatorId);
            userRefCourseEntity.setCreateTime(curTimeSec);
            pmpUserRefCourseEntityMapper.insertSelective(userRefCourseEntity);
            return userRefCourseEntity.getId();
        } else {
            Assert.isTrue(userId != null && userRefCourseId != null, CommonCodeEnum.PARAM_ERROR);
            entity.setUpdateTime(curTimeSec);
            entity.setUpdateManager(operatorId);
            pmpUserEntityMapper.updateByPrimaryKeySelective(entity);
            // 更新选课信息
            Long targetId = pmpUserExMapper.selectUserRefCourseId(userId, courseId);
            Assert.isTrue(targetId == null || targetId.equals(userRefCourseId),
                CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI, "学员不能选择重复课程");
            PmpUserRefCourseEntity userRefCourseEntity = mapperFacade.map(data, PmpUserRefCourseEntity.class);
            userRefCourseEntity.setId(userRefCourseId);
            userRefCourseEntity.setUpdateManager(operatorId);
            userRefCourseEntity.setUpdateTime(curTimeSec);
            pmpUserRefCourseEntityMapper.updateByPrimaryKeySelective(userRefCourseEntity);

            return userRefCourseId;
        }

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

    private Long getPayIdByName(List<PmpUserPayTypeEntity> list, String name) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)) {
            return null;
        }
        for (PmpUserPayTypeEntity item : list) {
            if (item.getPayName().equalsIgnoreCase(name)) {
                return item.getPayId();
            }
        }
        return null;
    }

    private Long getResourceIdByName(List<PmpUserResourceTypeEntity> list, String name) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)) {
            return null;
        }
        for (PmpUserResourceTypeEntity item : list) {
            if (item.getResourceName().equalsIgnoreCase(name)) {
                return item.getResourceId();
            }
        }
        return null;
    }

    private Long getEducationIdByName(List<PmpUserEducationEntity> list, String name) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)) {
            return null;
        }
        for (PmpUserEducationEntity item : list) {
            if (item.getEducationName().equalsIgnoreCase(name)) {
                return item.getEducationId();
            }
        }
        return null;
    }

    private Long getAdminIdByName(List<PmpAdminEntity> list, String name) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)) {
            return null;
        }
        for (PmpAdminEntity item : list) {
            if (item.getNickname().equalsIgnoreCase(name)) {
                return item.getAdminId();
            }
        }
        return null;
    }

    private Long getCourseIdByName(List<PmpCourseEntity> list, String name) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)) {
            return null;
        }
        for (PmpCourseEntity item : list) {
            if (item.getCourseName().equalsIgnoreCase(name)) {
                return item.getCourseId();
            }
        }
        return null;
    }

    private Long getRoomIdByName(List<PmpTeachingRoomEntity> list, String name) {
        if (CollectionUtils.isEmpty(list) || StringUtils.isBlank(name)) {
            return null;
        }
        for (PmpTeachingRoomEntity item : list) {
            if (item.getRoomName().equalsIgnoreCase(name)) {
                return item.getRoomId();
            }
        }
        return null;
    }

    @Override
    public UserDataResp getUserDetailByKey(Long userRefCourseId) {

        UserSearchReq search = new UserSearchReq();
        search.setUserRefCourseId(userRefCourseId);
        return pmpUserExMapper.selectUserDetail(search);
    }

    @Override
    public UserImportResp insertUserBatch(List<UserDataImport> list, Long managerId) {

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
        List<PmpTeachingRoomEntity> roomList = pmpRoomExMapper.selectRoomSimpleList(new RoomSearchReq());
        // 导入失败记录
        List<UserDataImport> errorList = null;
        // 填充配置数据、数据校验
        for (UserDataImport item : list) {
            String userName = item.getUserName();
            String identityNo = null;
            String phoneNo = item.getPhoneNo();
            if (StringUtils.isBlank(userName) || StringUtils.isBlank(phoneNo)
                || !Constants.REGEX_PHONE_NO.matcher(phoneNo).matches()) {
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
            item.setPayId(getPayIdByName(payTypeList, item.getPayName()));
            // 获取来源ID
            item.setResourceId(getResourceIdByName(resourceTypeList, item.getResourceName()));
            // 获取学历ID
            item.setEducationId(getEducationIdByName(educationList, item.getEducationName()));
            // 获取顾问ID
            item.setAdminId(getAdminIdByName(adminList, item.getSalesAdminName()));
            // 获取课程ID
            Long courseId = getCourseIdByName(courseList, item.getCourseName());
            item.setCourseId(courseId);
            // 获取班级ID
            item.setRoomId(getRoomIdByName(roomList, item.getRoomName()));

            // 数据入库
            PmpUserEntity user = mapperFacade.map(item, PmpUserEntity.class);
            // 用户唯一性确定
            Long userId = validUserUnique(userName, identityNo, item.getPhoneNo());
            Long curTime = DateUtil.getCurSecond();
            try {
                if (userId != null) {
                    // 修改
                    user.setUserId(userId);
                    user.setUpdateTime(curTime);
                    user.setUpdateManager(managerId);
                    pmpUserEntityMapper.updateByPrimaryKeySelective(user);
                    j++;
                } else {
                    user.setCreateTime(curTime);
                    user.setCreateManager(managerId);
                    pmpUserEntityMapper.insertSelective(user);
                    i++;
                    userId = user.getUserId();
                }
            } catch (Exception e) {
                logger.error("here is exception====", e);
                errorList.add(item);
                continue;
            }

            // TODO 选课唯一性确定
            if (courseId == null) {
                continue;
            }
            Long userRefCourseId = pmpUserExMapper.selectUserRefCourseId(userId, item.getCourseId());
            PmpUserRefCourseEntity userRefCourseEntity = mapperFacade.map(item, PmpUserRefCourseEntity.class);
            if (userRefCourseId == null) {
                // 新增选课
                userRefCourseEntity.setUserId(userId);
                userRefCourseEntity.setCreateTime(curTime);
                userRefCourseEntity.setCreateManager(managerId);
                pmpUserRefCourseEntityMapper.insertSelective(userRefCourseEntity);
            } else {
                // 更新选课信息
                userRefCourseEntity.setUpdateTime(curTime);
                userRefCourseEntity.setId(userRefCourseId);
                userRefCourseEntity.setUpdateManager(managerId);
                pmpUserRefCourseEntityMapper.updateByPrimaryKeySelective(userRefCourseEntity);
            }

        }
        result.setAddCount(i);
        result.setUpdateCount(j);
        return result;
    }

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
    public void updateUserRegister(UserPerfectReq data, String authentication) {
        Long userId = redisService.geFrontUserByToken(authentication);
        Long hasUserId = validUserUnique(data.getUserName(), data.getIdentityNo(), data.getPhoneNo());
        Assert.isTrue(hasUserId == null || userId.equals(hasUserId), CommonCodeEnum.PARAM_ERROR_USER_MULTI_ERROR);

        PmpUserEntity userEntity = mapperFacade.map(data, PmpUserEntity.class);
        userEntity.setUserId(userId);
        pmpUserEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    @Override
    public UserPerfectData getFrontUser(String authentication) {

        Long userId = redisService.geFrontUserByToken(authentication);

        return pmpUserExMapper.selectFrontUserData(userId);
    }

    @Override
    public String goLogin(UserCheckReq data) {

        // 验证登录信息
        PmpUserEntity user = validUser(data.getLoginName(), data.getLoginPassword());
        // 登录信息缓存
        String authentication = cacheRedisFrontUser(user.getUserId());
        return authentication;
    }

    @Override
    public void logOut(String authentication) {
        redisService.delFrontUser(authentication);
    }

    @Override
    public List<CourseListData> getUserCourseList(String authentication) {
        Long userId = redisService.geFrontUserByToken(authentication);
        UserSearchReq searchReq = new UserSearchReq();
        searchReq.setUserId(userId);
        return pmpUserExMapper.selectUserCourseList(searchReq);
    }

    @Override
    public PmpUserCourseApplyEntity getUserCourseApply(Long userRefCourseId, String authentication) {
        if (userRefCourseId == null) {
            return null;
        }
        Long userId = redisService.geFrontUserByToken(authentication);
        // 校验选课
        PmpUserRefCourseEntity userCourse = pmpUserRefCourseEntityMapper.selectByPrimaryKey(userRefCourseId);
        Assert.isTrue(userCourse != null, CommonCodeEnum.PARAM_ERROR);
        Assert.isTrue(userCourse.getUserId().equals(userId), CommonCodeEnum.PARAM_ERROR);
        PmpUserCourseApplyEntity entity = pmpUserExMapper.selectUserCourseApply(userRefCourseId);
        if (entity == null) {
            return null;
        }
        Assert.isTrue(entity.getUserId().equals(userId), CommonCodeEnum.PARAM_ERROR);
        return entity;
    }

    @Override
    public void updateUserCourseApply(CourseApplyData data, String authentication) {
        PmpUserCourseApplyEntity entity = getUserCourseApply(data.getUserRefCourseId(), authentication);
        if (entity == null) {
            entity = new PmpUserCourseApplyEntity();
            // 新增
            entity.setHtmlContent(data.getHtmlContent());
            Long userId = redisService.geFrontUserByToken(authentication);
            entity.setUserId(userId);
            entity.setUserRefCourseId(data.getUserRefCourseId());
            pmpUserCourseApplyEntityMapper.insertSelective(entity);
        } else {
            Long id = entity.getId();
            entity.setHtmlContent(data.getHtmlContent());
            entity = new PmpUserCourseApplyEntity();
            entity.setId(id);
            pmpUserCourseApplyEntityMapper.updateByPrimaryKeySelective(entity);
        }
    }

    @Override
    public void insertUserTourists(UserCheckReq data) {
        // 检验用户名唯一
        validLoginName(data.getLoginName());
        // 密码加密处理
        String salt = EncryptUtils.createSalt();
        String encryptPassword = EncryptUtils.createSysUserPsw(data.getLoginPassword(), salt);
        // 新增游客信息
        PmpUserTouristsEntity touristsEntity = new PmpUserTouristsEntity();
        touristsEntity.setDelOn(false);
        touristsEntity.setCreateTime(DateUtil.getCurSecond());
        touristsEntity.setLoginName(data.getLoginName());
        touristsEntity.setLoginPassword(encryptPassword);
        touristsEntity.setLoginSalt(salt);
        touristsEntity.setPerfectOn(false);
        pmpUserTouristsEntityMapper.insert(touristsEntity);
    }

    /**
     * description
     * 
     * @author chen wei
     * @date 2020/3/19
     */
    private void validLoginName(String loginName) {
        // 游客表
        PmpUserTouristsEntity touristsEntity = pmpUserExMapper.selectUserTouristsByLoginName(loginName);
        Assert.isTrue(touristsEntity == null, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
        // 用户表
        PmpUserEntity userEntity = pmpUserExMapper.selectUserByLoginName(loginName);
        Assert.isTrue(userEntity == null, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
    }

    public PmpUserEntity validUser(String userName, String password) {
        PmpUserEntity user = pmpUserExMapper.selectUserByName(userName, password, null);

        Assert.isTrue(user != null, CommonCodeEnum.PARAM_ERROR_LOGIN_USERNAME);
        // 验证密码
        Assert.isTrue(password.equals(user.getIdentityNo()), CommonCodeEnum.PARAM_ERROR_LOGIN_PASSWORD);
        return user;
    }

    private String cacheRedisFrontUser(Long userId) {

        String token = createAuthToken();
        redisService.setFrontUser(token, userId);
        return token;
    }

    private String createAuthToken() {

        return UUID.randomUUID().toString();
    }

    private Long validUserUnique(String userName, String identityNo, String phoneNo) {

        PmpUserEntity userEntity = pmpUserExMapper.selectUserByName(userName, identityNo, phoneNo);
        if (userEntity == null) {
            return null;
        }
        return userEntity.getUserId();
    }
}
