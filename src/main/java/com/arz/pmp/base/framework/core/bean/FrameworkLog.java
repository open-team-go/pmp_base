package com.arz.pmp.base.framework.core.bean;

import lombok.Data;

/**
 * description 日志打印类
 * 
 * @author chen wei
 * @date 2019/11/15
 */
@Data
public class FrameworkLog {

    private String requestUri;

    private String clazz;

    private Object request;

    private Object response;

    private String ip;

}
