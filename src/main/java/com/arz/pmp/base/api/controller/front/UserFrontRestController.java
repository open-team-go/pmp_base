package com.arz.pmp.base.api.controller.front;

import com.arz.pmp.base.api.bo.user.front.*;
import com.arz.pmp.base.api.service.user.UserService;
import com.arz.pmp.base.entity.PmpUserCourseApplyEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.commons.utils.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@Api(value = "前台 用户操作API集", tags = "前台 用户操作API集")
@RestController
@RequestMapping("/front/user")
public class UserFrontRestController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户注册 用户注册", notes = "用户注册")
    @PostMapping("/register")
    public RestResponse goRegister(@RequestBody @Valid RestRequest<UserCheckReq> data) {

        userService.insertUserTourists(data.getBody());
        return RestResponse.success();
    }

    @ApiOperation(value = "用户登录 用户登录", notes = "用户登录")
    @PostMapping("/login")
    public RestResponse<String> goLogin(@RequestBody @Valid RestRequest<UserCheckReq> data) {

        String authentication = userService.goLogin(data.getBody());
        return RestResponse.success(authentication);
    }

    @ApiOperation(value = "用户登出 用户登出", notes = "用户登出")
    @PostMapping("/logOut")
    public RestResponse loginOut(@RequestBody @Valid RestRequest data) {

        Assert.isTrue(data.getBody() != null, CommonCodeEnum.PARAM_ERROR);
        userService.logOut(data.getHeader().getAuthentication());
        return RestResponse.success();
    }

    @ApiOperation(value = "用户基本信息 用户基本信息获取", notes = "用户基本信息获取")
    @PostMapping("/info/get")
    public RestResponse<UserPerfectData> getUserData(@RequestBody @Valid RestRequest data) {

        UserPerfectData user = userService.getFrontUser(data.getHeader().getAuthentication());

        return RestResponse.success(user);
    }

    @ApiOperation(value = "用户基本信息 用户基本信息完善", notes = "用户完善用户信息")
    @PostMapping("/info/perfect")
    public RestResponse<String> updateUser(@RequestBody @Valid RestRequest<UserPerfectReq> data) {

        userService.updateUserRegister(data.getBody(), data.getHeader().getAuthentication());

        return RestResponse.success();
    }

    @ApiOperation(value = "用户课程 查询用户已选课程", notes = "查询用户已选课程")
    @PostMapping("/course")
    public RestResponse<CourseListData> getUserCourseList(@RequestBody @Valid RestRequest data) {

        List<CourseListData> list = userService.getUserCourseList(data.getHeader().getAuthentication());
        return RestResponse.success(list);
    }

    @ApiOperation(value = "用户课程 课程考试报名信息查询", notes = "课程考试报名信息查询")
    @PostMapping("/course/apply")
    public RestResponse<PmpUserCourseApplyEntity>
        getUserCourseApply(@RequestBody @Valid RestRequest<CourseApplyData> data) {

        PmpUserCourseApplyEntity result =
            userService.getUserCourseApply(data.getBody().getUserRefCourseId(), data.getHeader().getAuthentication());
        return RestResponse.success(result);
    }

    @ApiOperation(value = "用户课程 课程考试报名信息更新", notes = "课程考试报名信息更新")
    @PostMapping("/course/apply/up")
    public RestResponse updateUserCourseApply(@RequestBody @Valid RestRequest<CourseApplyData> data) {

        userService.updateUserCourseApply(data.getBody(), data.getHeader().getAuthentication());
        return RestResponse.success();
    }

}
