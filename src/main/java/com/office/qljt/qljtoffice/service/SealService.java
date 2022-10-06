package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.entity.Seal;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.SealVO;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
public interface SealService extends IService<Seal> {


    PageResult<SealDTO> listSeals();

    void savaOrUpdateSeal(SealVO sealVO);

    void updateSealDelete(DeleteVO deleteVO);
}
