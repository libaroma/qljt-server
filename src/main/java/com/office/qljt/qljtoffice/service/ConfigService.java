package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.ConfigDTO;
import com.office.qljt.qljtoffice.entity.Config;
import com.office.qljt.qljtoffice.vo.ConfigVO;
import com.office.qljt.qljtoffice.vo.PageResult;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface ConfigService extends IService<Config> {


    /**
     * 获取配置列表
     *
     * @return 获取配置列表
     */
    PageResult<ConfigDTO> listConfigsDTO();

    /**
     * 查询配置
     *
     * @param configId 配置id
     * @return 查询配置
     */
    ConfigDTO getConfigDTOByConfigId(String configId);

    /**
     * 保存/更新配置
     *
     * @param configVO 配置信息
     */
    void savaOrUpdateConfig(ConfigVO configVO);
}
