package com.arz.pmp.base.api.controller.back;

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.*;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import com.arz.pmp.base.api.bo.CommonDataReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.bo.course.CourseEditorReq;
import com.arz.pmp.base.api.bo.course.CourseSearchReq;
import com.arz.pmp.base.api.service.course.CourseService;
import com.arz.pmp.base.entity.PmpCourseEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.core.annotation.SysLog;
import com.arz.pmp.base.framework.core.enums.SysLogEnumClass;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * description 课程操作
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/11 21:42
 */
@Api(value = "后端 课程操作API集", tags = "后端 课程操作API集")
@RestController
@RequestMapping("/back/course")
public class CourseRestController {

    @Resource
    private CourseService courseService;

    @ApiOperation(value = "课程 列表查看", notes = "分页查看课程")
    @PostMapping("/index")
    @RequirePermissions({COURSE_READ})
    public RestResponse<PageInfo<List<PmpCourseEntity>>>
        getCourseListPage(@RequestBody @Valid RestRequest<CourseSearchReq> data) {

        PageInfo pageInfo = courseService.getCourseListPage(data);

        return RestResponse.success(pageInfo);
    }

    @ApiOperation(value = "课程 所有课程", notes = "获取所有课程")
    @PostMapping("/all")
    public RestResponse<List<PmpCourseEntity>> getCourseAll(@RequestBody @Valid RestRequest<CourseSearchReq> data) {

        List<PmpCourseEntity> list = courseService.getCourseAll(data.getBody());

        return RestResponse.success(list);
    }

    @ApiOperation(value = "课程 课程添加", notes = "添加新的课程")
    @PostMapping("/add")
    @RequirePermissions({COURSE_ADD})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.ADD, module = SysLogEnumClass.OptionModuleEnum.SYS_COURSE,
        describe = "添加课程信息")
    public RestResponse<Long> addCourse(@RequestBody @Valid RestRequest<CourseEditorReq> data) {

        Long id = courseService.addOrUpCourse(data.getBody(), true, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "课程 课程更新", notes = "更新课程")
    @PostMapping("/update")
    @RequirePermissions({COURSE_UPDATE})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.UPDATE, module = SysLogEnumClass.OptionModuleEnum.SYS_COURSE,
        describe = "更新课程信息")
    public RestResponse<Long> updateCourse(@RequestBody @Valid RestRequest<CourseEditorReq> data) {

        Long id = courseService.addOrUpCourse(data.getBody(), false, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "课程 课程删除", notes = "更新删除")
    @PostMapping("/delete")
    @RequirePermissions({COURSE_DEL})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.DELETE, module = SysLogEnumClass.OptionModuleEnum.SYS_COURSE,
        describe = "删除课程信息")
    public RestResponse deleteCourse(@RequestBody @Valid RestRequest<CommonDataReq> data) {

        courseService.deleteCourse(data.getBody().getId(), data.getHeader().getAuthentication());

        return RestResponse.success();
    }
}
