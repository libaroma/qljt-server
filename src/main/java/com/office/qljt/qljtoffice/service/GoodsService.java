package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.GoodsDTO;
import com.office.qljt.qljtoffice.entity.Goods;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 获取物资列表
     *
     * @return 获取物资列表
     */
    PageResult<GoodsDTO> listGoodsDTO();

    /**
     * 保存/更新物资
     *
     * @param goodsDTO 保存/更新物资
     */
    void saveOrUpdateGoods(GoodsDTO goodsDTO);

    /**
     * 删除物资
     *
     * @param deleteVO 删除物资
     */
    void updateGoodsDelete(DeleteVO deleteVO);
}
