package com.arz.pmp.base.entity;

public class PmpUserTouristsEntity {
    private Long id;

    private String loginName;

    private String loginPassword;

    private String loginSalt;

    private Long userId;

    private Boolean perfectOn;

    private Long createTime;

    private Boolean delOn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    public String getLoginSalt() {
        return loginSalt;
    }

    public void setLoginSalt(String loginSalt) {
        this.loginSalt = loginSalt == null ? null : loginSalt.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getPerfectOn() {
        return perfectOn;
    }

    public void setPerfectOn(Boolean perfectOn) {
        this.perfectOn = perfectOn;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelOn() {
        return delOn;
    }

    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
    }
}