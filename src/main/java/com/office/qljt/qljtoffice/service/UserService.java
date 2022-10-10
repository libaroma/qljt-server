package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.User;
import com.office.qljt.qljtoffice.vo.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface UserService extends IService<User> {

    /**
     * 尝试获取登录用户
     *
     * @return 返回
     */
    UserDTO getLoginUser();

    /**
     * 尝试解密openid
     * @param iv
     * @param encryptedData
     * @param code
     * @return
     */
    Result<?> decodeUerInfo(String iv, String encryptedData, String code);

    /**
     * 尝试解密openid
     * @param code
     * @return
     */
    Result<?> getOpenid(String code);

    /**
     * 尝试获取登录用户
     *
     * @param request 请求
     * @return 返回
     */
    UserDTO getLoginUser(HttpServletRequest request);

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
     * 条件查询用户列表
     *
     * @return 条件查询用户列表
     */
    PageResult<UserDTO> listUsersDTOByCondition(ConditionVO conditionVO);


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

    /**
     * 从缓存里面拿到登录信息
     *
     * @param id id
     * @return 从缓存里面拿到登录信息
     */
    UserDTO getUserDTOByUserIdFromRedis(String id);

    /**
     * 保存登录信息到缓存
     *
     * @param userDTO userInfo
     */
    void saveUserInfoToRedis(UserDTO userDTO);
}
