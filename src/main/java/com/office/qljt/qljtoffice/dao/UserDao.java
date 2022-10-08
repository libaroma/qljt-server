package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    UserDTO getUserDTOByUserId(@Param("id") String userId);

    /**
     * 获取用户列表
     *
     * @param current 当前页
     * @param size    页面尺寸
     * @return 获取用户列表
     */
    List<UserDTO> listUsersDTO(@Param("current") Long current, @Param("size")Long size);

    /**
     * 获取全部用户列表
     *
     * @return 获取全部用户列表
     */
    List<UserDTO> listAllUsersDTO();

    /**
     * 通过sduId获取用户信息
     * @param sduId sduId
     * @return 通过sduId获取用户信息
     */
    UserDTO getUserDTOBySduId(@Param("sduId") String sduId);
    /**
     * 通过sduId获取用户信息
     * @param sduId sduId
     * @return 通过sduId获取用户信息
     */
    UserDTO getUserDTOByIdAndSduId(@Param("id") String id,@Param("sduId") String sduId);
}
