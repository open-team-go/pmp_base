package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpTeachingPlaceEntity;

public interface PmpTeachingPlaceEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_place
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long placeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_place
     *
     * @mbg.generated
     */
    int insert(PmpTeachingPlaceEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_place
     *
     * @mbg.generated
     */
    int insertSelective(PmpTeachingPlaceEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_place
     *
     * @mbg.generated
     */
    PmpTeachingPlaceEntity selectByPrimaryKey(Long placeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_place
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PmpTeachingPlaceEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_place
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PmpTeachingPlaceEntity record);
}