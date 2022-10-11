package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.entity.Seal;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.SealVO;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
public interface SealService extends IService<Seal> {


    /**
     * 获取公章列表
     *
     * @return 获取公章列表
     */
    PageResult<SealDTO> listSealsDTO();

    /**
     * 保存/更新公章
     *
     * @param sealVO 公章信息
     */
    void savaOrUpdateSeal(SealVO sealVO);

    /**
     * 删除公章
     *
     * @param deleteVO 公章id
     */
    void updateSealDelete(StatusVO deleteVO);
}
