
 package com.arz.pmp.base.api.aop.annotation;

 import java.lang.annotation.*;

 @Target(ElementType.FIELD)
 @Retention(RetentionPolicy.RUNTIME)
 @Inherited
 public @interface PayrollProperty {



    String value() default "";
    /**
    *
    * default @see com.alibaba.excel.util.TypeUtil
    * if default is not  meet you can set format
    *
    * @return
    */
   String format() default "";


 }


