package com.arz.pmp.base.entity;

public class PmpUserCourseApplyEntity {
    private Long id;

    private Long userRefCourseId;

    private Long userId;

    private String htmlContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserRefCourseId() {
        return userRefCourseId;
    }

    public void setUserRefCourseId(Long userRefCourseId) {
        this.userRefCourseId = userRefCourseId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent == null ? null : htmlContent.trim();
    }
}