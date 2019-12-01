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

    @PayrollProperty("性别（0、女，1、男）")
    private Boolean gender;

    @PayrollProperty("民族")
    private String nationality;

    @PayrollProperty(value = "出生时间",format = "Date")
    private Long birthday;

    @PayrollProperty("联系电话")
    private String phoneNo;

    @PayrollProperty("联系邮箱")
    private String email;

    @PayrollProperty("备用邮箱")
    private String backupEmail;

    @PayrollProperty("微信号码")
    private String wechatNo;

    @PayrollProperty("QQ号码")
    private String qq;

    @PayrollProperty("身份证号码")
    private String identityNo;

    @PayrollProperty("邮编")
    private String zipCode;

    @PayrollProperty("通讯地址")
    private String userAddress;

    @PayrollProperty(value = "毕业时间",format = "Date")
    private Long graduationTime;

    @PayrollProperty("毕业学校")
    private String graduationSchool;

    @PayrollProperty("所学专业")
    private String schoolMajor;

    @PayrollProperty("从事行业")
    private String industry;

    @PayrollProperty("公司名称")
    private String comName;

    @PayrollProperty("公司部门名称")
    private String comDepartment;

    @PayrollProperty("公司职位名称")
    private String comPosition;

    @PayrollProperty("发票抬头")
    private String invoiceHeader;

    @PayrollProperty("发票税号")
    private String invoiceCode;

    @PayrollProperty("学员类型（1、内部学员，2、外部续证学员，3、联系中未报名）")
    private Integer userType;

    @PayrollProperty("课程名")
    private String courseName;

    @PayrollProperty("结业状态（0、未知，1、通过，2、未通过，3、缓考，4、缓读）")
    private Integer graduationStatus;

    @PayrollProperty("证书号")
    private String certNo;

    @PayrollProperty("证书用户名")
    private String certUserName;

    @PayrollProperty("证书密码")
    private String certPassword;

    @PayrollProperty("备注")
    private String remark;

    @PayrollProperty(value = "创建时间",format = "Date")
    private Long createTime;

}
