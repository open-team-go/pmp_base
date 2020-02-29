package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpUserCourseApplyEntity;

public interface PmpUserCourseApplyEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmpUserCourseApplyEntity record);

    int insertSelective(PmpUserCourseApplyEntity record);

    PmpUserCourseApplyEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmpUserCourseApplyEntity record);

    int updateByPrimaryKeyWithBLOBs(PmpUserCourseApplyEntity record);

    int updateByPrimaryKey(PmpUserCourseApplyEntity record);
}