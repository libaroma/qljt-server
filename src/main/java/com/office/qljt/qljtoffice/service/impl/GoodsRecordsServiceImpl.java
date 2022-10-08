package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.GoodsDao;
import com.office.qljt.qljtoffice.dao.GoodsRecordsDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.GoodsDTO;
import com.office.qljt.qljtoffice.dto.GoodsRecordsDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.Goods;
import com.office.qljt.qljtoffice.entity.GoodsRecords;
import com.office.qljt.qljtoffice.service.GoodsRecordsService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.GoodsRecordsVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Slf4j
@Service
public class GoodsRecordsServiceImpl extends ServiceImpl<GoodsRecordsDao, GoodsRecords> implements GoodsRecordsService {


    @Autowired
    private IdWorker idWorker;

    @Autowired
    private GoodsDao goodsDao;


    @Autowired
    private GoodsRecordsDao goodsRecordsDao;

    @Autowired
    private UserDao userDao;


    @Override
    public PageResult<GoodsRecordsDTO> listGoodsRecordsDTO() {
        return new PageResult<>(goodsRecordsDao.listGoodsRecordsDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()), PageUtils.getCurrent(), PageUtils.getSize(), goodsRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<GoodsRecordsDTO> listAllGoodsRecordsDTO() {
        return new PageResult<>(goodsRecordsDao.listAllGoodsRecordsDTO(), goodsRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<GoodsRecordsDTO> listGoodsRecordsDTOByCondition(ConditionVO conditionVO) {
        return new PageResult<>(goodsRecordsDao.listGoodsRecordsDTOByCondition(conditionVO), conditionVO.getCurrent() != null ? conditionVO.getCurrent() : 0L, conditionVO.getSize() != null ? conditionVO.getSize() : goodsRecordsDao.selectCount(null), goodsRecordsDao.selectCount(null));
    }

    @Override
    public Result<?> saveOrUpdateGoodsRecords(GoodsRecordsVO goodsRecordsVO) {
        GoodsRecords goodsRecords = BeanCopyUtils.copyObject(goodsRecordsVO, GoodsRecords.class);
        //判断物资是否存在
        GoodsDTO goodsDTO = goodsDao.getGoodsDTO(goodsRecords.getGoodsId());
        if (goodsDTO == null) return Result.fail("物资不存在");
        //判断用户是否存在
        UserDTO userDTO = userDao.getUserDTOByUserId(goodsRecords.getUserId());
        if (userDTO == null) return Result.fail("用户不存在");
        //需要更新物资数量信息
        Long count = goodsRecords.getCount();
        //copy Bean
        Goods goods = BeanCopyUtils.copyObject(goodsDTO, Goods.class);
        //设置剩余数量
        goods.setLeftCount(goods.getLeftCount() - count);
        //保存goods
        goodsDao.updateById(goods);
        //添加id
        if (TextUtils.isEmpty(goodsRecords.getId())) goodsRecords.setId(idWorker.nextId() + "");
        //保存
        this.saveOrUpdate(goodsRecords);
        //返回
        return Result.ok(goodsRecords);
    }
}
