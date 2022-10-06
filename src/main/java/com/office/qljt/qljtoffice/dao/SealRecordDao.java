package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SealRecordDTO;
import com.office.qljt.qljtoffice.entity.SealRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SealRecordDao extends BaseMapper<SealRecord> {

    List<SealRecordDTO> listSealRecordsDTO(@Param("current") Long current, @Param("size") Long size);

    List<SealRecordDTO> listAllSealRecordsDTO();
}
