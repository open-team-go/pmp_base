package com.arz.pmp.base.api.service.user;

import com.arz.pmp.base.api.bo.excel.UserDataExport;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.api.bo.excel.UserImportResp;
import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserEditorReq;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.bo.user.front.*;
import com.arz.pmp.base.entity.PmpUserCourseApplyEntity;
import com.arz.pmp.base.entity.PmpUserEducationEntity;
import com.arz.pmp.base.entity.PmpUserPayTypeEntity;
import com.arz.pmp.base.entity.PmpUserResourceTypeEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.github.pagehelper.PageInfo;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * description 学员业务
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
public interface UserService {

    PageInfo<List<UserDataResp>> getUserListPage(RestRequest<UserSearchReq> req);

    Long addOrUpUser(UserEditorReq data, boolean addOn, String authentication);

    void deleteUser(Long UserId, String authentication);

    List<PmpUserEducationEntity> getEducationList();

    List<PmpUserPayTypeEntity> getPayTypeList();

    List<PmpUserResourceTypeEntity> getResourceTypeList();

    List<UserDataExport> getExportUserList(UserSearchReq search, String authentication);

    UserDataResp getUserDetailByKey(Long userId);

    UserImportResp insertUserBatch(List<UserDataImport> list, Long managerId);

    void updateUserRegister(UserPerfectReq data, String authentication);

    UserPerfectData getFrontUser(String authentication);

    String goLogin(UserCheckReq data);

    void logOut(String authentication);

    List<CourseListData> getUserCourseList(String authentication);

    PmpUserCourseApplyEntity getUserCourseApply(Long userRefCourseId, String authentication);

    void updateUserCourseApply(CourseApplyData data, String authentication);

    void insertUserTourists(UserCheckReq data);

    void updateUserLoginPassword(UserPasswordData data, String authentication);

    void insertUserCourse(CourseChoosingData data, String authentication);

    void deleteUserCourse(Long courseId, String authentication);
}
