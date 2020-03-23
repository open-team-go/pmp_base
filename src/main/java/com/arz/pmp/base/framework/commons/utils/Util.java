package com.arz.pmp.base.framework.commons.utils;

import com.arz.pmp.base.framework.commons.constants.Constants;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * description 通用方法
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:06
 */
public class Util {

    public static boolean isEmpty(Object pObj) {
        if (pObj == null) {
            return true;
        }
        if (pObj instanceof String) {

            return StringUtils.isEmpty((String)pObj);
        } else if (pObj instanceof Collection) {

            return CollectionUtils.isEmpty((Collection)pObj);
        } else if (pObj instanceof Map) {

            return MapUtils.isEmpty((Map)pObj);
        } else if (pObj.getClass().isArray()) {

            return ArrayUtils.getLength(pObj) == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        String path = "/front/user/loginPassword";
        System.out.println(Constants.REGEX_INTERCEPTOR_AUTH_OFF.matcher(path).matches());
    }

}
