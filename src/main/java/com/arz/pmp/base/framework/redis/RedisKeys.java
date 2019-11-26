package com.arz.pmp.base.framework.redis;

/**
 * description rides keys
 * 
 * @author chen wei
 * @date 2019/11/11
 */
public interface RedisKeys {

    String PROJECT_NAME = "PMP_";

    String CACHE_CONF = PROJECT_NAME + "conf_";

    String SYS = "sys_";

    String FRONT = "front_";

    String DEFAULT_SEPARATOR = "_";

    // 后台
    /** 管理端缓存 */
    String SYS_CONF = CACHE_CONF + SYS;

    /** 管理端token前缀 */
    String SYS_OPERATION_TOKEN_PRE = SYS_CONF + "token" + DEFAULT_SEPARATOR;
    /** 管理员角色 */
    String SYS_OPERATOR_ROLE = SYS_CONF + "role" + DEFAULT_SEPARATOR;
    /** 管理员权限 */
    String SYS_OPERATOR_ROLE_PERMISSION = SYS_OPERATOR_ROLE + "perm" + DEFAULT_SEPARATOR;

}
