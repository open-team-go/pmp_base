package com.arz.pmp.base.framework.core.utils;

import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.fastjson.JSON;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.core.utils.excel.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

/**
 * description: java类作用描述
 *
 * @author chen wei
 * @date 2019/7/12 13:05
 * @version: 1.0
 *           <p>
 *           Copyright: Copyright (c) 2019
 *           </p>
 */
public class WebUtil {

    private WebUtil() {}

    /**
     * description 消息response回复
     *
     * @param request
     *            请求实例
     * @param response
     *            回复实例
     * @param restResponse
     *            返回信息实例
     * @return void
     * @author chen wei
     * @date 2019/7/13
     */
    public static void sendRestResponse(ServletRequest request, ServletResponse response,
        RestResponse<T> restResponse) {

        HttpServletResponse res = (HttpServletResponse)response;

        res.setContentType("text/plain");
        res.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = res.getWriter();
            out.write(restResponse.toString());
            res.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    /**
     * description 获取WEB会话中请求实例
     *
     * @return javax.servlet.http.HttpServletRequest
     * @author chen wei
     * @date 2019/7/13
     */
    public static HttpServletRequest getRequest() {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request =
            (HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);

        return request;
    }

    /**
     * description 获取WEB会话中回复实例
     *
     * @param
     * @return javax.servlet.http.HttpServletResponse
     * @author chen wei
     * @date 2019/7/13
     */
    public static HttpServletResponse getResponse() {
        HttpServletResponse reponse =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return reponse;
    }

    private static String createFileName(String fileName){
        if(StringUtils.isBlank(fileName)){
            fileName = "pmp";
        }
        String date = DateFormatUtils.format(DateUtil.getCurDateTime(),DateUtil.DateStrFormat.f_2);

        return fileName + "_" + date;
    }

    /**
     * description EXCEL下载返回
     *
     * @return void
     * @author chen wei
     * @date 2019/7/15
     */
    public static void sendExcelResponse(HttpServletResponse response, String fileName,
        List<? extends BaseRowModel> list, Class cla) {
        if (response == null) {
            return;
        }
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + createFileName(fileName) + ".xlsx");

        try {
            ServletOutputStream out = response.getOutputStream();
            ExcelUtil.writeExcelResponse(fileName, out, list, cla);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.flushBuffer();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * description 根据request 获取IP
     * 
     * @author chen wei
     * @date 2019/7/16
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取json参数
     *
     * @param request
     * @return
     */
    public static JSON getRequestBodyJson(HttpServletRequest request) {
        BufferedReader streamReader = null;
        try {
            streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            if (StringUtils.isBlank(responseStrBuilder.toString())) {
                return null;
            }
            JSON json = (JSON)JSON.parse(responseStrBuilder.toString());
            return json;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                }
            }

        }
        return null;
    }

    public static boolean isMultipart(HttpServletRequest request) {
        CommonsMultipartResolver commonsMultipartResolver =
            new CommonsMultipartResolver(request.getSession().getServletContext());

        return commonsMultipartResolver.isMultipart(request);
    }
}
