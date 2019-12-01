package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.service.permission.PermissionService;
import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.response.RestResponse;
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

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.LOG_READ;
import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.PERM_READ;

/**
 * description 功能权限操作
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/11 21:42
 */
@Api(value = "后端 功能权限操作API集", tags = "功能权限操作API集")
@RestController
@RequestMapping("/back/perm")
public class PermissionRestController {

    @Resource
    private PermissionService permissionService;

    @ApiOperation(value = "权限 列表查看", notes = "分页查看权限")
    @PostMapping("/index")
    @RequirePermissions({PERM_READ})
    public RestResponse<PageInfo<List<PmpPermissionEntity>>> getPermsList(@RequestBody @Valid RestRequest data) {

        PageInfo pageInfo = permissionService.getPermsListPage(data);

        return RestResponse.success(pageInfo);
    }

    @ApiOperation(value = "权限 所有功能权限", notes = "获取所有功能权限")
    @PostMapping("/all")
    @RequirePermissions({PERM_READ})
    public RestResponse<List<PmpPermissionEntity>> getPermsAll(@RequestBody @Valid RestRequest data) {

        List<PmpPermissionEntity> pageInfo = permissionService.getPermsAll();

        return RestResponse.success(pageInfo);
    }
}
