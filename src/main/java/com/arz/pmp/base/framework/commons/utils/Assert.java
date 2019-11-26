package com.arz.pmp.base.framework.commons.utils;

import com.arz.pmp.base.framework.commons.enums.StatusCode;
import com.arz.pmp.base.framework.commons.exception.builder.ExceptionBuilder;

import java.util.Objects;

/**
 * description 断言,使用需要注意，断言正确情况下不报错，条件不满足时候才报错
 * 
 * @author chen wei
 * @date 2019/11/11
 */
public class Assert {

    private Assert() {}

    public static void isNotNull(Object object, StatusCode statusCode) {

        if (Util.isEmpty(object)) {
            throw ExceptionBuilder.build(statusCode);
        }
    }

    public static void isNotNull(Object object, StatusCode statusCode, String msg) {

        if (Util.isEmpty(object)) {
            throw ExceptionBuilder.build(statusCode, msg);
        }
    }

    public static void isActive(String activeFlag, StatusCode statusCode) {
        if (!"1".equals(activeFlag)) {
            throw ExceptionBuilder.build(statusCode);
        }
    }

    public static void isActive(String activeFlag, StatusCode statusCode, String msg) {
        if (!"1".equals(activeFlag)) {
            throw ExceptionBuilder.build(statusCode, msg);
        }
    }

    public static void equals(String str1, String str2, StatusCode statusCode) {
        if (!Objects.equals(str1, str2)) {
            throw ExceptionBuilder.build(statusCode);
        }
    }

    public static void equals(String str1, String str2, StatusCode statusCode, String msg) {
        if (!Objects.equals(str1, str2)) {
            throw ExceptionBuilder.build(statusCode, msg);
        }
    }

    public static void isTrue(boolean b, StatusCode statusCode) {
        if (!b) {
            throw ExceptionBuilder.build(statusCode);
        }

    }

    public static void isTrue(boolean b, StatusCode statusCode, String msg) {
        if (!b) {
            throw ExceptionBuilder.build(statusCode, msg);
        }

    }

}
