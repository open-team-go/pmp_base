package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.adminn.AdminLoginResp;
import com.arz.pmp.base.api.bo.adminn.AdminSearchReq;
import com.arz.pmp.base.entity.PmpAdminEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpAdminExMapper {

    PmpAdminEntity selectAdminByName(@Param("userName") String userName);

    List<AdminLoginResp> selectAdminInfoList(AdminSearchReq search);

    List<PmpAdminEntity> selectAdminList(AdminSearchReq search);

    List<PmpAdminEntity> selectAdminAll();
}