package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.bo.user.front.UserPerfectData;
import com.arz.pmp.base.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpUserExMapper {

    List<UserDataResp> selectUserList(UserSearchReq search);

    Long selectUserByName(@Param("userName") String userName, @Param("identityNo") String identityNo,
                          @Param("courseId") Long courseId,@Param("roomId") Long roomId);

    List<PmpUserEducationEntity> selectEducationList();

    List<PmpUserPayTypeEntity> selectUserPayTypeList();

    List<PmpUserResourceTypeEntity> selectResourceTypeList();

    UserDataResp selectUserDetail(@Param("userId") Long userId, @Param("phoneNo") String phoneNo,
        @Param("keyWord") String keyWord, @Param("userName") String userName, @Param("identityNo") String identityNo);

    List<UserDataResp> selectExportUserList(UserSearchReq search);

    UserPerfectData selectFrontUserData(@Param("userName") String userName, @Param("identityNo") String identityNo);

    List<Long> selectUserIds(@Param("userName") String userName, @Param("identityNo") String identityNo);

}