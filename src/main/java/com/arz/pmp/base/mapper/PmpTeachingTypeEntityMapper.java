package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpTeachingTypeEntity;

public interface PmpTeachingTypeEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_type
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long typeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_type
     *
     * @mbg.generated
     */
    int insert(PmpTeachingTypeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_type
     *
     * @mbg.generated
     */
    int insertSelective(PmpTeachingTypeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_type
     *
     * @mbg.generated
     */
    PmpTeachingTypeEntity selectByPrimaryKey(Long typeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PmpTeachingTypeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_teaching_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PmpTeachingTypeEntity record);
}