package com.doocker.crm.mapper;

import com.doocker.crm.po.Transfer;
import com.doocker.crm.po.TransferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TransferMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int countByExample(TransferExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int deleteByExample(TransferExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int deleteByPrimaryKey(Integer tid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int insert(Transfer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int insertSelective(Transfer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    List<Transfer> selectByExample(TransferExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    Transfer selectByPrimaryKey(Integer tid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int updateByExampleSelective(@Param("record") Transfer record, @Param("example") TransferExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int updateByExample(@Param("record") Transfer record, @Param("example") TransferExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int updateByPrimaryKeySelective(Transfer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transfer
     *
     * @mbggenerated Tue Jul 25 15:27:57 CST 2017
     */
    int updateByPrimaryKey(Transfer record);
}