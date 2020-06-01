package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.bo.user.UsersStatisticsResp;
import com.arz.pmp.base.api.bo.user.front.CourseListData;
import com.arz.pmp.base.api.bo.user.front.UserPerfectData;
import com.arz.pmp.base.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpUserExMapper {

    List<UserDataResp> selectUserList(UserSearchReq search);

    PmpUserEntity selectUserByName(@Param("userName") String userName, @Param("identityNo") String identityNo,
                                   @Param("phoneNo") String phoneNo);

    List<PmpUserEducationEntity> selectEducationList(@Param("delOn") Boolean delOn);

    List<PmpUserPayTypeEntity> selectUserPayTypeList(@Param("delOn") Boolean delOn);

    List<PmpUserResourceTypeEntity> selectResourceTypeList(@Param("delOn") Boolean delOn);

    UserDataResp selectUserDetail(UserSearchReq search);

    List<UserDataResp> selectExportUserList(UserSearchReq search);

    UserPerfectData selectFrontUserData(@Param("userId") Long userId);

    List<Long> selectUserIds(@Param("userName") String userName, @Param("identityNo") String identityNo);

    Long selectUserRefCourseId(@Param("userId") Long userId, @Param("courseId") Long courseId);

    PmpUserRefCourseEntity selectUserRefCourse(@Param("userId") Long userId, @Param("courseId") Long courseId);

    List<CourseListData> selectUserCourseList(UserSearchReq search);

    PmpUserCourseApplyEntity selectUserCourseApply(@Param("userRefCourseId") Long userRefCourseId);

    PmpUserTouristsEntity selectUserTouristsByLoginName(@Param("loginName") String loginName);

    PmpUserEntity selectUserByLoginName(@Param("loginName") String loginName);

    void updateTouristsDelByUser(@Param("userId") Long userId);

    void deleteUserCourse(@Param("userId") Long userId, @Param("courseId") Long courseId,
                          @Param("curTime") Long curTime);

    UsersStatisticsResp selectUsersStatistics(@Param("todayTime") Long todayTime,
                                              @Param("salesAdminId") Long salesAdminId);

    int selectRoomChoosingCount(@Param("todayTime") Long todayTime, @Param("list") List<Long> roomId);
}