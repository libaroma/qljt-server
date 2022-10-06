package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SealRecordDTO;
import com.office.qljt.qljtoffice.entity.SealRecord;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SealRecordVO;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
public interface SealRecordService extends IService<SealRecord> {

    /**
     * 查询印信申请记录
     *
     * @return 印信申请记录
     */
    PageResult<SealRecordDTO> listSealRecords();

    /**
     * 查询所有印信申请记录
     *
     * @return 印信申请记录
     */
    PageResult<SealRecordDTO> listAllSealRecords();

    Result<?> saveOrUpdateSealRecord(SealRecordVO sealRecordVO);
}
