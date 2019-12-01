package com.arz.pmp.base.api.controller.back;

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.*;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.arz.pmp.base.api.bo.CommonDataReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.bo.adminn.AdminEditorReq;
import com.arz.pmp.base.api.bo.adminn.AdminLoginReq;
import com.arz.pmp.base.api.bo.adminn.AdminLoginResp;
import com.arz.pmp.base.api.bo.adminn.AdminSearchReq;
import com.arz.pmp.base.api.service.admin.AdminService;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.core.annotation.SysLog;
import com.arz.pmp.base.framework.core.enums.SysLogEnumClass;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * description 管理员操作
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/11 21:42
 */
@Api(value = "后端 管理员操作API集", tags = "后端 管理员操作API集")
@RestController
@RequestMapping("/back/admin")
public class AdminRestController {

    @Resource
    private AdminService adminService;

    @ApiOperation(value = "管理员 登录", notes = "登录")
    @PostMapping("/login")
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.QUERY, module = SysLogEnumClass.OptionModuleEnum.SYS_LOGIN,
        describe = "管理员登录")
    public RestResponse<AdminLoginResp> goLogin(@RequestBody @Valid RestRequest<AdminLoginReq> data) {

        Assert.isTrue(data.getBody() != null, CommonCodeEnum.PARAM_ERROR);
        AdminLoginResp resp = adminService.goLogin(data.getBody().getUserName(), data.getBody().getPassword());

        return RestResponse.success(resp);
    }

    @ApiOperation(value = "管理员 登出", notes = "登出")
    @PostMapping("/login/out")
    public RestResponse goLogOut(@RequestBody @Valid RestRequest data) {

        Assert.isTrue(data.getBody() != null, CommonCodeEnum.PARAM_ERROR);
        adminService.goLogOut(data.getHeader().getAuthentication());

        return RestResponse.success();
    }

    @ApiOperation(value = "管理员 列表查看", notes = "分页查看管理员")
    @PostMapping("/index")
    @RequirePermissions({ADMIN_READ})
    public RestResponse<PageInfo<List<AdminLoginResp>>>
        getAdminList(@RequestBody @Valid RestRequest<AdminSearchReq> data) {

        PageInfo<List<AdminLoginResp>> pageInfo = adminService.getAdminListPage(data);
        return RestResponse.success(pageInfo);
    }

    @ApiOperation(value = "管理员 添加", notes = "添加新管理员")
    @PostMapping("/add")
    @RequirePermissions({ADMIN_ADD})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.ADD, module = SysLogEnumClass.OptionModuleEnum.SYS_ADMIN,
        describe = "添加新管理员")
    public RestResponse<Long> addAdmin(@RequestBody @Valid RestRequest<AdminEditorReq> data) {

        Long adminId = adminService.addOrUpAdmin(data.getBody(), true, data.getHeader().getAuthentication());

        return RestResponse.success(adminId);
    }

    @ApiOperation(value = "管理员 更新", notes = "修改管理员信息")
    @PostMapping("/update")
    @RequirePermissions({ADMIN_UPDATE})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.UPDATE, module = SysLogEnumClass.OptionModuleEnum.SYS_ADMIN,
        describe = "修改管理员信息")
    public RestResponse<Long> updateAdmin(@RequestBody @Valid RestRequest<AdminEditorReq> data) {

        Long adminId = adminService.addOrUpAdmin(data.getBody(), false, data.getHeader().getAuthentication());

        return RestResponse.success(adminId);
    }

    @ApiOperation(value = "管理员 删除", notes = "删除管理员信息")
    @PostMapping("/delete")
    @RequirePermissions({ADMIN_DEL})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.DELETE, module = SysLogEnumClass.OptionModuleEnum.SYS_ADMIN,
        describe = "删除管理员信息")
    public RestResponse deleteAdmin(@RequestBody @Valid RestRequest<CommonDataReq> data) {

        adminService.deleteAdmin(data.getBody().getId(), data.getHeader().getAuthentication());

        return RestResponse.success();
    }

    @ApiOperation(value = "管理员 按条件获取", notes = "按条件获取管理员不分页")
    @PostMapping("/search")
    public RestResponse<List<PmpAdminEntity>> getAdmins(@RequestBody @Valid RestRequest<AdminSearchReq> data) {

        List<PmpAdminEntity> list = adminService.getAdminList(data.getBody());

        return RestResponse.success(list);
    }

}
