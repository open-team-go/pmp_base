package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpUserRefCourseEntity;

public interface PmpUserRefCourseEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmpUserRefCourseEntity record);

    int insertSelective(PmpUserRefCourseEntity record);

    PmpUserRefCourseEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmpUserRefCourseEntity record);

    int updateByPrimaryKey(PmpUserRefCourseEntity record);
}