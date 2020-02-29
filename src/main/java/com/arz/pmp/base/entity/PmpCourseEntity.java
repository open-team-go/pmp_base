package com.arz.pmp.base.entity;

public class PmpCourseEntity {
    private Long courseId;

    private String courseName;

    private String courseDesc;

    private Integer sort;

    private Boolean useOn;

    private String applyUrl;

    private Boolean delOn;

    private Long createTime;

    private Long updateTime;

    private Long createManager;

    private Long updateManager;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc == null ? null : courseDesc.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getUseOn() {
        return useOn;
    }

    public void setUseOn(Boolean useOn) {
        this.useOn = useOn;
    }

    public String getApplyUrl() {
        return applyUrl;
    }

    public void setApplyUrl(String applyUrl) {
        this.applyUrl = applyUrl == null ? null : applyUrl.trim();
    }

    public Boolean getDelOn() {
        return delOn;
    }

    public void setDelOn(Boolean delOn) {
        this.delOn = delOn;
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
}