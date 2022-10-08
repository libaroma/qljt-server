package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.GoodsDTO;
import com.office.qljt.qljtoffice.entity.Goods;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface GoodsDao extends BaseMapper<Goods> {

    /**
     * 获取物资列表
     *
     * @return 获取物资列表
     */
    List<GoodsDTO> listGoodsDTO();

    /**
     * 获取物资
     *
     * @param goodsId 物资id
     * @return 获取物资
     */
    GoodsDTO getGoodsDTO(@Param("id") String goodsId);
}
