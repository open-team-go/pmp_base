package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpUserEntity;

public interface PmpUserEntityMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(PmpUserEntity record);

    int insertSelective(PmpUserEntity record);

    PmpUserEntity selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(PmpUserEntity record);

    int updateByPrimaryKey(PmpUserEntity record);
}