package com.arz.pmp.base.entity;

import java.math.BigDecimal;

public class PmpUserEntity {
    private Long userId;

    private Long educationId;

    private Long payId;

    private Long roomId;

    private Long resourceId;

    private String recommendName;

    private String userName;

    private Boolean gender;

    private String nationality;

    private Long birthday;

    private String phoneNo;

    private String fixedTelephone;

    private String email;

    private String backupEmail;

    private String wechatNo;

    private String qq;

    private String identityNo;

    private String zipCode;

    private String userAddress;

    private Long graduationTime;

    private String graduationSchool;

    private String schoolMajor;

    private String industry;

    private String comName;

    private String comDepartment;

    private String comPosition;

    private Boolean invoiceOn;

    private String invoiceHeader;

    private String invoiceCode;

    private Integer userType;

    private Long adminId;

    private Long courseId;

    private Integer graduationStatus;

    private String certNo;

    private String certEnName;

    private String certEnPasw;

    private String certCnName;

    private String certCnPasw;

    private Long certStartTime;

    private Long certEndTime;

    private String remark;

    private String examName;

    private String institution;

    private String examPlace;

    private Boolean easeOut;

    private Boolean easeIn;

    private Boolean withdrawOn;

    private Boolean auditOn;

    private Integer receiveType;

    private Boolean reExamineOn;

    private Long examinationTime;

    private BigDecimal payTotal;

    private Long payTime;

    private String payRemarks;

    private Long consultationTime;

    private String consultationCity;

    private Long createTime;

    private Long updateTime;

    private Long createManager;

    private Long updateManager;

    private Boolean delOn;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName == null ? null : recommendName.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getFixedTelephone() {
        return fixedTelephone;
    }

    public void setFixedTelephone(String fixedTelephone) {
        this.fixedTelephone = fixedTelephone == null ? null : fixedTelephone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBackupEmail() {
        return backupEmail;
    }

    public void setBackupEmail(String backupEmail) {
        this.backupEmail = backupEmail == null ? null : backupEmail.trim();
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo == null ? null : identityNo.trim();
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode == null ? null : zipCode.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Long getGraduationTime() {
        return graduationTime;
    }

    public void setGraduationTime(Long graduationTime) {
        this.graduationTime = graduationTime;
    }

    public String getGraduationSchool() {
        return graduationSchool;
    }

    public void setGraduationSchool(String graduationSchool) {
        this.graduationSchool = graduationSchool == null ? null : graduationSchool.trim();
    }

    public String getSchoolMajor() {
        return schoolMajor;
    }

    public void setSchoolMajor(String schoolMajor) {
        this.schoolMajor = schoolMajor == null ? null : schoolMajor.trim();
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry == null ? null : industry.trim();
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName == null ? null : comName.trim();
    }

    public String getComDepartment() {
        return comDepartment;
    }

    public void setComDepartment(String comDepartment) {
        this.comDepartment = comDepartment == null ? null : comDepartment.trim();
    }

    public String getComPosition() {
        return comPosition;
    }

    public void setComPosition(String comPosition) {
        this.comPosition = comPosition == null ? null : comPosition.trim();
    }

    public Boolean getInvoiceOn() {
        return invoiceOn;
    }

    public void setInvoiceOn(Boolean invoiceOn) {
        this.invoiceOn = invoiceOn;
    }

    public String getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(String invoiceHeader) {
        this.invoiceHeader = invoiceHeader == null ? null : invoiceHeader.trim();
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode == null ? null : invoiceCode.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getGraduationStatus() {
        return graduationStatus;
    }

    public void setGraduationStatus(Integer graduationStatus) {
        this.graduationStatus = graduationStatus;
    }

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo == null ? null : certNo.trim();
    }

    public String getCertEnName() {
        return certEnName;
    }

    public void setCertEnName(String certEnName) {
        this.certEnName = certEnName == null ? null : certEnName.trim();
    }

    public String getCertEnPasw() {
        return certEnPasw;
    }

    public void setCertEnPasw(String certEnPasw) {
        this.certEnPasw = certEnPasw == null ? null : certEnPasw.trim();
    }

    public String getCertCnName() {
        return certCnName;
    }

    public void setCertCnName(String certCnName) {
        this.certCnName = certCnName == null ? null : certCnName.trim();
    }

    public String getCertCnPasw() {
        return certCnPasw;
    }

    public void setCertCnPasw(String certCnPasw) {
        this.certCnPasw = certCnPasw == null ? null : certCnPasw.trim();
    }

    public Long getCertStartTime() {
        return certStartTime;
    }

    public void setCertStartTime(Long certStartTime) {
        this.certStartTime = certStartTime;
    }

    public Long getCertEndTime() {
        return certEndTime;
    }

    public void setCertEndTime(Long certEndTime) {
        this.certEndTime = certEndTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName == null ? null : examName.trim();
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution == null ? null : institution.trim();
    }

    public String getExamPlace() {
        return examPlace;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace == null ? null : examPlace.trim();
    }

    public Boolean getEaseOut() {
        return easeOut;
    }

    public void setEaseOut(Boolean easeOut) {
        this.easeOut = easeOut;
    }

    public Boolean getEaseIn() {
        return easeIn;
    }

    public void setEaseIn(Boolean easeIn) {
        this.easeIn = easeIn;
    }

    public Boolean getWithdrawOn() {
        return withdrawOn;
    }

    public void setWithdrawOn(Boolean withdrawOn) {
        this.withdrawOn = withdrawOn;
    }

    public Boolean getAuditOn() {
        return auditOn;
    }

    public void setAuditOn(Boolean auditOn) {
        this.auditOn = auditOn;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Boolean getReExamineOn() {
        return reExamineOn;
    }

    public void setReExamineOn(Boolean reExamineOn) {
        this.reExamineOn = reExamineOn;
    }

    public Long getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(Long examinationTime) {
        this.examinationTime = examinationTime;
    }

    public BigDecimal getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(BigDecimal payTotal) {
        this.payTotal = payTotal;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public String getPayRemarks() {
        return payRemarks;
    }

    public void setPayRemarks(String payRemarks) {
        this.payRemarks = payRemarks == null ? null : payRemarks.trim();
    }

    public Long getConsultationTime() {
        return consultationTime;
    }

    public void setConsultationTime(Long consultationTime) {
        this.consultationTime = consultationTime;
    }

    public String getConsultationCity() {
        return consultationCity;
    }

    public void setConsultationCity(String consultationCity) {
        this.consultationCity = consultationCity == null ? null : consultationCity.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateManager() {
        return createManager;
    }

    public void setCreateManager(Long createManager) {
        this.createManager = createManager;
    }

    public Long getUpdateManager() {
        return updateManager;
    }

    public void setUpdateManager(Long updateManager) {
        this.updateManager = updateManager;
    }

    public Boolean getDelOn() {
        return delOn;
    }

    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
    }
}