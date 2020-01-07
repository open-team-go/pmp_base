package com.arz.pmp.base.framework.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * description 数字处理工具
 * 
 * @author chen wei
 * @date 2019/11/11
 */
public class NumberUtil {

    private NumberUtil() {}

    /**
     * 默认除法运算精度
     */
    private static final int SCALE_DIV_DEFAULT = 10;
    /**
     * 默认输出保留精度值小数位数
     */
    private static final int SCALE_DEFAULT = 2;

    /**
     * description:
     *
     * @param v1
     * @param v2
     * @Author: chen wei
     * @Date: 2019/6/24
     * @return: int
     */
    public static int intAdd(Integer v1, Integer v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.add(b2).intValue();
    }

    public static int intMul(Integer v1, Integer v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.multiply(b2).intValue();
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static double doubleAdd(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static double doubleSub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static double doubleMul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static double doubleDiv(double v1, double v2) {
        return doubleDiv(v1, v2, SCALE_DIV_DEFAULT);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double doubleDiv(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double doubleRound(double v, int scale) {

        return doubleDiv(v, 1, scale);
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v
     *            需要四舍五入的数字
     * @return 四舍五入后的结果默认保留两位小数
     */
    public static double doubleRound(double v) {

        return doubleDiv(v, 1, SCALE_DEFAULT);
    }

    /**
     * description 数字字符串转化为数字
     *
     * @param resource
     *            源字符串
     * @param cla
     *            数字class
     * @return java.lang.Object
     * @author chen wei
     * @date 2019/7/16
     */
    public static <T> T typeChange(String resource, Class<? extends Number> cla) {
        if (StringUtils.isBlank(resource)) {
            return null;
        }
        try {
            Method method = ReflectionUtils.findMethod(cla, "valueOf", String.class);
            if (method == null) {
                return null;
            }
            return (T)ReflectionUtils.invokeMethod(method, cla, resource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * description 判断是否正数
     * 
     * @author chen wei
     * @date 2019/11/12
     * @return boolean
     */
    public static boolean isPositive(Number number) {
        if (number == null) {
            return false;
        }
        String value = number.toString();
        if ("0".equals(value)) {
            return false;
        }
        if (value.indexOf("-") != -1) {
            return false;
        }
        return true;
    }
}
