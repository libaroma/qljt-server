package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.User;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.UserVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface UserService extends IService<User> {


    /**
     * 查询用户
     *
     * @param userId 用户id
     * @return 查询用户
     */
    UserDTO getUserDTOByUserId(String userId);

    /**
     * 保存/更新用户
     *
     * @param userVO 用户信息
     * @return 保存/更新用户
     */
    Result<?> saveOrUpdateUser(UserVO userVO);

    /**
     * 用户列表
     *
     * @return 用户列表
     */
    PageResult<UserDTO> listUsersDTO();


    /**
     * 全部用户列表
     *
     * @return 全部用户列表
     */
    PageResult<UserDTO> listAllUsersDTO();

    /**
     * 删除用户
     *
     * @param deleteVO 删除用户列表
     */
    void updateUsersDelete(DeleteVO deleteVO);
}
