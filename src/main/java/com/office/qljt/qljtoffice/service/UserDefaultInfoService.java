package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.UserDefaultInfoDTO;
import com.office.qljt.qljtoffice.entity.UserDefaultInfo;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.UserDefaultInfoVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface UserDefaultInfoService extends IService<UserDefaultInfo> {


    /**
     * 保存/更新用户默认信息
     *
     * @param userDefaultInfoVO 用户默认信息信息
     * @return 保存/更新用户默认信息
     */
    Result<?> saveOrUpdateUserDefaultInfo(UserDefaultInfoVO userDefaultInfoVO);

    /**
     * 用户默认信息列表
     *
     * @return 用户默认信息列表
     */
    PageResult<UserDefaultInfoDTO> listUserDefaultInfosDTO(String userId);

    /**
     * 删除用户默认信息
     *
     * @param deleteVO 删除用户默认信息列表
     */
    void updateUserDefaultInfosDelete(DeleteVO deleteVO);
}
