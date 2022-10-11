package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.DispatchMethodsDTO;
import com.office.qljt.qljtoffice.entity.DispatchMethods;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.DispatchMethodsVO;
import com.office.qljt.qljtoffice.vo.PageResult;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface DispatchMethodsService extends IService<DispatchMethods> {


    /**
     * 获取发文登记方式列表
     *
     * @return 获取发文登记方式列表
     */
    PageResult<DispatchMethodsDTO> listDispatchMethodsDTO();

    /**
     * 保存/更新发文登记方式
     *
     * @param dispatchMethodsVO 发文登记方式信息
     */
    void savaOrUpdateDispatchMethods(DispatchMethodsVO dispatchMethodsVO);

    /**
     * 删除发文登记方式
     *
     * @param deleteVO 发文登记方式id列表
     */
    void updateDispatchMethodsDelete(StatusVO deleteVO);
}
