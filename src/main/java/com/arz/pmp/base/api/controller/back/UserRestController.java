package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.UserEditorReq;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.service.user.UserService;
import com.arz.pmp.base.entity.PmpUserEducationEntity;
import com.arz.pmp.base.entity.PmpUserPayTypeEntity;
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

/**
 * description 学员操作
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/11 21:42
 */
@Api(value = "后端 学员操作API集", tags = "后端 学员操作API集")
@RestController
@RequestMapping("/back/user")
public class UserRestController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "学员 列表查看", notes = "分页查看学员")
    @PostMapping("/index")
    @RequiresPermissions({"user:read"})
    public RestResponse<PageInfo<List<UserDataResp>>>
        getUserListPage(@RequestBody @Valid RestRequest<UserSearchReq> data) {

        PageInfo pageInfo = userService.getUserListPage(data);

        return RestResponse.success(pageInfo);
    }

    @ApiOperation(value = "学员 学员添加", notes = "添加新的学员")
    @PostMapping("/add")
    @RequiresPermissions({"user:add"})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.ADD, module = SysLogEnumClass.OptionModuleEnum.SYS_USER,
        describe = "添加学员信息")
    public RestResponse<Long> addUser(@RequestBody @Valid RestRequest<UserEditorReq> data) {

        Long id = userService.addOrUpUser(data.getBody(), true, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "学员 学员更新", notes = "更新学员")
    @PostMapping("/update")
    @RequiresPermissions({"user:update"})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.UPDATE, module = SysLogEnumClass.OptionModuleEnum.SYS_USER,
        describe = "更新学员信息")
    public RestResponse<Long> updateUser(@RequestBody @Valid RestRequest<UserEditorReq> data) {

        Long id = userService.addOrUpUser(data.getBody(), false, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "学员 学员删除", notes = "学员删除")
    @PostMapping("/delete")
    @RequiresPermissions({"user:delete"})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.DELETE, module = SysLogEnumClass.OptionModuleEnum.SYS_USER,
        describe = "删除学员信息")
    public RestResponse deleteUser(@RequestBody @Valid RestRequest<Long> data) {

        userService.deleteUser(data.getBody(), data.getHeader().getAuthentication());

        return RestResponse.success();
    }

    @ApiOperation(value = "学员学历 所有学员学历", notes = "获取所有学员学历")
    @PostMapping("/education")
    public RestResponse<List<PmpUserEducationEntity>> getUserEducation(@RequestBody @Valid RestRequest data) {

        List<PmpUserEducationEntity> list = userService.getEducationList();

        return RestResponse.success(list);
    }

    @ApiOperation(value = "学员支付方式 所有学员支付方式", notes = "获取所有学员支付方式")
    @PostMapping("/payType")
    public RestResponse<List<PmpUserPayTypeEntity>> getUserPayType(@RequestBody @Valid RestRequest data) {

        List<PmpUserPayTypeEntity> list = userService.getPayTypeList();

        return RestResponse.success(list);
    }
}
