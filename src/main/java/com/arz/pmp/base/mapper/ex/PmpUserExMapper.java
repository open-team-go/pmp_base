package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.entity.PmpUserEducationEntity;
import com.arz.pmp.base.entity.PmpUserEntity;
import com.arz.pmp.base.entity.PmpUserPayTypeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpUserExMapper {

    List<UserDataResp> selectUserList(UserSearchReq search);

    Long selectUserByName(@Param("userName") String userName, @Param("identityNo") String identityNo);

    List<PmpUserEducationEntity> selectEducationList();

    List<PmpUserPayTypeEntity> selectUserPayTypeList();

    UserDataResp selectUserDetail(@Param("userId") Long userId, @Param("phoneNo") String phoneNo,
        @Param("keyWord") String keyWord,@Param("userName") String userName,
                                  @Param("identityNo") String identityNo);

    List<UserDataResp> selectExportUserList(UserSearchReq search);

}