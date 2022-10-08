package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SuppliesCategoryDao;
import com.office.qljt.qljtoffice.dao.SuppliesRecordsDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SuppliesCategoryDTO;
import com.office.qljt.qljtoffice.dto.SuppliesRecordsDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.SuppliesRecords;
import com.office.qljt.qljtoffice.service.SuppliesRecordsService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SuppliesRecordsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Slf4j
@Service
public class SuppliesRecordsServiceImpl extends ServiceImpl<SuppliesRecordsDao, SuppliesRecords> implements SuppliesRecordsService {


    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SuppliesCategoryDao suppliesCategoryDao;
    @Autowired
    private SuppliesRecordsDao suppliesRecordsDao;

    @Autowired
    private UserDao userDao;


    @Override
    public PageResult<SuppliesRecordsDTO> listSuppliesRecordsDTO() {
        return new PageResult<>(suppliesRecordsDao.listSuppliesRecordsDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()), PageUtils.getCurrent(), PageUtils.getSize(), suppliesRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<SuppliesRecordsDTO> listAllSuppliesRecordsDTO() {
        return new PageResult<>(suppliesRecordsDao.listAllSuppliesRecordsDTO(), suppliesRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<SuppliesRecordsDTO> listSuppliesRecordsDTOByCondition(ConditionVO conditionVO) {
        return new PageResult<>(suppliesRecordsDao.listSuppliesRecordsDTOByCondition(conditionVO), conditionVO.getCurrent() != null ? conditionVO.getCurrent() : 0L, conditionVO.getSize() != null ? conditionVO.getSize() : suppliesRecordsDao.selectCount(null), suppliesRecordsDao.selectCount(null));

    }

    @Override
    public Result<?> saveOrUpdateSuppliesRecord(SuppliesRecordsVO suppliesRecordsVO) {
        SuppliesRecords suppliesRecords = BeanCopyUtils.copyObject(suppliesRecordsVO, SuppliesRecords.class);
        SuppliesCategoryDTO suppliesCategoryDTO = suppliesCategoryDao.getSuppliesCategoryDTO(suppliesRecords.getSuppliesCategory());
        if (suppliesCategoryDTO == null) return Result.fail("设备分类不存在");
        UserDTO userDTO = userDao.getUserDTOByUserId(suppliesRecords.getUserId());
        if (userDTO == null) return Result.fail("用户不存在");
        if (TextUtils.isEmpty(suppliesRecords.getId())) suppliesRecords.setId(idWorker.nextId() + "");
        this.saveOrUpdate(suppliesRecords);
        return Result.ok(suppliesRecords);
    }
}
