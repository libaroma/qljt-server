package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SuppliesRecordsDTO;
import com.office.qljt.qljtoffice.entity.SuppliesRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SuppliesRecordsDao extends BaseMapper<SuppliesRecords> {

    /**
     * 获取设备借用列表
     *
     * @param current 当前页
     * @param size    页尺寸
     * @return 获取设备借用列表
     */
    List<SuppliesRecordsDTO> listSuppliesRecordsDTO(@Param("current") Long current, @Param("size") Long size);

    /**
     * 获取全部设备借用列表
     *
     * @return 获取全部设备借用列表
     */
    List<SuppliesRecordsDTO> listAllSuppliesRecordsDTO();


    /**
     * 条件获取设备借用列表
     *
     * @param condition 条件
     * @return 条件获取设备借用列表
     */
    List<SuppliesRecordsDTO> listSuppliesRecordsDTOByCondition(@Param("condition") ConditionVO condition);

    /**
     * id查询
     * @param id id
     * @return 查询
     */
    SuppliesRecordsDTO getSuppliesRecordsById(@Param("id") String id);
}
