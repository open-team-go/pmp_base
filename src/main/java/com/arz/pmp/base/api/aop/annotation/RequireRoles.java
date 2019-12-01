package com.arz.pmp.base.api.aop.annotation;

import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import org.apache.shiro.authz.annotation.Logical;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description 权限角色注解类
 * 
 * @author chen wei
 * @date 2019/7/16
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequireRoles {

    /**
     * A single String role name or multiple comma-delimited role names required in order for the method invocation to
     * be allowed.
     */
    SysPermEnumClass.RoleEnum[] value();

    /**
     * The logical operation for the permission check in case multiple roles are specified. AND is the default
     * 
     * @since 1.1.0
     */
    Logical logical() default Logical.OR;
}
