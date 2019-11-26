package com.arz.pmp.base.framework.commons.utils;

import com.google.common.base.Strings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author
 */
public class DateUtil {
    public final static String YYYYMMDD = "yyyyMMdd";

    public final static String YYYYMMDDHH24MISS = "yyyyMMddHHmmss";

    public final static String HH24MISS = "HHmmss";

    /**
     * 日期格式
     */
    public static final String DATE = "yyyy-MM-dd";

    /**
     * 日期时间格式
     */
    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期时间格式 精确到分
     */
    public static final String DATETIMEMIN = "yyyy-MM-dd HH:mm";

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
     * 获取当前时间 - 秒
     *
     * @return
     */
    public static long getCurIntegerDate() {
        return Long.valueOf((System.currentTimeMillis() / 1000)).intValue();
    }


    /**
     * 获取年
     *
     * @param yDate
     * @return
     */
    public static int yearOfDate(Date yDate) {
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(yDate);
        int year = aCalendar.get(Calendar.YEAR);
        return year;
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

    public static void main(String[] args){
//        System.out.println(DateUtil.strToDate("1993-09-08","yyyy-MM-dd").getTime()/1000);
        System.out.println(getCurSecond());
    }


}