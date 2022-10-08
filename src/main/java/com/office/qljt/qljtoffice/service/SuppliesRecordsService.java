package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SuppliesRecordsDTO;
import com.office.qljt.qljtoffice.entity.SuppliesRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SuppliesRecordsVO;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
public interface SuppliesRecordsService extends IService<SuppliesRecords> {

    /**
     * 查询设备借用记录
     *
     * @return 设备借用记录
     */
    PageResult<SuppliesRecordsDTO> listSuppliesRecordsDTO();

    /**
     * 查询所有设备借用记录
     *
     * @return 设备借用记录
     */
    PageResult<SuppliesRecordsDTO> listAllSuppliesRecordsDTO();

    /**
     * 条件查询设备借用记录
     *
     * @return 条件设备借用记录
     */
    PageResult<SuppliesRecordsDTO> listSuppliesRecordsDTOByCondition(ConditionVO conditionVO);

    /**
     * 保存/更新
     * @param suppliesRecordsVO 设备
     * @return 保存/更新
     */
    Result<?> saveOrUpdateSuppliesRecord(SuppliesRecordsVO suppliesRecordsVO);
}
