package com.arz.pmp.base.api.bo.user.front;

import com.arz.pmp.base.framework.commons.constants.Constants;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class UserPasswordData {

    @NotEmpty(message = "旧的登录密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度限制6-16位")
    private String oldPassword;

    @NotEmpty(message = "新的登录密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度限制6-16位")
    private String newPassword;
}
