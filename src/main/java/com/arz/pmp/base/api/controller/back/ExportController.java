package com.arz.pmp.base.api.controller.back;

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.USER_EXPORT;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.arz.pmp.base.framework.commons.utils.NumberUtil;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import com.arz.pmp.base.framework.core.utils.WebUtil;

@Controller
@RequestMapping("/export")
public class ExportController {

    @Resource
    private UserService userService;

    @Autowired
    private PermAopHandle permAopHandle;

    @GetMapping("/{authentication}/user")
    public void exportUser(@PathVariable("authentication") String authentication) throws IOException {
        permAopHandle.assertPermissions(authentication,
            permAopHandle.getPerms(null, new SysPermEnumClass.PermissionEnum[] {USER_EXPORT}), Logical.AND, false);
        HttpServletResponse response = WebUtil.getResponse();
        HttpServletRequest request = WebUtil.getRequest();
        UserSearchReq search = new UserSearchReq();
        String keyword = request.getParameter("keyWord");
        search.setKeyWord(keyword);
        Long courseId = NumberUtil.typeChange(request.getParameter("courseId"), Long.class);
        search.setCourseId(courseId);
        Long educationAdminId = NumberUtil.typeChange(request.getParameter("educationAdminId"), Long.class);
        search.setEducationAdminId(educationAdminId);
        Long endTime = NumberUtil.typeChange(request.getParameter("endTime"), Long.class);
        search.setEndTime(endTime);
        Long placeId = NumberUtil.typeChange(request.getParameter("placeId"), Long.class);
        search.setPlaceId(placeId);
        Long roomId = NumberUtil.typeChange(request.getParameter("roomId"), Long.class);
        search.setRoomId(roomId);
        Long salesAdminId = NumberUtil.typeChange(request.getParameter("salesAdminId"), Long.class);
        search.setSalesAdminId(salesAdminId);
        Long startTime = NumberUtil.typeChange(request.getParameter("startTime"), Long.class);
        search.setStartTime(startTime);
        Integer userType = NumberUtil.typeChange(request.getParameter("userType"), Integer.class);
        search.setUserType(userType);
        String invoiceOn = request.getParameter("invoiceOn");
        if (StringUtils.isNotBlank(invoiceOn)) {
            search.setInvoiceOn(Boolean.valueOf(invoiceOn));
        }
        String certNo = request.getParameter("invoiceOn");
        search.setCertNo(certNo);
        String comPosition = request.getParameter("comPosition");
        search.setComPosition(comPosition);
        String comName = request.getParameter("comName");
        search.setComName(comName);
        Double payTotal = NumberUtil.typeChange(request.getParameter("payTotal"), Double.class);
        search.setPayTotal(payTotal);
        List<UserDataExport> list = userService.getExportUserList(search, authentication);
        WebUtil.sendExcelResponse(response, "pmp_user", list, UserDataExport.class);
    }

}
