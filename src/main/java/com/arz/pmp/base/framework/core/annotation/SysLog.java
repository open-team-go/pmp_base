package com.arz.pmp.base.framework.core.annotation;

import com.arz.pmp.base.framework.core.enums.SysLogEnumClass;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * description 日志写入注解类
 * 
 * @author chen wei
 * @date 2019/7/16
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface SysLog {

    /** 操作类型 */
    SysLogEnumClass.OptionTypeEnum type();

    /** 操作模块 */
    SysLogEnumClass.OptionModuleEnum module();

    /** 操作描述 */
    String describe() default "";
}
