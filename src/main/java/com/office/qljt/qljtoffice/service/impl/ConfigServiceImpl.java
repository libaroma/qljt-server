package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.ConfigDao;
import com.office.qljt.qljtoffice.dto.ConfigDTO;
import com.office.qljt.qljtoffice.entity.Config;
import com.office.qljt.qljtoffice.service.ConfigService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.ConfigVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigDao, Config> implements ConfigService {


    @Autowired
    private IdWorker idWorker;

    @Autowired
    private ConfigDao configDao;

    @Override
    public PageResult<ConfigDTO> listConfigsDTO() {
        return new PageResult<>(configDao.listConfigsDTO(), configDao.selectCount(null));
    }

    @Override
    public ConfigDTO getConfigDTOByConfigId(String configId) {
        return configDao.getConfigDTOByConfigId(configId);
    }

    @Override
    public void savaOrUpdateConfig(ConfigVO configVO) {
        Config config = BeanCopyUtils.copyObject(configVO, Config.class);
        if (TextUtils.isEmpty(config.getId())) config.setId(idWorker.nextId() + "");
        this.saveOrUpdate(config);
    }

}
