package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.entity.Seal;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SealDao extends BaseMapper<Seal> {

    /**
     * 获取公章列表
     *
     * @return 获取公章列表
     */
    List<SealDTO> listSealsDTO();

    /**
     * 获取公章
     *
     * @param sealId 公章id
     * @return 获取公章
     */
    SealDTO getSealDTO(@Param("id") String sealId);
}
