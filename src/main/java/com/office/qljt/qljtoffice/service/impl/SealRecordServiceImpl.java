package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SealDao;
import com.office.qljt.qljtoffice.dao.SealRecordDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.dto.SealRecordDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.SealRecord;
import com.office.qljt.qljtoffice.service.SealRecordService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SealRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Service
public class SealRecordServiceImpl extends ServiceImpl<SealRecordDao, SealRecord> implements SealRecordService {


    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SealDao sealDao;
    @Autowired
    private SealRecordDao sealRecordDao;

    @Autowired
    private UserDao userDao;


    @Override
    public PageResult<SealRecordDTO> listSealRecords() {
        return new PageResult<>(sealRecordDao.listSealRecordsDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()), sealRecordDao.selectCount(null));
    }

    @Override
    public PageResult<SealRecordDTO> listAllSealRecords() {
        return new PageResult<>(sealRecordDao.listAllSealRecordsDTO(), sealRecordDao.selectCount(null));
    }

    @Override
    public Result<?> saveOrUpdateSealRecord(SealRecordVO sealRecordVO) {
        SealRecord sealRecord = BeanCopyUtils.copyObject(sealRecordVO, SealRecord.class);
        SealDTO sealDTO = sealDao.getSeal(sealRecord.getSeal());
        if (sealDTO == null) return Result.fail("公章不存在");
        UserDTO userDTO = userDao.getUser(sealRecord.getUserId());
        if (userDTO == null) return Result.fail("用户不存在");
        sealRecord.setId(idWorker.nextId() + "");
        return Result.ok();
    }
}
