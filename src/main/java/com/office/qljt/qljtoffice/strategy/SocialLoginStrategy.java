package com.office.qljt.qljtoffice.strategy;

import com.office.qljt.qljtoffice.dto.UserDTO;

/**
 * 第三方登录策略
 *
 * @author lib
 * @date 2021/07/28
 */
public interface SocialLoginStrategy {

    /**
     * 登录
     *
     * @param data 数据
     * @return {@link UserDTO} 用户信息
     */
    UserDTO login(String data);

}
