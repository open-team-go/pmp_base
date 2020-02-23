package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.bo.user.front.UserPerfectData;
import com.arz.pmp.base.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpUserExMapper {

    List<UserDataResp> selectUserList(UserSearchReq search);

    PmpUserEntity selectUserByName(@Param("userName") String userName, @Param("identityNo") String identityNo,@Param("phoneNo") String phoneNo);

    List<PmpUserEducationEntity> selectEducationList(@Param("delOn") Boolean delOn);

    List<PmpUserPayTypeEntity> selectUserPayTypeList(@Param("delOn") Boolean delOn);

    List<PmpUserResourceTypeEntity> selectResourceTypeList(@Param("delOn") Boolean delOn);

    UserDataResp selectUserDetail(@Param("userId") Long userId);

    List<UserDataResp> selectExportUserList(UserSearchReq search);

    UserPerfectData selectFrontUserData(@Param("userName") String userName, @Param("identityNo") String identityNo);

    List<Long> selectUserIds(@Param("userName") String userName, @Param("identityNo") String identityNo);

}