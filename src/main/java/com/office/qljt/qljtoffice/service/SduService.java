package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SduDTO;
import com.office.qljt.qljtoffice.entity.Sdu;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.SduVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface SduService extends IService<Sdu> {

    /**
     * 保存/更新Sdu
     *
     * @param sduVO sdu
     */
    void saveOrUpdateSdu(SduVO sduVO);

    /**
     * 删除Sdu
     *
     * @param deleteVO sdu Id
     */
    void updateSdusDelete(DeleteVO deleteVO);

    /**
     * 获取sdu列表
     *
     * @return 获取sdu列表
     */
    PageResult<SduDTO> listSdusDTO();


    /**
     * 获取全部sdu列表
     *
     * @return 获取全部sdu列表
     */
    PageResult<SduDTO> listAllSdusDTO();
}
