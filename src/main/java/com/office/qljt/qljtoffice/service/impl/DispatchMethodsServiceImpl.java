package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.DispatchMethodsDao;
import com.office.qljt.qljtoffice.dto.DispatchMethodsDTO;
import com.office.qljt.qljtoffice.entity.DispatchMethods;
import com.office.qljt.qljtoffice.service.DispatchMethodsService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.DispatchMethodsVO;
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
public class DispatchMethodsServiceImpl extends ServiceImpl<DispatchMethodsDao, DispatchMethods> implements DispatchMethodsService {


    @Autowired
    private IdWorker idWorker;

    @Autowired
    private DispatchMethodsDao dispatchMethodsDao;

    @Override
    public PageResult<DispatchMethodsDTO> listDispatchMethodsDTO() {
        return new PageResult<>(dispatchMethodsDao.listDispatchMethodsDTO(), dispatchMethodsDao.selectCount(null));
    }

    @Override
    public void savaOrUpdateDispatchMethods(DispatchMethodsVO emailVO) {
        DispatchMethods dispatchMethods = BeanCopyUtils.copyObject(emailVO, DispatchMethods.class);
        if (dispatchMethods.getStatus() == null) dispatchMethods.setStatus(1L);
        if (TextUtils.isEmpty(dispatchMethods.getId())) dispatchMethods.setId(idWorker.nextId() + "");
        this.saveOrUpdate(dispatchMethods);
    }


    @Override
    public void updateDispatchMethodsDelete(DeleteVO deleteVO) {
        List<DispatchMethods> dispatchMethodsList = deleteVO.getIdList().stream()
                .map(id -> DispatchMethods.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(dispatchMethodsList);
    }
}
