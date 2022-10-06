package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SduDao;
import com.office.qljt.qljtoffice.entity.Sdu;
import com.office.qljt.qljtoffice.service.SduService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.SduVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class SduServiceImpl extends ServiceImpl<SduDao, Sdu> implements SduService {

    @Autowired
    private SduDao sduDao;

    @Override
    public void saveOrUpdate(SduVO sduVO) {
        Sdu sdu = BeanCopyUtils.copyObject(sduVO, Sdu.class);
        sdu.setStatus(1L);
        this.saveOrUpdate(sdu);
    }

    @Override
    public void deleteSdu(DeleteVO deleteVO) {
        List<Sdu> sduList = deleteVO.getIdList().stream()
                .map(id -> Sdu.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()
                ).collect(Collectors.toList());
        this.updateBatchById(sduList);
    }
}
