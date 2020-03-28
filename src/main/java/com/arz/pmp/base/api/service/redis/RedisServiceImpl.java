package com.arz.pmp.base.api.service.redis;

import com.arz.pmp.base.api.bo.user.front.UserCacheData;
import com.arz.pmp.base.entity.PmpAdminEntity;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.redis.RedisKeys;
import com.arz.pmp.base.framework.redis.RedisUtil;
import com.arz.pmp.base.framework.redis.TimeConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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

    @Override
    public void setAdminUserUniqueLogin(String loginName, String token) {

        redisUtil.set(getAdminUserUniqueKey(loginName), token);
    }

    @Override
    public String getAdminUserUniqueToken(String loginName) {

        Object value = redisUtil.get(getAdminUserUniqueKey(loginName));
        return value == null ? null : value.toString();
    }

    @Override
    public void delAdminUserUniqueCache(String loginName) {

        redisUtil.del(getAdminUserUniqueKey(loginName));
    }

    private String getAdminUserUniqueKey(String loginName) {
        return RedisKeys.SYS_LOGIN_UNIQUE_PRE + loginName;
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
    public void setFrontUser(String token, UserCacheData userInfo) {
        if (StringUtils.isBlank(token) || userInfo == null) {
            return;
        }

        redisUtil.set(getFrontUserKey(token), userInfo, getOperatorExpire());
    }

    @Override
    public void delFrontUser(String token) {

        String key = getFrontUserKey(token);
        redisUtil.del(key);
    }

    @Override
    public UserCacheData geFrontUserByToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        Object value = redisUtil.get(getFrontUserKey(token));
        if (value == null) {
            return null;
        }
        UserCacheData userInfo = (UserCacheData)value;
        // 延长有效期
        extendFrontUser(token, userInfo);
        return userInfo;
    }

    @Override
    public void setFrontUserUniqueLogin(String loginName, String token) {
        Assert.isTrue(StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(token), CommonCodeEnum.PARAM_ERROR);
        redisUtil.set(getFrontUserUniqueKey(loginName), token);
    }

    @Override
    public String getFrontUserUniqueToken(String loginName) {
        Object value = redisUtil.get(getFrontUserUniqueKey(loginName));

        return value == null ? null : value.toString();
    }

    @Override
    public void delFrontUserUniqueCache(String loginName) {

        redisUtil.del(getFrontUserUniqueKey(loginName));
    }

    @Override
    public int getLoginFailNum(String userName, boolean adminFlag) {
        if (userName == null || userName.isEmpty()) {
            return 0;
        }
        String key = getUserLoginFailKey(userName, adminFlag);
        Object value = redisUtil.get(key);

        return value == null ? 0 : (int)value;
    }

    @Override
    public void delLoginFailNum(String userName, boolean adminFlag) {

        redisUtil.del(getUserLoginFailKey(userName, adminFlag));
    }

    @Override
    public void updateLoginFailNum(String userName, int num, boolean adminFlag) {
        // 过期时间
        redisUtil.set(getUserLoginFailKey(userName, adminFlag), num, getLoginFailExpireSec());
    }

    private long getLoginFailExpireSec() {
        return DateUtil.getDateSecond(DateUtil.strToDate(
            DateUtil.dateToStr(DateUtils.addDays(DateUtil.getCurDateTime(), 1), DateUtil.DateStrFormat.f_2),
            DateUtil.DateStrFormat.f_2)) - DateUtil.getCurSecond();
    }

    private String getUserLoginFailKey(String userName, boolean adminFlag) {
        return (adminFlag ? RedisKeys.SYS_OPERATOR_LOGIN_FAIL : RedisKeys.FRONT_USER_LOGIN_FAIL) + userName;
    }

    private String getFrontUserUniqueKey(String loginName) {

        return RedisKeys.FRONT_USER_LOGIN_UNIQUE_PRE + loginName;
    }

    private String getFrontUserKey(String token) {

        return RedisKeys.FRONT_USER_TOKEN_PRE + token;
    }

    private void extendFrontUser(String token, UserCacheData loginName) {

        long time = redisUtil.getExpire(getFrontUserKey(token));
        // 是否到达延长时间
        if (time > TimeConstants.TWO_HOUR_SECONDS) {
            return;
        }
        // 延长失效时间
        setFrontUser(token, loginName);
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
