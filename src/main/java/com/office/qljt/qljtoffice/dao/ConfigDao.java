package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.ConfigDTO;
import com.office.qljt.qljtoffice.entity.Config;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface ConfigDao extends BaseMapper<Config> {

    /**
     * 获取配置列表
     *
     * @return 获取配置列表
     */
    List<ConfigDTO> listConfigsDTO();

    /**
     * 查询配置
     *
     * @param configId 配置id
     * @return 查询配置
     */
    ConfigDTO getConfigDTOByConfigId(@Param("id") String configId);
}
