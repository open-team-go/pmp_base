package com.arz.pmp.base.api.aop;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.aop.annotation.RequireRoles;
import com.arz.pmp.base.api.service.permission.PermissionService;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.NumberUtil;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;

/**
 * description 权限aop类
 * 
 * @author chen wei
 * @date 2019/11/12
 */
@Aspect
@Component
public class PermAopHandle {

    private static final Logger logger = LoggerFactory.getLogger(PermAopHandle.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private PermissionService permissionService;
    @Value("${service.project.auth.enable}")
    private boolean authEnable;

    /**
     * description 权限注解RequiresRole
     * 
     * @author chen wei
     * @date 2019/7/11
     */
    @Before(value = "@annotation(requireRoles)")
    private void prePerms(JoinPoint jp, RequireRoles requireRoles) {
        String token = getHeaderToken(getRestRequest(jp));
        assertPermissions(token, getPerms(requireRoles.value(), null), requireRoles.logical(), true);
    }

    /**
     * description 权限注解RequiresPermissions
     *
     * @author chen wei
     * @date 2019/7/11
     */
    @Before(value = "@annotation(requiresPermissions)")
    private void prePerms(JoinPoint jp, RequirePermissions requiresPermissions) {
        String token = getHeaderToken(getRestRequest(jp));
        assertPermissions(token, getPerms(null, requiresPermissions.value()), requiresPermissions.logical(), false);
    }

    public String[] getPerms(SysPermEnumClass.RoleEnum[] roles, SysPermEnumClass.PermissionEnum[] permissions) {
        if (ArrayUtils.isEmpty(roles) && ArrayUtils.isEmpty(permissions)) {
            return null;
        }
        String[] perms = ArrayUtils.isEmpty(roles) ? new String[permissions.length] : new String[roles.length];
        int i = 0;
        if (!ArrayUtils.isEmpty(roles)) {

            for (SysPermEnumClass.RoleEnum item : roles) {
                perms[i++] = item.getCode();
            }
        } else {
            for (SysPermEnumClass.PermissionEnum item : permissions) {
                perms[i++] = item.getCode();
            }
        }
        return perms;
    }

    /**
     * description 权限处理
     * 
     * @author chen wei
     * @date 2019/11/14
     */
    public void assertPermissions(String token, String[] perms, Logical logical, boolean roleOn) {
        if (!authEnable) {
            logger.info("properties配置中关闭权限验证");
            return;
        }
        if (ArrayUtils.isEmpty(perms)) {
            return;
        }
        PmpAdminEntity userInfo = redisService.getOperatorByToken(token);
        Assert.isTrue(userInfo != null, CommonCodeEnum.PERMISSION_ERROR_LOGIN);
        boolean flag;
        if (roleOn) {
            Assert.isTrue(NumberUtil.isPositive(userInfo.getRoleId()), CommonCodeEnum.PERMISSION_ERROR);
            PmpRoleEntity roleEntity = permissionService.getRoleById(userInfo.getRoleId());
            flag = checkRoles(perms, logical, roleEntity.getRoleCode());

        } else {
            List<PmpPermissionEntity> permList = permissionService.getPermListByRoleId(userInfo.getRoleId());
            flag = checkPermits(perms, permList, logical);
        }
        Assert.isTrue(flag, CommonCodeEnum.PERMISSION_ERROR);

    }

    private RestRequest getRestRequest(JoinPoint jp) {
        Object[] args = jp.getArgs();
        if (ArrayUtils.isEmpty(args)) {
            return null;
        }
        for (Object obj : args) {
            if (obj != null && obj instanceof RestRequest) {
                return (RestRequest)obj;
            }
        }
        return null;
    }

    private String getHeaderToken(RestRequest restRequest) {
        RequestHeader header = restRequest.getHeader();
        Assert.isTrue(header != null && header.getAuthentication() != null, CommonCodeEnum.PERMISSION_ERROR_LOGIN);
        return header.getAuthentication();
    }

    /**
     * description 验证角色是否符合
     * 
     * @author chen wei
     * @date 2019/8/8
     * @return boolean
     */
    private boolean checkRoles(String[] requireRoles, Logical logical, String role) {

        if (logical.equals(Logical.AND)) {
            return false;
        }
        for (String item : requireRoles) {

            if (item.equals(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * description 验证权限是否符合
     * 
     * @author chen wei
     * @date 2019/8/8
     * @return boolean
     */
    private boolean checkPermits(String[] requirePerms, Collection<PmpPermissionEntity> stringPerms, Logical logical) {

        Collection<Permission> perms = resolvePermissions(stringPerms);

        if (Logical.OR.equals(logical)) {

            for (String permission : requirePerms) {
                if (isPermit(permission, perms)) {
                    return true;
                }

            }
            return false;
        } else if (Logical.AND.equals(logical)) {

            for (String permission : requirePerms) {

                if (!isPermit(permission, perms)) {
                    return false;
                }

            }
            return true;
        }
        return false;
    }

    /**
     * description 验证权限
     * 
     * @author chen wei
     * @date 2019/8/8
     * @return boolean
     */
    public boolean isPermit(String requirePerm, Collection<Permission> perms) {
        Permission permission = new WildcardPermission(requirePerm);

        for (Permission perm : perms) {
            if (perm.implies(permission)) {
                return true;
            }
        }

        return false;
    }

    /**
     * description 构建权限对象集合
     * 
     * @author chen wei
     * @date 2019/8/8
     * @return boolean
     */
    public Collection<Permission> resolvePermissions(Collection<PmpPermissionEntity> stringPerms) {
        Collection<Permission> perms = Collections.emptySet();
        if (!CollectionUtils.isEmpty(stringPerms)) {
            perms = new LinkedHashSet<Permission>(stringPerms.size());
            for (PmpPermissionEntity item : stringPerms) {
                Permission permission = new WildcardPermission(item.getPermValue());
                perms.add(permission);
            }
        }
        return perms;
    }
}
