package com.arz.pmp.base.framework.commons.utils;

import com.google.common.base.Strings;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author
 */
public class DateUtil {

    /**
     * 时间串转化为Date
     *
     * @param dateStr
     *            dateFormat时间格式的字符串
     * @param dateFormat
     *            时间格式
     * @return Date
     */
    public static Date strToDate(String dateStr, String dateFormat) {
        if (Strings.isNullOrEmpty(dateStr)) {
            return null;
        }

        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            return df.parse(dateStr);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Date转化为dateFormat时间格式的字符串
     *
     * @param date
     *            Date
     * @param dateFormat
     *            时间格式
     * @return dateFormat时间格式的字符串
     */
    public static String dateToStr(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    /**
     * 返回指定格式的当前日期时间字符串
     *
     * @param format
     * @return
     */
    public static String getDateTimeStr(String format) {
        String returnStr = null;
        SimpleDateFormat f = new SimpleDateFormat(format);
        Date date = new Date();
        returnStr = f.format(date);
        return returnStr;
    }

    /**
     * 获取当前时间戮
     *
     * @return
     */
    public static long getCurLongDate() {
        return System.currentTimeMillis();
    }

    /**
     * description 获取当前时间date
     *
     * @author chen wei
     * @date 2019/7/17
     * @return java.util.Date
     */
    public static Date getCurDateTime() {

        return new Date();
    }

    /**
     * description 获取当前时间秒
     * 
     * @author chen wei
     * @date 2019/7/17
     * @return java.util.Date
     */
    public static long getCurSecond() {

        return getCurDateTime().getTime() / 1000;
    }
    /**
     * description 获取当前时间秒
     *
     * @author chen wei
     * @date 2019/12/1
     * @return java.util.Date
     */
    public static Long getDateSecond(Date d) {
        if(d == null){
            return null;
        }
        return d.getTime() / 1000;
    }

    public interface DateStrFormat {
        /** YYYY-MM-dd HH:mm:ss */
        String f_1 = "YYYY-MM-dd HH:mm:ss";
        /** YYYY-MM-dds */
        String f_2 = "YYYY-MM-dd";
    }

    public static String getSecToStr(Long second, String format) {
        if (!NumberUtil.isPositive(second)) {
            return null;
        }

        return dateToStr(new Date(second * 1000), format);
    }
}