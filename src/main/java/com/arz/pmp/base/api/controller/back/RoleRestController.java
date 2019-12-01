package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.bo.permission.RolePermsData;
import com.arz.pmp.base.api.service.permission.PermissionService;
import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.core.annotation.SysLog;
import com.arz.pmp.base.framework.core.enums.SysLogEnumClass;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.PLACE_READ;
import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.ROLE_READ;
import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.ROLE_UPDATE;

/**
 * description 角色操作
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/11 21:42
 */
@Api(value = "后端 角色操作API集", tags = "后端 角色操作API集")
@RestController
@RequestMapping("/back/role")
public class RoleRestController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "角色 列表查看", notes = "分页查看角色")
    @PostMapping("/index")
    @RequirePermissions({ROLE_READ})
    public RestResponse<PageInfo<List<PmpRoleEntity>>> getRoleList(@RequestBody @Valid RestRequest data) {

        PageInfo<List<PmpRoleEntity>> pageInfo = permissionService.getRolesListPage(data);

        return RestResponse.success(pageInfo);
    }

    @ApiOperation(value = "角色 所有角色", notes = "获取所有角色")
    @PostMapping("/all")
    @RequirePermissions({ROLE_READ})
    public RestResponse<List<PmpRoleEntity>> getRoleAll(@RequestBody @Valid RestRequest data) {

        List<PmpRoleEntity> list = permissionService.getRolesAll();

        return RestResponse.success(list);
    }

    @ApiOperation(value = "角色 拥有的功能权限", notes = "获取角色下功能权限")
    @PostMapping("/perms")
    @RequirePermissions({ROLE_READ})
    public RestResponse<List<PmpPermissionEntity>> getRolePerms(@RequestBody @Valid RestRequest<Long> data) {

        List<PmpPermissionEntity> list = permissionService.getPermListByRoleId(data.getBody());

        return RestResponse.success(list);
    }

    @ApiOperation(value = "角色 更新角色的功能权限", notes = "更新角色的功能权限")
    @PostMapping("/perms/update")
    @RequirePermissions({ROLE_UPDATE})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.UPDATE, module = SysLogEnumClass.OptionModuleEnum.SYS_ROLE,
        describe = "更新角色权限信息")
    public RestResponse updateRolePerms(@RequestBody @Valid RestRequest<RolePermsData> data) {

        permissionService.updateRolePerms(data);

        return RestResponse.success();
    }
}
