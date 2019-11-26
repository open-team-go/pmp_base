package com.arz.pmp.base.api.aop;

import com.arz.pmp.base.api.aop.annotation.RequiresRoles;
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
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

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
    @Before(value = "@annotation(requiresRoles)")
    private void prePerms(JoinPoint jp, RequiresRoles requiresRoles) {

        assertPermissions(jp, requiresRoles, null, true);
    }

    /**
     * description 权限注解RequiresPermissions
     *
     * @author chen wei
     * @date 2019/7/11
     */
    @Before(value = "@annotation(requiresPermissions)")
    private void prePerms(JoinPoint jp, RequiresPermissions requiresPermissions) {

        assertPermissions(jp, null, requiresPermissions, false);
    }

    /**
     * description 权限处理
     * 
     * @author chen wei
     * @date 2019/11/14
     */
    private void assertPermissions(JoinPoint jp, RequiresRoles requiresRoles, RequiresPermissions requiresPermissions,
        boolean roleOn) {
        if (!authEnable) {
            logger.info("properties配置中关闭权限验证");
            return;
        }
        if (roleOn && requiresRoles == null) {
            return;
        }
        if (!roleOn && requiresPermissions == null) {
            return;
        }
        RestRequest restRequest = getRestRequest(jp);

        if (restRequest == null) {
            return;
        }
        String appToken = getHeaderToken(restRequest);

        PmpAdminEntity userInfo = redisService.getOperatorByToken(appToken);
        Assert.isTrue(userInfo != null, CommonCodeEnum.PERMISSION_ERROR_LOGIN);
        boolean flag;
        if (roleOn) {
            String[] rolesArr = requiresRoles.value();
            Logical logical = requiresRoles.logical();
            Assert.isTrue(NumberUtil.isPositive(userInfo.getRoleId()), CommonCodeEnum.PERMISSION_ERROR);
            PmpRoleEntity roleEntity = permissionService.getRoleById(userInfo.getRoleId());
            flag = checkRoles(rolesArr, logical, roleEntity.getRoleCode());

        } else {
            String[] requirePerms = requiresPermissions.value();
            Logical logical = requiresPermissions.logical();
            List<PmpPermissionEntity> permList = permissionService.getPermListByRoleId(userInfo.getRoleId());
            flag = checkPermits(requirePerms, permList, logical);
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
    public boolean checkPermits(String[] requirePerms, Collection<PmpPermissionEntity> stringPerms, Logical logical) {

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
