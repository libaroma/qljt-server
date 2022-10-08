package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.GoodsRecordsDTO;
import com.office.qljt.qljtoffice.entity.GoodsRecords;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.GoodsRecordsVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface GoodsRecordsService extends IService<GoodsRecords> {

    /**
     * 查询物资请领记录
     *
     * @return 物资请领记录
     */
    PageResult<GoodsRecordsDTO> listGoodsRecordsDTO();

    /**
     * 查询所有物资请领记录
     *
     * @return 物资请领记录
     */
    PageResult<GoodsRecordsDTO> listAllGoodsRecordsDTO();

    /**
     * 条件查询物资请领记录
     *
     * @return 物资请领记录
     */
    PageResult<GoodsRecordsDTO> listGoodsRecordsDTOByCondition(ConditionVO conditionVO);

    /**
     * 保存/更新物资请领历史
     *
     * @param goodsRecordsVO 物资
     * @return 保存/更新
     */
    Result<?> saveOrUpdateGoodsRecords(GoodsRecordsVO goodsRecordsVO);

}
