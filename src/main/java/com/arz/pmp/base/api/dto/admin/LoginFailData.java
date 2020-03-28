package com.arz.pmp.base.api.dto.admin;

import lombok.Data;

/**
 * description java类作用描述
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2020
 *          </p>
 * @date 2020/3/28 11:08
 */
@Data
public class LoginFailData {

    private int failNum;

    private long limitTime;

}
