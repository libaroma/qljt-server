package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.GoodsDao;
import com.office.qljt.qljtoffice.dto.GoodsDTO;
import com.office.qljt.qljtoffice.entity.Goods;
import com.office.qljt.qljtoffice.service.GoodsService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public PageResult<GoodsDTO> listGoodsDTO() {
        return new PageResult<>(goodsDao.listGoodsDTO(), goodsDao.selectCount((null)));
    }

    @Override
    public void saveOrUpdateGoods(GoodsDTO goodsDTO) {
        Goods goods = BeanCopyUtils.copyObject(goodsDTO, Goods.class);
        if (goods.getStatus() == null) goods.setStatus(1L);
        if (TextUtils.isEmpty(goods.getId())) goods.setId(idWorker.nextId() + "");
        this.saveOrUpdate(goods);
    }

    @Override
    public void updateGoodsDelete(DeleteVO deleteVO) {
        List<Goods> goodsList = deleteVO.getIdList().stream()
                .map(id -> Goods.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()
                ).collect(Collectors.toList());
        this.updateBatchById(goodsList);
    }
}
