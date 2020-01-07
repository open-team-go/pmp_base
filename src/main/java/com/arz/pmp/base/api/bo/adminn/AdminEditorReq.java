package com.arz.pmp.base.api.bo.adminn;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import com.arz.pmp.base.framework.commons.constants.Constants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description 管理员添加修改类
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 10:07
 */
@Data
@ApiModel
public class AdminEditorReq {

    @ApiModelProperty("管理员ID")
    private Long adminId;

    @ApiModelProperty("用户名")
    @NotEmpty
    @Length(max = 30)
    private String userName;

    @ApiModelProperty("密码")
    @Length(max = 16, min = 6)
    private String password;

    @ApiModelProperty("头像")
    @Pattern(regexp = Constants.REGEX_URL_STR)
    private String avatar;

    @ApiModelProperty("昵称")
    @Length(max = 50)
    private String nickname;

    @ApiModelProperty("邮箱")
    @Pattern(regexp = Constants.REGEX_EMAIL_STR)
    private String email;

    @ApiModelProperty("联系方式")
    private String contactPhone;

    @ApiModelProperty("角色ID")
    @Positive
    @NotNull(message = "角色必填")
    private Long roleId;

}
