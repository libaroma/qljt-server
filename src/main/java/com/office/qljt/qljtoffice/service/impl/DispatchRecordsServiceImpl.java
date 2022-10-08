package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.DispatchMethodsDao;
import com.office.qljt.qljtoffice.dao.DispatchRecordsDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.DispatchMethodsDTO;
import com.office.qljt.qljtoffice.dto.DispatchRecordsDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.DispatchRecords;
import com.office.qljt.qljtoffice.service.DispatchRecordsService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.DispatchRecordsVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Slf4j
@Service
public class DispatchRecordsServiceImpl extends ServiceImpl<DispatchRecordsDao, DispatchRecords> implements DispatchRecordsService {


    @Autowired
    private IdWorker idWorker;
    @Autowired
    private DispatchMethodsDao dispatchMethodsDao;
    @Autowired
    private DispatchRecordsDao dispatchRecordsDao;

    @Autowired
    private UserDao userDao;


    @Override
    public PageResult<DispatchRecordsDTO> listDispatchRecordsDTO() {
        return new PageResult<>(dispatchRecordsDao.listDispatchRecordsDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()), PageUtils.getCurrent(), PageUtils.getSize(), dispatchRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<DispatchRecordsDTO> listAllDispatchRecordsDTO() {
        return new PageResult<>(dispatchRecordsDao.listAllDispatchRecordsDTO(), dispatchRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<DispatchRecordsDTO> listAllSpaceRecordsDTOByCondition(ConditionVO conditionVO) {
        return new PageResult<>(dispatchRecordsDao.listDispatchRecordsDTOByCondition(conditionVO), conditionVO.getCurrent() != null ? conditionVO.getCurrent() : 0L, conditionVO.getSize() != null ? conditionVO.getSize() : dispatchRecordsDao.selectCount(null), dispatchRecordsDao.selectCount(null));
    }

    @Override
    public Result<?> saveOrUpdateDispatchRecords(DispatchRecordsVO dispatchRecordVO) {
        DispatchRecords dispatchRecords = BeanCopyUtils.copyObject(dispatchRecordVO, DispatchRecords.class);
        //获取发文Method
        DispatchMethodsDTO dispatchMethodsDTO = dispatchMethodsDao.getDispatchMethodsDTO(dispatchRecords.getMethod());
        //判断Method是否存在
        if (dispatchMethodsDTO == null) return Result.fail("发文方式不存在");
        UserDTO userDTO = userDao.getUserDTOByUserId(dispatchRecords.getUserId());
        if (userDTO == null) return Result.fail("用户不存在");
        //确定中本年度该Method下的发文sequence
        //创建Condition
        ConditionVO condition = ConditionVO.builder().method(dispatchRecords.getMethod()).year(dispatchRecords.getYear()).build();
        //查找数据库中本年度该Method下的发文
        List<DispatchRecordsDTO> recordsDTOS = dispatchRecordsDao.listDispatchRecordsDTOByConditionBrief(condition);
        //设置sequence
        dispatchRecords.setSequence(recordsDTOS.size() + 1L);
        //设置id
        if (TextUtils.isEmpty(dispatchRecords.getId())) dispatchRecords.setId(idWorker.nextId() + "");
        //保存
        this.saveOrUpdate(dispatchRecords);
        //返回结果
        return Result.ok(dispatchRecords);
    }
}
