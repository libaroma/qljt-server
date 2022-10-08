package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SuppliesCategoryDTO;
import com.office.qljt.qljtoffice.entity.SuppliesCategory;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface SuppliesCategoryService extends IService<SuppliesCategory> {

    /**
     * 获取设备分类信息列表
     *
     * @return 获取设备分类信息列表
     */
    PageResult<SuppliesCategoryDTO> listSuppliesCategoryDTO();

    /**
     * 保存/更新设备分类信息
     *
     * @param suppliesCategoryDTO 保存/更新设备分类信息
     */
    void saveOrUpdateSuppliesCategory(SuppliesCategoryDTO suppliesCategoryDTO);

    /**
     * 删除设备分类信息
     *
     * @param deleteVO 删除设备分类信息
     */
    void updateSuppliesCategoryDelete(DeleteVO deleteVO);
}
