package com.arz.pmp.base.api.service.course;

import com.arz.pmp.base.api.bo.course.CourseEditorReq;
import com.arz.pmp.base.api.bo.course.CourseSearchReq;
import com.arz.pmp.base.entity.PmpCourseEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description 课程业务
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
public interface CourseService {

    PageInfo<List<PmpCourseEntity>> getCourseListPage(RestRequest<CourseSearchReq> req);

    List<PmpCourseEntity> getCourseAll(CourseSearchReq search);

    Long addOrUpCourse(CourseEditorReq data, boolean addOn, String authentication);

    void deleteCourse(Long courseId, String authentication);

}
