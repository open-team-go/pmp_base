package com.arz.pmp.base.api.service.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.arz.pmp.base.mapper.*;
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
    private PmpCourseEntityMapper pmpCourseEntityMapper;
    @Autowired
    private PmpUserRefCourseEntityMapper pmpUserRefCourseEntityMapper;
    @Autowired
    private PmpRoomExMapper pmpRoomExMapper;
    @Autowired
    private PmpUserCourseApplyEntityMapper pmpUserCourseApplyEntityMapper;
    @Autowired
    private PmpUserTouristsEntityMapper pmpUserTouristsEntityMapper;
    @Value("${service.project.login.fail.max}")
    private int loginFailMax;

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
            Long id = validUserUnique(name, null, phoneNo);
            // 判断是否是学员已存在添加新选课
            if (addOn && id != null) {
                addOn = false;
                userId = id;
            }
            boolean flag = id == null || (!addOn && id.equals(userId));
            Assert.isTrue(flag, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI, "已存在同名同手机号学员信息");
        }
        PmpUserEntity entity = mapperFacade.map(data, PmpUserEntity.class);
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);

        // 新增
        if (addOn) {
            Assert.isTrue(StringUtils.isNotBlank(name), CommonCodeEnum.PARAM_ERROR);
            insertUserEntity(entity, operatorId);
            userId = entity.getUserId();
            // 设置学员登录默认信息
            updateUserDefaultLoginInfo(userId, operatorId);
            // 新增选课信息
            PmpUserRefCourseEntity userRefCourseEntity = mapperFacade.map(data, PmpUserRefCourseEntity.class);
            userRefCourseEntity.setUserId(userId);
            insertUserRefCourseEntity(userRefCourseEntity, operatorId);
            return userRefCourseEntity.getId();
        } else {
            Assert.isTrue(userId != null, CommonCodeEnum.PARAM_ERROR);
            updateUserEntity(entity, operatorId);
            // 更新选课信息
            Long targetId = pmpUserExMapper.selectUserRefCourseId(userId, courseId);
            PmpUserRefCourseEntity userRefCourseEntity = mapperFacade.map(data, PmpUserRefCourseEntity.class);
            userRefCourseEntity.setUserId(userId);
            // 新增选课
            if (targetId == null) {
                insertUserRefCourseEntity(userRefCourseEntity, operatorId);
            } else {
                // 更新选课信息
                userRefCourseEntity.setId(targetId);
                updateUserRefCourseEntity(userRefCourseEntity, operatorId);
            }

            return userRefCourseId;
        }

    }

    /** 后台构建新学员时默认登录信息 */
    private void updateUserDefaultLoginInfo(Long userId, Long operatorId) {
        PmpUserEntity userEntity = new PmpUserEntity();
        userEntity.setUserId(userId);
        userEntity.setLoginName(getUserDefaultLoginName(userId));
        String loginSale = EncryptUtils.createSalt();
        userEntity.setLoginSalt(loginSale);
        String loginPassword = EncryptUtils.createSysUserPsw(Constants.ADMIN_DEFAULT_PASSWORD, loginSale);
        userEntity.setLoginPassword(loginPassword);
        updateUserEntity(userEntity, operatorId);
    }

    private static final String USER_DEFAULT_LOGIN_NAME_PRE = "user_";

    private String getUserDefaultLoginName(Long userId) {
        return USER_DEFAULT_LOGIN_NAME_PRE + userId;
    }

    @Override
    public void deleteUser(Long userId, String authentication) {
        PmpUserEntity userEntity = pmpUserEntityMapper.selectByPrimaryKey(userId);
        if (userEntity == null || userEntity.getDelOn()) {
            return;
        }
        PmpUserEntity entity = new PmpUserEntity();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);
        entity.setUserId(userId);
        entity.setDelOn(true);
        updateUserEntity(entity, operatorId);
        // 更新对应游客信息
        pmpUserExMapper.updateTouristsDelByUser(userId);
        // 清空登录信息
        cleanFrontUserCache(userEntity.getLoginName());
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
                    updateUserEntity(user, managerId);
                    j++;
                } else {
                    insertUserEntity(user, managerId);
                    i++;
                    userId = user.getUserId();
                    // 设置学员登录默认信息
                    updateUserDefaultLoginInfo(userId, managerId);
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
                insertUserRefCourseEntity(userRefCourseEntity, managerId);
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

    @Override
    public void updateUserRegister(UserPerfectReq data, String authentication) {
        UserCacheData userInfo = getFrontUserCache(authentication);
        Long userId = userInfo.getUserId();
        Long oldUserId = null;
        // 更新用户表基本信息
        if (userId != null) {
            PmpUserEntity oldUser = pmpUserEntityMapper.selectByPrimaryKey(userId);
            if (oldUser == null || oldUser.getDelOn()) {
                oldUserId = null;
            }
            oldUserId = oldUser.getUserId();
            // 不允许前台修改手机号
            if (StringUtils.isNotBlank(oldUser.getPhoneNo()) && !data.getPhoneNo().equals(oldUser.getPhoneNo())) {
                Assert.isTrue(false, CommonCodeEnum.PARAM_ERROR_USER_EDITOR);
            }
            // 不允许前台修改身份证号
            if (StringUtils.isNotBlank(oldUser.getIdentityNo())
                && !data.getIdentityNo().equals(oldUser.getIdentityNo())) {
                Assert.isTrue(false, CommonCodeEnum.PARAM_ERROR_USER_EDITOR);
            }
            // 不允许前台修改身份证号
            if (StringUtils.isNotBlank(oldUser.getUserName()) && !data.getUserName().equals(oldUser.getUserName())) {
                Assert.isTrue(false, CommonCodeEnum.PARAM_ERROR_USER_EDITOR);
            }

        } else {
            // 完善用户信息：同步游客数据到用户表
            oldUserId = validUserUnique(data.getUserName(), null, data.getPhoneNo());
        }
        PmpUserEntity userEntity = mapperFacade.map(data, PmpUserEntity.class);
        // 添加新的学员信息
        boolean perfectFlag = false;
        Long touristsId = null;
        if (oldUserId == null) {
            PmpUserTouristsEntity touristsEntity =
                pmpUserExMapper.selectUserTouristsByLoginName(userInfo.getLoginName());
            touristsId = touristsEntity.getId();
            userEntity.setLoginName(touristsEntity.getLoginName());
            userEntity.setLoginSalt(touristsEntity.getLoginSalt());
            userEntity.setLoginPassword(touristsEntity.getLoginPassword());
            insertUserEntity(userEntity, null);
            perfectFlag = true;
        } else {
            // 更新学员信息
            userEntity.setUserId(oldUserId);
            if (!userInfo.isPerfectOn()) {
                PmpUserTouristsEntity touristsEntity =
                    pmpUserExMapper.selectUserTouristsByLoginName(userInfo.getLoginName());
                touristsId = touristsEntity.getId();
                userEntity.setLoginName(touristsEntity.getLoginName());
                userEntity.setLoginSalt(touristsEntity.getLoginSalt());
                userEntity.setLoginPassword(touristsEntity.getLoginPassword());
                perfectFlag = true;
            }
            updateUserEntity(userEntity, null);
        }
        // 更新游客、学员绑定关系
        if (perfectFlag) {
            PmpUserTouristsEntity touristsEntity = new PmpUserTouristsEntity();
            touristsEntity.setId(touristsId);
            // 更新游客信息
            touristsEntity.setPerfectOn(true);
            touristsEntity.setUserId(userEntity.getUserId());
            updateUserTouristsEntity(touristsEntity, null);
            // 更新缓存信息
            userInfo.setPerfectOn(true);
            userInfo.setUserId(oldUserId);
            cacheRedisFrontUser(userInfo, authentication);
        }
    }

    /**
     * 学员添加
     */
    private Long insertUserEntity(PmpUserEntity userEntity, Long operatorId) {
        userEntity.setCreateManager(operatorId);
        userEntity.setCreateTime(DateUtil.getCurSecond());
        userEntity.setDelOn(false);
        pmpUserEntityMapper.insertSelective(userEntity);
        return userEntity.getUserId();
    }

    /**
     * 学员更新
     */
    private void updateUserEntity(PmpUserEntity userEntity, Long operatorId) {
        userEntity.setUpdateManager(operatorId);
        userEntity.setUpdateTime(DateUtil.getCurSecond());
        pmpUserEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    /**
     * 游客更新
     */
    private void updateUserTouristsEntity(PmpUserTouristsEntity touristsEntity, Long operatorId) {
        pmpUserTouristsEntityMapper.updateByPrimaryKeySelective(touristsEntity);
    }

    @Override
    public UserPerfectData getFrontUser(String authentication) {
        UserCacheData userInfo = getFrontUserCache(authentication);
        if (userInfo.getUserId() == null) {
            return new UserPerfectData();
        }

        return pmpUserExMapper.selectFrontUserData(userInfo.getUserId());
    }

    @Override
    public String goLogin(UserCheckReq data) {
        // 验证登录信息
        UserCacheData userInfo = validUserLogin(data.getLoginName(), data.getLoginPassword());
        // 登录信息缓存，用户名作为唯一标识
        String authentication = cacheRedisFrontUser(userInfo, null);
        return authentication;
    }

    @Override
    public void logOut(String authentication) {
        redisService.delFrontUser(authentication);
    }

    @Override
    public List<CourseListData> getUserCourseList(String authentication) {
        UserCacheData userInfo = getFrontUserCache(authentication);
        UserSearchReq searchReq = new UserSearchReq();
        searchReq.setUserId(userInfo.getUserId());
        return pmpUserExMapper.selectUserCourseList(searchReq);
    }

    @Override
    public PmpUserCourseApplyEntity getUserCourseApply(Long userRefCourseId, String authentication) {
        if (userRefCourseId == null) {
            return null;
        }
        UserCacheData userInfo = getFrontUserCache(authentication);
        Long userId = userInfo.getUserId();
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
            UserCacheData userInfo = getFrontUserCache(authentication);
            entity.setUserId(userInfo.getUserId());
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

    @Override
    public void updateUserLoginPassword(UserPasswordData data, String authentication) {

        Assert.isTrue(!data.getNewPassword().equals(data.getOldPassword()),
            CommonCodeEnum.PARAM_ERROR_USER_LOGIN_PASSWORD_CHANGE);

        UserCacheData loginInfo = getFrontUserCache(authentication);
        boolean flag = false;
        if (loginInfo.getUserId() != null) {
            PmpUserEntity userEntity = pmpUserEntityMapper.selectByPrimaryKey(loginInfo.getUserId());
            Assert.isTrue(EncryptUtils.doCredentialsMatch(data.getOldPassword(), userEntity.getLoginSalt(),
                userEntity.getLoginPassword()), CommonCodeEnum.PARAM_ERROR_LOGIN_PASSWORD);
            flag = true;
            userEntity = new PmpUserEntity();
            userEntity.setUserId(loginInfo.getUserId());
            String loginSalt = EncryptUtils.createSalt();
            userEntity.setLoginSalt(loginSalt);
            userEntity.setLoginPassword(EncryptUtils.createSysUserPsw(data.getNewPassword(), loginSalt));
            updateUserEntity(userEntity, null);
        }
        if (loginInfo.getTouristsId() != null) {
            PmpUserTouristsEntity touristsEntity =
                pmpUserTouristsEntityMapper.selectByPrimaryKey(loginInfo.getTouristsId());
            Assert.isTrue(flag || EncryptUtils.doCredentialsMatch(data.getOldPassword(), touristsEntity.getLoginSalt(),
                touristsEntity.getLoginPassword()), CommonCodeEnum.PARAM_ERROR_LOGIN_PASSWORD);

            touristsEntity = new PmpUserTouristsEntity();
            touristsEntity.setId(loginInfo.getTouristsId());
            String loginSalt = EncryptUtils.createSalt();
            touristsEntity.setLoginSalt(loginSalt);
            touristsEntity.setLoginPassword(EncryptUtils.createSysUserPsw(data.getNewPassword(), loginSalt));
            updateUserTouristsEntity(touristsEntity, null);
        }
        // 清除用户登录信息
        cleanFrontUserCache(loginInfo.getLoginName());
    }

    @Override
    public void insertUserCourse(Long courseId, String authentication) {
        PmpCourseEntity courseEntity = pmpCourseEntityMapper.selectByPrimaryKey(courseId);
        Assert.isTrue(courseEntity != null && !courseEntity.getDelOn(), CommonCodeEnum.PARAM_ERROR);
        UserCacheData loginInfo = getFrontUserCache(authentication);
        // 查询是否已选过该课程
        Long refCourseId = pmpUserExMapper.selectUserRefCourseId(loginInfo.getUserId(), courseId);
        Assert.isTrue(refCourseId == null, CommonCodeEnum.PARAM_ERROR_USER_CHOOSE_COURSE_MULTI);
        PmpUserRefCourseEntity refCourseEntity = new PmpUserRefCourseEntity();
        refCourseEntity.setCourseId(courseId);
        refCourseEntity.setUserId(loginInfo.getUserId());
        insertUserRefCourseEntity(refCourseEntity, null);
    }

    /**
     * 新增学员选课
     */
    private void insertUserRefCourseEntity(PmpUserRefCourseEntity refCourseEntity, Long operatorId) {
        refCourseEntity.setCreateTime(DateUtil.getCurSecond());
        refCourseEntity.setCreateManager(operatorId);
        pmpUserRefCourseEntityMapper.insertSelective(refCourseEntity);
    }

    /**
     * 更新学员选课
     */
    private void updateUserRefCourseEntity(PmpUserRefCourseEntity refCourseEntity, Long operatorId) {
        refCourseEntity.setUpdateTime(DateUtil.getCurSecond());
        refCourseEntity.setUpdateManager(operatorId);
        pmpUserRefCourseEntityMapper.updateByPrimaryKeySelective(refCourseEntity);
    }

    /**
     * 登录信息缓存获取
     */
    private UserCacheData getFrontUserCache(String authentication) {

        return redisService.geFrontUserByToken(authentication);
    }

    /**
     * 登录信息缓存
     */
    private void cleanFrontUserCache(String loginName) {

        redisService.delFrontUserUniqueCache(loginName);
    }

    /**
     * description
     * 
     * @author chen wei
     * @date 2020/3/19
     */
    private void validLoginName(String loginName) {
        Assert.isTrue(!loginName.startsWith("user_"), CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
        // 游客表
        PmpUserTouristsEntity touristsEntity = pmpUserExMapper.selectUserTouristsByLoginName(loginName);
        Assert.isTrue(touristsEntity == null, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
        // 用户表
        PmpUserEntity userEntity = pmpUserExMapper.selectUserByLoginName(loginName);
        Assert.isTrue(userEntity == null, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
    }

    public UserCacheData validUserLogin(String loginName, String password) {
        // 登录错误处理
        int failNum = validLoginRefuse(loginName);

        PmpUserEntity user = pmpUserExMapper.selectUserByLoginName(loginName);
        String validPassword = null;
        String validSalt = null;
        Long touristsId = null;
        Long userId = null;
        if (user != null) {
            validPassword = user.getLoginPassword();
            validSalt = user.getLoginSalt();
            PmpUserTouristsEntity touristsEntity = pmpUserExMapper.selectUserTouristsByLoginName(loginName);
            if (touristsEntity != null) {
                touristsId = touristsEntity.getId();
            }
            userId = user.getUserId();
        } else {
            PmpUserTouristsEntity touristsEntity = pmpUserExMapper.selectUserTouristsByLoginName(loginName);
            Assert.isTrue(touristsEntity != null, CommonCodeEnum.PARAM_ERROR_LOGIN_USERNAME);
            validPassword = touristsEntity.getLoginPassword();
            validSalt = touristsEntity.getLoginSalt();
            userId = touristsEntity.getUserId();
            touristsId = touristsEntity.getId();
        }
        // 验证密码
        if (!EncryptUtils.doCredentialsMatch(password, validSalt, validPassword)) {
            // 更新登录失败统计
            redisService.updateLoginFailNum(loginName, failNum + 1, false);
            Assert.isTrue(false, CommonCodeEnum.PARAM_ERROR_LOGIN_PASSWORD);
        }
        // 登录成功清除错误次数统计
        if (failNum > 0) {
            redisService.delLoginFailNum(loginName, false);
        }

        UserCacheData userInfo = new UserCacheData();
        userInfo.setLoginName(loginName);
        if (userId != null) {
            userInfo.setPerfectOn(true);
        }
        userInfo.setUserId(userId);
        userInfo.setTouristsId(touristsId);
        return userInfo;
    }

    /**
     * description 校验用户名是否被禁止登录
     *
     * @param userName
     * @author chen wei
     * @date 2020/3/28
     */
    private int validLoginRefuse(String userName) {

        int failNum = redisService.getLoginFailNum(userName, false);
        Assert.isTrue(failNum < loginFailMax, CommonCodeEnum.PERMISSION_ERROR_LOGIN_REFUSE);
        return failNum;
    }

    /**
     * 登录信息缓存
     */
    private String cacheRedisFrontUser(UserCacheData userInfo, String authentication) {
        if (StringUtils.isBlank(authentication)) {
            authentication = createAuthToken();
        }
        redisService.setFrontUser(authentication, userInfo);
        // 单点登录控制
        redisService.setFrontUserUniqueLogin(userInfo.getLoginName(), authentication);
        return authentication;
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
