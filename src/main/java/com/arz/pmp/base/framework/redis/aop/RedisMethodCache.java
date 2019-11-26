package com.arz.pmp.base.framework.redis.aop;

import com.arz.pmp.base.framework.redis.TimeConstants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description 方法redis缓存注解类
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/7/29 17:23
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisMethodCache {

    /** 过期时间秒数 */
    int expire() default TimeConstants.ONE_DAY_SECONDS;

    /** redis存储key前缀 */
    String keyPre();

    /** 是否redis存储 */
    boolean saveOn() default true;

}
