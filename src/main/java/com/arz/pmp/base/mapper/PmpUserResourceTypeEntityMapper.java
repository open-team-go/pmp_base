package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpUserResourceTypeEntity;

public interface PmpUserResourceTypeEntityMapper {
    int deleteByPrimaryKey(Long resourceId);

    int insert(PmpUserResourceTypeEntity record);

    int insertSelective(PmpUserResourceTypeEntity record);

    PmpUserResourceTypeEntity selectByPrimaryKey(Long resourceId);

    int updateByPrimaryKeySelective(PmpUserResourceTypeEntity record);

    int updateByPrimaryKey(PmpUserResourceTypeEntity record);
}