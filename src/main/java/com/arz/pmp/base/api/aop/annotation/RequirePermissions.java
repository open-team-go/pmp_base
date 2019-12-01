package com.arz.pmp.base.api.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import org.apache.shiro.authz.annotation.Logical;

/**
 * description 权限角色注解类
 * 
 * @author chen wei
 * @date 2019/7/16
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequirePermissions {

    /**
     * A single String role name or multiple comma-delimited permissions names required in order for the method
     * invocation to be allowed.
     */
    SysPermEnumClass.PermissionEnum[] value();

    /**
     * The logical operation for the permission check in case multiple permissions are specified. AND is the default
     * 
     * @since 1.1.0
     */
    Logical logical() default Logical.OR;
}
