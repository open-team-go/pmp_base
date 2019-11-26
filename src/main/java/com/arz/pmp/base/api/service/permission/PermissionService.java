package com.arz.pmp.base.api.service.permission;

import com.arz.pmp.base.api.bo.permission.RolePermsData;
import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description 管理员
 * 
 * @author chen wei
 * @date 2019/11/12
 */
public interface PermissionService {

    PmpRoleEntity getRoleById(Long roleId);

    List<PmpPermissionEntity> getPermListByRoleId(Long roleId);

    PageInfo<List<PmpPermissionEntity>> getPermsListPage(RestRequest data);

    List<PmpPermissionEntity> getPermsAll();

    PageInfo<List<PmpRoleEntity>> getRolesListPage(RestRequest data);

    List<PmpRoleEntity> getRolesAll();

    void updateRolePerms(RestRequest<RolePermsData> req);
}
