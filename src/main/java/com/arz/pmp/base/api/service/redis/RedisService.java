package com.arz.pmp.base.api.service.redis;

import com.arz.pmp.base.entity.PmpAdminEntity;

/**
 * description java类作用描述
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/7/19 10:41
 */
public interface RedisService {

    void setOperator(String appToken, PmpAdminEntity userInfo);

    void delOperator(String appToken);

    /**
     * description 获取登录验证token对应值
     * 
     * @param appToken
     *            token
     * @author chen wei
     * @date 2019/8/8
     * @return java.lang.Long
     */
    Long getOperatorIdByToken(String appToken);

    PmpAdminEntity getOperatorByToken(String appToken);

    /**
     * description 清除分销平台redis缓存
     * 
     * @author chen wei
     * @date 2019/8/29
     * @return void
     */
    void cleanPlatConf();

    /**
     * description 清除分销redis缓存
     *
     * @author chen wei
     * @date 2019/8/29
     * @return void
     */
    void cleanAllCache();

    void delRolePerms(Long roleId);

    // 前台用户

    void setFrontUser(String token, Long userId);

    void delFrontUser(String token);

    Long geFrontUserByToken(String token);

}
