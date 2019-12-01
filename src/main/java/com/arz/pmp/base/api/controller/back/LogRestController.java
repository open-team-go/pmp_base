package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.bo.log.LogDataResp;
import com.arz.pmp.base.api.bo.log.LogSearchReq;
import com.arz.pmp.base.api.service.log.LogService;
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

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.ADMIN_READ;
import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.LOG_READ;
import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.PERM_READ;

/**
 * description 日志操作
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/26 21:42
 */
@Api(value = "后端 日志操作API集", tags = "日志操作API集")
@RestController
@RequestMapping("/back/log")
public class LogRestController {

    @Resource
    private LogService logService;

    @ApiOperation(value = "日志 列表查看", notes = "分页查看日志")
    @PostMapping("/index")
    @RequirePermissions({LOG_READ})
    public RestResponse<PageInfo<List<LogDataResp>>> getPermsList(@RequestBody @Valid RestRequest<LogSearchReq> data) {

        PageInfo pageInfo = logService.getLogListPage(data);

        return RestResponse.success(pageInfo);
    }

}
