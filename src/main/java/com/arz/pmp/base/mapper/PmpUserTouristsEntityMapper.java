package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpUserTouristsEntity;

public interface PmpUserTouristsEntityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PmpUserTouristsEntity record);

    int insertSelective(PmpUserTouristsEntity record);

    PmpUserTouristsEntity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmpUserTouristsEntity record);

    int updateByPrimaryKey(PmpUserTouristsEntity record);
}