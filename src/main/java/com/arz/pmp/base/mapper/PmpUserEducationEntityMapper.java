package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpUserEducationEntity;

public interface PmpUserEducationEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_education
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long educationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_education
     *
     * @mbg.generated
     */
    int insert(PmpUserEducationEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_education
     *
     * @mbg.generated
     */
    int insertSelective(PmpUserEducationEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_education
     *
     * @mbg.generated
     */
    PmpUserEducationEntity selectByPrimaryKey(Long educationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_education
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PmpUserEducationEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_education
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PmpUserEducationEntity record);
}