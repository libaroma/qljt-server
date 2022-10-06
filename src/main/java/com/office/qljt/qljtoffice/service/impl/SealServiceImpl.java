package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SealDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.entity.Seal;
import com.office.qljt.qljtoffice.service.SealService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.SealVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Service
public class SealServiceImpl extends ServiceImpl<SealDao, Seal> implements SealService {


    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SealDao sealDao;
    @Autowired
    private UserDao userDao;

    @Override
    public PageResult<SealDTO> listSeals() {
        return new PageResult<>(sealDao.listSealsDTO(), sealDao.selectCount(null));
    }

    @Override
    public void savaOrUpdateSeal(SealVO sealVO) {
//        if (TextUtils.isEmpty(userId)) return false;
//        UserDTO user = userDao.getUser(userId);
//        if (user == null || user.getRole() > 0) return false;
        Seal seal = BeanCopyUtils.copyObject(sealVO, Seal.class);
        seal.setStatus(1L);
        seal.setId(idWorker.nextId() + "");
        this.saveOrUpdate(seal);
    }


    @Override
    public void updateSealDelete(DeleteVO deleteVO) {
        List<Seal> sealList = deleteVO.getIdList().stream()
                .map(id -> Seal.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()
                ).collect(Collectors.toList());
        this.updateBatchById(sealList);
    }
}
