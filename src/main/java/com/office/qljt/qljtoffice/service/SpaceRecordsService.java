package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SpaceRecordsDTO;
import com.office.qljt.qljtoffice.entity.SpaceRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SpaceRecordsVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface SpaceRecordsService extends IService<SpaceRecords> {

    /**
     * 查询会场预约记录
     *
     * @return 会场预约记录
     */
    PageResult<SpaceRecordsDTO> listSpaceRecordsDTO();

    /**
     * 查询所有会场预约记录
     *
     * @return 会场预约记录
     */
    PageResult<SpaceRecordsDTO> listAllSpaceRecordsDTO();

    /**
     * 条件查询会场预约历史
     *
     * @return 会场预约历史
     */
    PageResult<SpaceRecordsDTO> listSpaceRecordsDTOByCondition(ConditionVO conditionVO);

    /**
     * 保存/更新会场预约记录
     *
     * @param spaceRecordsVO 会场预约记录
     * @return 保存/更新会场预约记录
     */
    Result<?> saveOrUpdateSpaceRecords(SpaceRecordsVO spaceRecordsVO);
}
