package com.arz.pmp.base.api.controller.front;

import com.arz.pmp.base.api.bo.user.UserDataResp;
import com.arz.pmp.base.api.bo.user.front.UserCheckReq;
import com.arz.pmp.base.api.bo.user.front.UserRegistReq;
import com.arz.pmp.base.api.service.user.UserService;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@Api(value = "前台 用户操作API集",tags = "前台 用户操作API集")
@RestController
@RequestMapping("/front/user")
public class UserFrontRestController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户信息 用户上传用户信息",notes = "用户上传用户信息")
    @PostMapping("/register")
    public RestResponse<String> insertUser(@RequestBody @Valid RestRequest<UserRegistReq> data){

        userService.insertUserRegister(data.getBody());

        return RestResponse.success();
    }

    @ApiOperation(value = "用户信息 用户完善用户信息",notes = "用户完善用户信息")
    @PostMapping("/perfect")
    public RestResponse<String> updateUser(@RequestBody @Valid RestRequest<UserRegistReq> data){

        userService.insertUserRegister(data.getBody());

        return RestResponse.success();
    }

    @ApiOperation(value = "用户信息 用户查看用户信息",notes = "用户查看用户信息")
    @PostMapping("/check")
    public RestResponse<UserDataResp> getUserData(@RequestBody @Valid RestRequest<UserCheckReq> data){

        UserDataResp user = userService.getFrontUser(data.getBody());

        return RestResponse.success(user);
    }
}
