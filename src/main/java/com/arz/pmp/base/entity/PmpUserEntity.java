package com.arz.pmp.base.entity;

public class PmpUserEntity {
    private Long userId;

    private Long educationId;

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

    private Integer userType;

    private String institution;

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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution == null ? null : institution.trim();
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