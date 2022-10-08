package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SuppliesCategoryDTO;
import com.office.qljt.qljtoffice.entity.SuppliesCategory;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SuppliesCategoryDao extends BaseMapper<SuppliesCategory> {

    /**
     * 获取设备分类列表
     *
     * @return 获取设备分类列表
     */
    List<SuppliesCategoryDTO> listSuppliesCategoryDTO();


    /**
     * 获取设备分类
     *
     * @param id 设备分类id
     * @return 获取设备分类
     */
    SuppliesCategoryDTO getSuppliesCategoryDTO(@Param("id") String id);
}
