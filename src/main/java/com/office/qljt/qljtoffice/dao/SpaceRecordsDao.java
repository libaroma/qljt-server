package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SpaceRecordsDTO;
import com.office.qljt.qljtoffice.entity.SpaceRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SpaceRecordsDao extends BaseMapper<SpaceRecords> {

    /**
     * 获取会场预约列表
     *
     * @param current 当前页
     * @param size    页尺寸
     * @return 获取会场预约列表
     */
    List<SpaceRecordsDTO> listSpaceRecordsDTO(@Param("current") Long current, @Param("size") Long size);

    /**
     * 获取全部会场预约列表
     *
     * @return 获取全部会场预约列表
     */
    List<SpaceRecordsDTO> listAllSpaceRecordsDTO();

    /**
     * 条件查询
     *
     * @param condition 条件
     * @return 条件查询
     */
    List<SpaceRecordsDTO> listSpaceRecordsDTOByCondition(@Param("condition") ConditionVO condition);

    /**
     * 条件查询(不返回其他嵌套信息
     *
     * @param condition 条件
     * @return 条件查询
     */
    List<SpaceRecordsDTO> listSpaceRecordsDTOByConditionBrief(@Param("condition") ConditionVO condition);

    /**
     * 根据id查询记录
     *
     * @param id id
     * @return 查询记录
     */
    SpaceRecordsDTO getSpaceRecordsById(@Param("id") String id);
}
