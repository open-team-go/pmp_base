package com.arz.pmp.base.api.bo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.entity.PmpTeachingRoomEntity;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.commons.utils.NumberUtil;
import lombok.Data;

import java.util.Date;

@Data
public class UserDataExport extends BaseRowModel {
    @ExcelProperty(index = 0, value = "用户表ID")
    private Long userId;

    @ExcelProperty("班级信息")
    private PmpTeachingRoomEntity roomInfo;

    @ExcelProperty("课程顾问信息")
    private PmpAdminEntity adminInfo;

    @ExcelProperty("学历")
    private String educationName;

    @ExcelProperty("付款方式")
    private String payName;

    @ExcelProperty("姓名")
    private String userName;

    @ExcelProperty("性别（0、女，1、男）")
    private Boolean gender;

    @ExcelProperty("民族")
    private String nationality;

    private Long birthday;

    @ExcelProperty("出生时间")
    private String birthdayStr;

    @ExcelProperty("联系电话")
    private String phoneNo;

    @ExcelProperty("联系邮箱")
    private String email;

    @ExcelProperty("备用邮箱")
    private String backupEmail;

    @ExcelProperty("微信号码")
    private String wechatNo;

    @ExcelProperty("QQ号码")
    private String qq;

    @ExcelProperty("身份证号码")
    private String identityNo;

    @ExcelProperty("邮编")
    private String zipCode;

    @ExcelProperty("通讯地址")
    private String userAddress;

    private Long graduationTime;

    @ExcelProperty("毕业时间")
    private String graduationTimeStr;

    @ExcelProperty("毕业学校")
    private String graduationSchool;

    @ExcelProperty("所学专业")
    private String schoolMajor;

    @ExcelProperty("从事行业")
    private String industry;

    @ExcelProperty("公司名称")
    private String comName;

    @ExcelProperty("公司部门名称")
    private String comDepartment;

    @ExcelProperty("公司职位名称")
    private String comPosition;

    @ExcelProperty("发票抬头")
    private String invoiceHeader;

    @ExcelProperty("发票税号")
    private String invoiceCode;

    @ExcelProperty("学员类型（1、内部学员，2、外部续证学员，3、联系中未报名）")
    private Integer userType;

    @ExcelProperty("课程名")
    private String courseName;

    @ExcelProperty("结业状态（0、未知，1、通过，2、未通过，3、缓考，4、缓读）")
    private Integer graduationStatus;

    @ExcelProperty("证书号")
    private String certNo;

    @ExcelProperty("证书用户名")
    private String certUserName;

    @ExcelProperty("证书密码")
    private String certPassword;

    @ExcelProperty("备注")
    private String remark;

    private Long createTime;

    @ExcelProperty("创建时间")
    private String createTimeStr;

    public String getBirthdayStr() {
        return DateUtil.getSecToStr(birthday, DateUtil.DateStrFormat.f_1);
    }

    public Object getGraduationTimeStr() {
        return DateUtil.getSecToStr(graduationTime, DateUtil.DateStrFormat.f_1);
    }

    public Object getCreateTimeStr() {
        return DateUtil.getSecToStr(createTime, DateUtil.DateStrFormat.f_1);
    }
}
