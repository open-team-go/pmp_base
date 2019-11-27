package com.arz.pmp.base.api.service.admin;

import com.arz.pmp.base.api.bo.adminn.AdminEditorReq;
import com.arz.pmp.base.api.bo.adminn.AdminLoginResp;
import com.arz.pmp.base.api.bo.adminn.AdminSearchReq;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description 管理员
 * 
 * @author chen wei
 * @date 2019/11/12
 */
public interface AdminService {

    AdminLoginResp goLogin(String userName, String password);

    void goLogOut(String authentication);

    PageInfo<List<AdminLoginResp>> getAdminListPage(RestRequest<AdminSearchReq> data);

    Long addOrUpAdmin(AdminEditorReq data, boolean addOn, String authentication);

    void deleteAdmin(Long adminId, String authentication);

    List<PmpAdminEntity> getAdminList(AdminSearchReq data);

    Long getRoleAdminId(String authentication, SysPermEnumClass.RoleEnum roleEnum);
}
