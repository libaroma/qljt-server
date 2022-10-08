package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.DispatchRecordsDTO;
import com.office.qljt.qljtoffice.entity.DispatchRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.DispatchRecordsVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface DispatchRecordsService extends IService<DispatchRecords> {

    /**
     * 查询发文登记记录
     *
     * @return 发文登记记录
     */
    PageResult<DispatchRecordsDTO> listDispatchRecordsDTO();

    /**
     * 查询所有发文登记记录
     *
     * @return 发文登记记录
     */
    PageResult<DispatchRecordsDTO> listAllDispatchRecordsDTO();

    /**
     * 条件查询发文登记记录
     *
     * @param conditionVO 条件
     * @return 条件查询发文登记记录
     */
    PageResult<DispatchRecordsDTO> listAllSpaceRecordsDTOByCondition(ConditionVO conditionVO);

    /**
     * 保存/更新发文登记
     *
     * @param dispatchRecordsVO 发文登记
     * @return 保存/更新发文登记
     */
    Result<?> saveOrUpdateDispatchRecords(DispatchRecordsVO dispatchRecordsVO);
}
