package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.course.CourseSearchReq;
import com.arz.pmp.base.entity.PmpCourseEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpCourseExMapper {

    List<PmpCourseEntity> getCourseList(CourseSearchReq search);

    Long selectCourseByName(@Param("courseName") String courseName);
}