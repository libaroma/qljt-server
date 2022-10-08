package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.GoodsRecordsDTO;
import com.office.qljt.qljtoffice.entity.GoodsRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface GoodsRecordsDao extends BaseMapper<GoodsRecords> {

    /**
     * 获取物资请领列表
     *
     * @param current 当前页
     * @param size    页尺寸
     * @return 获取物资请领列表
     */
    List<GoodsRecordsDTO> listGoodsRecordsDTO(@Param("current") Long current, @Param("size") Long size);

    /**
     * 获取全部物资请领列表
     *
     * @return 获取全部物资请领列表
     */
    List<GoodsRecordsDTO> listAllGoodsRecordsDTO();


    /**
     * 条件获取物资请领列表
     *
     * @param condition 条件
     * @return 条件获取物资请领列表
     */
    List<GoodsRecordsDTO> listGoodsRecordsDTOByCondition(@Param("condition") ConditionVO condition);
}
