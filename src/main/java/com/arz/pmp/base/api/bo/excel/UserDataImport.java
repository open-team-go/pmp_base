package com.arz.pmp.base.api.bo.excel;

import com.arz.pmp.base.api.aop.annotation.PayrollProperty;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.entity.PmpTeachingRoomEntity;

import lombok.Data;

@Data
public class UserDataImport {
    public static final String dateFormat ="Date";

    @PayrollProperty("班级信息")
    private PmpTeachingRoomEntity roomInfo;

    @PayrollProperty("课程顾问信息")
    private PmpAdminEntity adminInfo;

    @PayrollProperty("学历")
    private String educationName;

    @PayrollProperty("付款方式")
    private String payName;

    @PayrollProperty("姓名")
    private String userName;

    @PayrollProperty("考试名称")
    private String examName;

    @PayrollProperty("从事行业")
    private String industry;

    @PayrollProperty("现在单位")
    private String comName;

    @PayrollProperty("公司部门名称")
    private String comDepartment;

    @PayrollProperty("职务")
    private String comPosition;

    @PayrollProperty("手机号码")
    private String phoneNo;

    @PayrollProperty("固定电话")
    private String fixedTelephone;

    @PayrollProperty("Email")
    private String email;

    @PayrollProperty("备用Email")
    private String backupEmail;

    @PayrollProperty("PMI网站用户名")
    private String certEnName;

    @PayrollProperty("PMI网站密码")
    private String certEnPasw;

}
