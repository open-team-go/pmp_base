package com.arz.pmp.base.mapper;

import com.arz.pmp.base.entity.PmpUserPayTypeEntity;

public interface PmpUserPayTypeEntityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_pay_type
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long payId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_pay_type
     *
     * @mbg.generated
     */
    int insert(PmpUserPayTypeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_pay_type
     *
     * @mbg.generated
     */
    int insertSelective(PmpUserPayTypeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_pay_type
     *
     * @mbg.generated
     */
    PmpUserPayTypeEntity selectByPrimaryKey(Long payId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_pay_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PmpUserPayTypeEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table pmp_user_pay_type
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PmpUserPayTypeEntity record);
}