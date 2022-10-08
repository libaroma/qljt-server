package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SealDao;
import com.office.qljt.qljtoffice.dao.SealRecordsDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.dto.SealRecordsDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.SealRecords;
import com.office.qljt.qljtoffice.service.SealRecordsService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SealRecordsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Slf4j
@Service
public class SealRecordsServiceImpl extends ServiceImpl<SealRecordsDao, SealRecords> implements SealRecordsService {


    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SealDao sealDao;
    @Autowired
    private SealRecordsDao sealRecordsDao;

    @Autowired
    private UserDao userDao;


    @Override
    public PageResult<SealRecordsDTO> listSealRecordsDTO() {
        return new PageResult<>(sealRecordsDao.listSealRecordsDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()), PageUtils.getCurrent(), PageUtils.getSize(), sealRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<SealRecordsDTO> listAllSealRecordsDTO() {
        return new PageResult<>(sealRecordsDao.listAllSealRecordsDTO(), sealRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<SealRecordsDTO> listSealRecordsDTOByCondition(ConditionVO conditionVO) {
        return new PageResult<>(sealRecordsDao.listSealRecordsDTOByCondition(conditionVO), conditionVO.getCurrent() != null ? conditionVO.getCurrent() : 0L, conditionVO.getSize() != null ? conditionVO.getSize() : sealRecordsDao.selectCount(null), sealRecordsDao.selectCount(null));
    }

    @Override
    public Result<?> saveOrUpdateSealRecords(SealRecordsVO sealRecordVO) {
        SealRecords sealRecords = BeanCopyUtils.copyObject(sealRecordVO, SealRecords.class);
        SealDTO sealDTO = sealDao.getSealDTO(sealRecords.getSeal());
        if (sealDTO == null) return Result.fail("公章不存在");
        UserDTO userDTO = userDao.getUserDTOByUserId(sealRecords.getUserId());
        if (userDTO == null) return Result.fail("用户不存在");
        if (TextUtils.isEmpty(sealRecords.getId())) sealRecords.setId(idWorker.nextId() + "");
        this.saveOrUpdate(sealRecords);
        return Result.ok(sealRecords);
    }
}
