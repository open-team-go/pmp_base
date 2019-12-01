package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.bo.adminn.AdminLoginResp;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.api.bo.excel.UserImportResp;
import com.arz.pmp.base.api.service.excel.ExcelService;
import com.arz.pmp.base.api.service.user.UserService;
import com.arz.pmp.base.entity.PmpUserEntity;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "后端 文件导入", tags = "后端 文件导入API集")
@RequestMapping("/back/import")
public class ImportRestController {

    @Resource
    private ExcelService excelService;
    @Resource
    private UserService userService;


    @ApiOperation(value = "学员信息导入", notes = "学员信息导入")
    @PostMapping(value = "/user")
    public RestResponse<UserImportResp> importUser(@PathParam( value = "file") MultipartFile file) throws Exception {
        // 获取文件中数据
        List<UserDataImport> list = excelService.importUser(file);
        UserImportResp result = userService.insertUserBatch(list);
        return RestResponse.success(result);
    }
}
