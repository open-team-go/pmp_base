package com.arz.pmp.base.api.bo.excel;

import com.arz.pmp.base.api.aop.annotation.PayrollProperty;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.entity.PmpTeachingRoomEntity;

import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.commons.utils.Util;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserDataImport {
    @PayrollProperty("咨询日期")
    private String consultationTimeStr;

    private Long consultationTime;

    public  String getConsultationTimeStr(){
        if(consultationTimeStr!=null || consultationTime == null){
            return consultationTimeStr;
        }
        return DateUtil.getSecToStr(consultationTime,DateUtil.DateStrFormat.f_1);
    }
    public  Long getConsultationTime(){
        if(consultationTime!=null){
            return consultationTime;
        }
        return DateUtil.getDateSecond(DateUtil.strToDate(consultationTimeStr,DateUtil.DateStrFormat.f_1));
    }

    @PayrollProperty("咨询城市")
    private String consultationCity;

    @PayrollProperty("顾问名称")
    private String salesAdminName;

    @PayrollProperty("姓名")
    private String userName;

    @PayrollProperty("性别")
    private String genderStr;

    private Boolean gender;

    public  String getGenderStr(){
        if(genderStr!=null){
            return genderStr;
        }
        return gender!=null&&gender?"男":"女";
    }

    public  Boolean getGender(){
        if(gender!=null){
            return gender;
        }
        return "男".equals(genderStr)?true:false;
    }

    @PayrollProperty("手机号码")
    private String phoneNo;

    @PayrollProperty("通讯地址")
    private String address;

    @PayrollProperty("邮箱")
    private String email;

    @PayrollProperty("学历")
    private String educationName;

    @PayrollProperty("毕业学校")
    private String graduationSchool;

    @PayrollProperty("毕业时间")
    private String graduationTimeStr;

    private Long graduationTime;

    public  String getGraduationTimeStr(){
        if(graduationTimeStr!=null || graduationTime == null){
            return graduationTimeStr;
        }
        return DateUtil.getSecToStr(graduationTime,DateUtil.DateStrFormat.f_1);
    }
    public  Long getGraduationTime(){
        if(graduationTime!=null){
            return graduationTime;
        }
        return DateUtil.getDateSecond(DateUtil.strToDate(graduationTimeStr,DateUtil.DateStrFormat.f_1));
    }

    @PayrollProperty("公司")
    private String comName;

    @PayrollProperty("职务")
    private String comPosition;

    @PayrollProperty("课程名称")
    private String courseName;

    @PayrollProperty("PMI ID号")
    private String certNo;

    @PayrollProperty("PMI网站用户名")
    private String certEnName;

    @PayrollProperty("PMI网站密码")
    private String certEnPasw;

    @ApiModelProperty("付款金额")
    private BigDecimal payTotal;

    @ApiModelProperty("付款方式")
    private String payName;

    @ApiModelProperty("是否含票")
    private String invoiceOnStr;

    private Boolean invoiceOn;

    public  String getInvoiceOnStr(){
        if(invoiceOnStr!=null){
            return invoiceOnStr;
        }
        return invoiceOn!=null&&invoiceOn?"是":"否";
    }

    public  Boolean getInvoiceOn(){
        if(invoiceOn!=null){
            return invoiceOn;
        }
        return "是".equals(invoiceOnStr)?true:false;
    }

    @ApiModelProperty("付款时间")
    private String payTimeStr;

    private Long payTime;

    public  String getPayTimeStr(){
        if(payTimeStr!=null || payTime == null){
            return payTimeStr;
        }
        return DateUtil.getSecToStr(payTime,DateUtil.DateStrFormat.f_1);
    }
    public  Long getPayTime(){
        if(payTime!=null){
            return payTime;
        }
        return DateUtil.getDateSecond(DateUtil.strToDate(payTimeStr,DateUtil.DateStrFormat.f_1));
    }

    @ApiModelProperty("考试时间")
    private Long examinationTime;

    @ApiModelProperty("备注")
    private String remark;
}
