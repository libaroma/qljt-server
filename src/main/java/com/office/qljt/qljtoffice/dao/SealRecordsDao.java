package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SealRecordsDTO;
import com.office.qljt.qljtoffice.entity.SealRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SealRecordsDao extends BaseMapper<SealRecords> {

    /**
     * 获取印信申请列表
     *
     * @param current 当前页
     * @param size    页尺寸
     * @return 获取印信申请列表
     */
    List<SealRecordsDTO> listSealRecordsDTO(@Param("current") Long current, @Param("size") Long size);

    /**
     * 获取全部印信申请列表
     *
     * @return 获取全部印信申请列表
     */
    List<SealRecordsDTO> listAllSealRecordsDTO();


    /**
     * 条件获取印信申请列表
     *
     * @param condition 条件
     * @return 条件获取印信申请列表
     */
    List<SealRecordsDTO> listSealRecordsDTOByCondition(@Param("condition") ConditionVO condition);
}
