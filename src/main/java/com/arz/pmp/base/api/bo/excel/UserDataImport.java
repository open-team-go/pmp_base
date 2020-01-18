package com.arz.pmp.base.api.bo.excel;

import com.arz.pmp.base.api.aop.annotation.PayrollProperty;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDataImport {
    @PayrollProperty("咨询日期")
    private String consultationTimeStr;

    private Long consultationTime;

    public String getConsultationTimeStr() {
        if (consultationTimeStr != null || consultationTime == null) {
            return consultationTimeStr;
        }
        return DateUtil.getSecToStr(consultationTime, DateUtil.DateStrFormat.f_2);
    }

    public Long getConsultationTime() {
        if (consultationTime != null) {
            return consultationTime;
        }
        return DateUtil.getDateSecond(DateUtil.strToDate(consultationTimeStr, DateUtil.DateStrFormat.f_2));
    }

    @PayrollProperty("咨询城市")
    private String consultationCity;

    @PayrollProperty("顾问姓名")
    private String salesAdminName;

    private Long adminId;

    @PayrollProperty("来源")
    private String resourceName;

    private Long resourceId;

    @PayrollProperty("姓名")
    private String userName;

    @PayrollProperty("性别")
    private String genderStr;

    private Boolean gender;

    public String getGenderStr() {
        if (genderStr != null) {
            return genderStr;
        }
        return gender == null?null : gender ? "男" : "女";
    }

    public Boolean getGender() {
        if (gender != null) {
            return gender;
        }
        return "男".equals(genderStr) ? true : false;
    }

    @PayrollProperty("手机号码")
    private String phoneNo;

    @PayrollProperty("通讯地址")
    private String userAddress;

    @PayrollProperty("邮箱")
    private String email;

    @PayrollProperty("学历")
    private String educationName;

    private Long educationId;

    @PayrollProperty("专业")
    private String schoolMajor;

    @PayrollProperty("毕业时间")
    private String graduationTimeStr;

    @PayrollProperty("毕业院校")
    private String graduationSchool;

    private Long graduationTime;

    public String getGraduationTimeStr() {
        if (graduationTimeStr != null || graduationTime == null) {
            return graduationTimeStr;
        }
        return DateUtil.getSecToStr(graduationTime, DateUtil.DateStrFormat.f_2);
    }

    public Long getGraduationTime() {
        if (graduationTime != null) {
            return graduationTime;
        }
        return DateUtil.getDateSecond(DateUtil.strToDate(graduationTimeStr, DateUtil.DateStrFormat.f_2));
    }

    @PayrollProperty("公司")
    private String comName;

    @PayrollProperty("职位")
    private String comPosition;

    @PayrollProperty("报名课程")
    private String courseName;

    private Long courseId;

    @PayrollProperty("PMI ID号")

    private String certNo;

    @PayrollProperty("PMI英文网站用户名")
    private String certEnName;

    @PayrollProperty("PMI英文网站密码")
    private String certEnPasw;
    @PayrollProperty("PMI中文网站用户名")

    private String certCnName;

    @PayrollProperty("PMI中文网站密码")
    private String certCnPasw;

    @PayrollProperty("付款金额")
    private BigDecimal payTotal;

    @PayrollProperty("付款方式")
    private String payName;

    @PayrollProperty("支付备注")
    private String payRemarks;

    private Long payId;

    @PayrollProperty("是否含票")
    private String invoiceOnStr;

    private Boolean invoiceOn;

    public String getInvoiceOnStr() {
        if (invoiceOnStr != null) {
            return invoiceOnStr;
        }
        return invoiceOn != null && invoiceOn ? "是" : "否";
    }

    public Boolean getInvoiceOn() {
        if (invoiceOn != null) {
            return invoiceOn;
        }
        return "是".equals(invoiceOnStr) ? true : false;
    }

    @PayrollProperty("付款时间")
    private String payTimeStr;

    private Long payTime;

    public String getPayTimeStr() {
        if (payTimeStr != null || payTime == null) {
            return payTimeStr;
        }
        return DateUtil.getSecToStr(payTime, DateUtil.DateStrFormat.f_2);
    }

    public Long getPayTime() {
        if (payTime != null) {
            return payTime;
        }
        return DateUtil.getDateSecond(DateUtil.strToDate(payTimeStr, DateUtil.DateStrFormat.f_2));
    }

    @PayrollProperty("考试时间")
    private String examinationTimeStr;

    private Long examinationTime;

    public String getExaminationTimeStr() {
        if (examinationTimeStr != null || examinationTime == null) {
            return examinationTimeStr;
        }
        return DateUtil.getSecToStr(examinationTime, DateUtil.DateStrFormat.f_1);
    }

    public Long getExaminationTime() {
        if (examinationTime != null) {
            return examinationTime;
        }
        return DateUtil.getDateSecond(DateUtil.strToDate(examinationTimeStr, DateUtil.DateStrFormat.f_1));
    }

    @PayrollProperty("备注")
    private String remark;
}
