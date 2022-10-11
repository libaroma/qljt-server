package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SuppliesCategoryDao;
import com.office.qljt.qljtoffice.dto.SuppliesCategoryDTO;
import com.office.qljt.qljtoffice.entity.SuppliesCategory;
import com.office.qljt.qljtoffice.service.SuppliesCategoryService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.StatusVO;
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
public class SuppliesCategoryServiceImpl extends ServiceImpl<SuppliesCategoryDao, SuppliesCategory> implements SuppliesCategoryService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SuppliesCategoryDao suppliesCategoryDao;

    @Override
    public PageResult<SuppliesCategoryDTO> listSuppliesCategoryDTO() {
        return new PageResult<>(suppliesCategoryDao.listSuppliesCategoryDTO(), suppliesCategoryDao.selectCount((null)));
    }

    @Override
    public void saveOrUpdateSuppliesCategory(SuppliesCategoryDTO suppliesCategoryDTO) {
        SuppliesCategory suppliesCategory = BeanCopyUtils.copyObject(suppliesCategoryDTO, SuppliesCategory.class);
        if (suppliesCategory.getStatus() == null) suppliesCategory.setStatus(1L);
        if (TextUtils.isEmpty(suppliesCategory.getId())) suppliesCategory.setId(idWorker.nextId() + "");
        this.saveOrUpdate(suppliesCategory);
    }

    @Override
    public void updateSuppliesCategoryDelete(StatusVO deleteVO) {
        List<SuppliesCategory> suppliesCategoryList = deleteVO.getIdList().stream()
                .map(id -> SuppliesCategory.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()
                ).collect(Collectors.toList());
        this.updateBatchById(suppliesCategoryList);
    }
}
