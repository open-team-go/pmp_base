package com.arz.pmp.base.api.service.redis;

import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.commons.utils.NumberUtil;
import com.arz.pmp.base.framework.redis.RedisKeys;
import com.arz.pmp.base.framework.redis.RedisUtil;
import com.arz.pmp.base.framework.redis.TimeConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * description redis 操作方法接口实现类
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/7/19 10:39
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void setOperator(String appToken, PmpAdminEntity userInfo) {
        if (StringUtils.isBlank(appToken) || userInfo == null) {
            return;
        }

        redisUtil.set(getOperatorKey(appToken), userInfo, getOperatorExpire());
    }

    private String getOperatorKey(String appToken) {

        return RedisKeys.SYS_OPERATION_TOKEN_PRE + appToken;
    }

    private int getOperatorExpire() {

        return TimeConstants.TWELVE_HOUR_SECONDS;
    }

    @Override
    public Long getOperatorIdByToken(String appToken) {

        PmpAdminEntity userInfo = getOperatorByToken(appToken);
        if (userInfo == null) {
            return 0L;
        }
        return userInfo.getAdminId();
    }

    @Override
    public PmpAdminEntity getOperatorByToken(String appToken) {
        if (StringUtils.isBlank(appToken)) {
            return null;
        }
        String key = getOperatorKey(appToken);
        Object value = redisUtil.get(key);
        if (value == null) {
            return null;
        }
        PmpAdminEntity userInfo = (PmpAdminEntity)value;
        // 延长有效期
        extendOperator(appToken, userInfo);
        return userInfo;
    }

    private boolean isExpire(Long expireSecond) {
        if (expireSecond == null) {

            return true;
        }
        Long curSecond = DateUtil.getCurSecond();

        return curSecond.compareTo(expireSecond) <= 0;
    }

    @Override
    public void delOperator(String appToken) {
        String key = getOperatorKey(appToken);
        redisUtil.del(key);
    }

    private void extendOperator(String appToken, PmpAdminEntity userInfo) {

        long time = redisUtil.getExpire(getOperatorKey(appToken));
        // 是否到达延长时间
        if (time > TimeConstants.TWO_HOUR_SECONDS) {
            return;
        }
        // 延长失效时间
        setOperator(appToken, userInfo);
    }

    @Override
    public void cleanPlatConf() {
        String pattern = RedisKeys.CACHE_CONF + "*";
        delAllPatternKey(pattern);
    }

    @Override
    public void cleanAllCache() {
        String pattern = RedisKeys.PROJECT_NAME + "*";
        delAllPatternKey(pattern);
    }

    @Override
    public void delRolePerms(Long roleId) {

        String key = RedisKeys.SYS_OPERATOR_ROLE_PERMISSION + roleId;
        redisUtil.del(key);
    }

    @Override
    public void setFrontUser(String token, Long userId) {
        if (StringUtils.isBlank(token) || userId == null) {
            return;
        }

        redisUtil.set(getFrontUserKey(token), userId, getOperatorExpire());
    }

    @Override
    public void delFrontUser(String token) {

        String key = getFrontUserKey(token);
        redisUtil.del(key);
    }

    @Override
    public Long geFrontUserByToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Object value = redisUtil.get(getFrontUserKey(token));
        if (value == null) {
            return null;
        }
        Long userId = NumberUtil.typeChange(value.toString(), Long.class);
        // 延长有效期
        extendFrontUser(token, userId);
        return userId;
    }

    private String getFrontUserKey(String token) {

        return RedisKeys.FRONT_USER_TOKEN_PRE + token;
    }

    private void extendFrontUser(String token, Long userId) {

        long time = redisUtil.getExpire(getFrontUserKey(token));
        // 是否到达延长时间
        if (time > TimeConstants.TWO_HOUR_SECONDS) {
            return;
        }
        // 延长失效时间
        setFrontUser(token, userId);
    }

    private void delAllPatternKey(String pattern) {
        Set<String> keys = redisUtil.patternKeys(pattern);
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        String[] keyArr = new String[keys.size()];
        redisUtil.del(keys.toArray(keyArr));
    }

}
