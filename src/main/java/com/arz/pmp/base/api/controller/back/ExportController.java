package com.arz.pmp.base.api.controller.back;

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.USER_EXPORT;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.glasnost.orika.MapperFacade;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arz.pmp.base.api.aop.PermAopHandle;
import com.arz.pmp.base.api.bo.excel.UserDataExport;
import com.arz.pmp.base.api.bo.user.UserSearchReq;
import com.arz.pmp.base.api.service.user.UserService;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import com.arz.pmp.base.framework.core.utils.WebUtil;

@Controller
@RequestMapping("/export")
public class ExportController {

    @Resource
    private UserService userService;

    @Autowired
    private PermAopHandle permAopHandle;

    @Autowired
    private MapperFacade mapperFacade;

    @GetMapping("/{authentication}/user")
    public void exportUser(@PathVariable("authentication") String authentication) throws IOException {
        permAopHandle.assertPermissions(authentication,
            permAopHandle.getPerms(null, new SysPermEnumClass.PermissionEnum[] {USER_EXPORT}), Logical.AND, false);
        HttpServletResponse response = WebUtil.getResponse();
        HttpServletRequest request = WebUtil.getRequest();
        Map<String,String> map = request.getTrailerFields();
        UserSearchReq search = mapperFacade.map(map,UserSearchReq.class);
        List<UserDataExport> list = userService.getExportUserList(search, authentication);
        WebUtil.sendExcelResponse(response, "pmp_user", list, UserDataExport.class);
    }
}
