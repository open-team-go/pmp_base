package com.arz.pmp.base.api.bo.user.front;

import lombok.Data;

@Data
public class UserCacheData {

    private Long userId;

    private Long touristsId;

    private boolean perfectOn;

    private String loginName;
}
