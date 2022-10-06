package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface UserDao extends BaseMapper<User> {
    /**
     * 查询用户
     *
     * @param userId id
     * @return 查询用户
     */
    UserDTO getUser(String userId);
}
