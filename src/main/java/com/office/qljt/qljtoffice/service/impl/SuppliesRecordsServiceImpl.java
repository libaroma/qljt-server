package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SuppliesCategoryDao;
import com.office.qljt.qljtoffice.dao.SuppliesRecordsDao;
import com.office.qljt.qljtoffice.dto.SuppliesCategoryDTO;
import com.office.qljt.qljtoffice.dto.SuppliesRecordsDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.SuppliesRecords;
import com.office.qljt.qljtoffice.service.EmailService;
import com.office.qljt.qljtoffice.service.SuppliesRecordsService;
import com.office.qljt.qljtoffice.service.UserService;
import com.office.qljt.qljtoffice.utils.*;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SuppliesRecordsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.office.qljt.qljtoffice.service.impl.EmailServiceImpl.*;

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
    private UserService userService;

    @Autowired
    private EmailService emailService;

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
    public Result<?> saveOrUpdateSuppliesRecords(SuppliesRecordsVO suppliesRecordsVO) {
        SuppliesRecords suppliesRecords = BeanCopyUtils.copyObject(suppliesRecordsVO, SuppliesRecords.class);
        SuppliesCategoryDTO suppliesCategoryDTO = suppliesCategoryDao.getSuppliesCategoryDTO(suppliesRecords.getSuppliesCategory());
        if (suppliesCategoryDTO == null) return Result.fail("设备分类不存在");
        UserDTO loginUser = userService.getLoginUser();
        if (loginUser == null || !loginUser.getId().equals(suppliesRecords.getUserId()))
            return Result.fail("登录用户id与借用记录用户id不一致，无权限");
        if (TextUtils.isEmpty(suppliesRecords.getId())) suppliesRecords.setId(idWorker.nextId() + "");
        this.saveOrUpdate(suppliesRecords);
        emailService.sendEmail(suppliesRecords, suppliesCategoryDTO, loginUser);
        return Result.ok(suppliesRecords);
    }

    @Override
    public Result<?> returnSuppliesRecords(String id) {
        if (TextUtils.isEmpty(id)) return Result.fail("借用记录id不能为空");
        SuppliesRecordsDTO suppliesRecordsDTO = suppliesRecordsDao.getSuppliesRecordsById(id);
        if (suppliesRecordsDTO == null) return Result.fail("没有此借用记录");
        if (suppliesRecordsDTO.getIsReturn() > 0) return Result.fail("已提交归还申请，无需重复申请");
        UserDTO loginUser = userService.getLoginUser();
        if (loginUser == null || (!loginUser.getId().equals(suppliesRecordsDTO.getUserId())&&loginUser.getRole() < 1)) return Result.fail("登录用户id与借用记录用户id不一致，无权限");
        suppliesRecordsDTO.setIsReturn(1L);
        this.saveOrUpdate(BeanCopyUtils.copyObject(suppliesRecordsDTO, SuppliesRecords.class));
        String content = "<h2>这是归还物资通知</h2>\n" +
                prefix_d + " <h4>" + loginUser.getSduInfo().getSduName() + " </h4>提交了一个归还物资申请" + endfix_d +
                prefix_d + " 他要归还的物资是<h4>" + suppliesRecordsDTO.getName() + "</h4>" + endfix_d +
                prefix_d + " 他归还的物资数量为<h4>" + suppliesRecordsDTO.getCount() + "</h4>" + endfix_d +
                prefix_d + " 联系方式是<h4>" + suppliesRecordsDTO.getContact() + "</h4>" + endfix_d +
                prefix_d + " 他的单位是<h4>" + suppliesRecordsDTO.getCompany() + "</h4>" + endfix_d +
                style;

        emailService.sendEmailNotice("归还物资通知", content, null);
        return Result.ok("归还成功！");
    }

    @Override
    public Result<?> examineSuppliesRecords(String id) {
        if (TextUtils.isEmpty(id)) return Result.fail("借用记录id不能为空");
        SuppliesRecordsDTO suppliesRecordsDTO = suppliesRecordsDao.getSuppliesRecordsById(id);
        if (suppliesRecordsDTO == null) return Result.fail("没有此借用记录");
        if (suppliesRecordsDTO.getIsReturn() > 1) return Result.fail("已经审核过啦！");
        UserDTO loginUser = userService.getLoginUser();
        if (loginUser == null || loginUser.getRole() < 1) return Result.fail("无权限操作！");
        suppliesRecordsDTO.setIsReturn(2L);
        this.saveOrUpdate(BeanCopyUtils.copyObject(suppliesRecordsDTO, SuppliesRecords.class));
        return Result.ok("审核完成！");
    }
}
