package com.arz.pmp.base.api.bo.excel;

import java.math.BigDecimal;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.arz.pmp.base.api.aop.annotation.PayrollProperty;
import com.arz.pmp.base.framework.commons.utils.DateUtil;

import lombok.Data;

@Data
public class UserDataExport extends BaseRowModel {

    
    @ExcelProperty("咨询日期")
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

    
    @ExcelProperty("咨询城市")
    private String consultationCity;

    
    @ExcelProperty("顾问姓名")
    private String salesAdminName;

    private Long adminId;

    
    @ExcelProperty("来源")
    private String resourceName;

    private Long resourceId;

    
    @ExcelProperty("姓名")
    private String userName;

    
    @ExcelProperty("性别")
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

    
    @ExcelProperty("手机号码")
    private String phoneNo;

    
    @ExcelProperty("通讯地址")
    private String userAddress;

    
    @ExcelProperty("邮箱")
    private String email;

    
    @ExcelProperty("学历")
    private String educationName;

    private Long educationId;

    
    @ExcelProperty("专业")
    private String schoolMajor;

    
    @ExcelProperty("毕业时间")
    private String graduationTimeStr;

    
    @ExcelProperty("毕业院校")
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

    
    @ExcelProperty("公司")
    private String comName;

    
    @ExcelProperty("职位")
    private String comPosition;

    
    @ExcelProperty("报名课程")
    private String courseName;

    private Long courseId;

    @ExcelProperty("班级名称")
    private String roomName;

    private Long roomId;

    @ExcelProperty("PMI ID号")
    private String certNo;

    
    @ExcelProperty("PMI英文网站用户名")
    private String certEnName;

    
    @ExcelProperty("PMI英文网站密码")
    private String certEnPasw;

    
    @ExcelProperty("PMI中文网站用户名")
    private String certCnName;

    
    @ExcelProperty("PMI中文网站密码")
    private String certCnPasw;

    
    @ExcelProperty("付款金额")
    private BigDecimal payTotal;

    
    @ExcelProperty("付款方式")
    private String payName;

    private Long payId;

    @ExcelProperty("支付备注")
    private String payRemarks;
    
    @ExcelProperty("是否含票")
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

    
    @ExcelProperty("付款时间")
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

    
    @ExcelProperty("考试时间")
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

    
    @ExcelProperty("备注")
    private String remark;

}
