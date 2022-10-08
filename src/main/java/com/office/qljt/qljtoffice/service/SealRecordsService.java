package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SealRecordsDTO;
import com.office.qljt.qljtoffice.entity.SealRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SealRecordsVO;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
public interface SealRecordsService extends IService<SealRecords> {

    /**
     * 查询印信申请记录
     *
     * @return 印信申请记录
     */
    PageResult<SealRecordsDTO> listSealRecordsDTO();

    /**
     * 查询所有印信申请记录
     *
     * @return 印信申请记录
     */
    PageResult<SealRecordsDTO> listAllSealRecordsDTO();

    /**
     * 条件查询所有印信申请记录
     *
     * @return 印信申请记录
     */
    PageResult<SealRecordsDTO> listSealRecordsDTOByCondition(ConditionVO conditionVO);

    /**
     * 保存/更新印信申请
     * @param sealRecordVO 印信申请
     * @return 保存/更新印信申请
     */
    Result<?> saveOrUpdateSealRecords(SealRecordsVO sealRecordVO);

}
