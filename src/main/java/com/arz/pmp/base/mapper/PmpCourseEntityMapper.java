package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpCourseEntity;

public interface PmpCourseEntityMapper {
    int deleteByPrimaryKey(Long courseId);

    int insert(PmpCourseEntity record);

    int insertSelective(PmpCourseEntity record);

    PmpCourseEntity selectByPrimaryKey(Long courseId);

    int updateByPrimaryKeySelective(PmpCourseEntity record);

    int updateByPrimaryKey(PmpCourseEntity record);
}