package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.DispatchRecordsDTO;
import com.office.qljt.qljtoffice.entity.DispatchRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface DispatchRecordsDao extends BaseMapper<DispatchRecords> {

    /**
     * 获取发文登记列表
     *
     * @param current 当前页
     * @param size    页尺寸
     * @return 获取发文登记列表
     */
    List<DispatchRecordsDTO> listDispatchRecordsDTO(@Param("current") Long current, @Param("size") Long size);

    /**
     * 获取全部发文登记列表
     *
     * @return 获取全部发文登记列表
     */
    List<DispatchRecordsDTO> listAllDispatchRecordsDTO();


    /**
     * 条件查询
     *
     * @param condition 条件
     * @return 条件查询
     */
    List<DispatchRecordsDTO> listDispatchRecordsDTOByCondition(@Param("condition") ConditionVO condition);

    /**
     * 条件查询(不返回其他嵌套信息)
     *
     * @param condition 条件
     * @return 条件查询
     */
    List<DispatchRecordsDTO> listDispatchRecordsDTOByConditionBrief(@Param("condition") ConditionVO condition);
}
