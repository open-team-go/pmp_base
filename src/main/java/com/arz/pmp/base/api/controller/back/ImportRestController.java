package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.aop.PermAopHandle;
import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.bo.adminn.AdminLoginResp;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.api.bo.excel.UserImportResp;
import com.arz.pmp.base.api.service.excel.ExcelService;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.api.service.user.UserService;
import com.arz.pmp.base.entity.PmpUserEntity;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.USER_EXPORT;
import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.USER_IMPORT;

@RestController
@Api(value = "后端 文件导入", tags = "后端 文件导入API集")
@RequestMapping("/import")
public class ImportRestController {

    @Resource
    private ExcelService excelService;
    @Resource
    private UserService userService;
    @Resource
    private RedisService redisService;

    @Autowired
    private PermAopHandle permAopHandle;

    @ApiOperation(value = "学员信息导入", notes = "学员信息导入")
    @PostMapping(value = "/{authentication}/user")
    public RestResponse<UserImportResp> importUser(@PathParam(value = "file") MultipartFile file,
        @PathVariable("authentication") String authentication) throws Exception {
        permAopHandle.assertPermissions(authentication,
            permAopHandle.getPerms(null, new SysPermEnumClass.PermissionEnum[] {USER_IMPORT}), Logical.AND, false);
        // 获取文件中数据
        List<UserDataImport> list = excelService.importUser(file);
        Long managerId = redisService.getOperatorIdByToken(authentication);
        UserImportResp result = userService.insertUserBatch(list,managerId);
        return RestResponse.success(result);
    }
}
