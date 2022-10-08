package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.UserDefaultInfoDTO;
import com.office.qljt.qljtoffice.entity.UserDefaultInfo;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface UserDefaultInfoDao extends BaseMapper<UserDefaultInfo> {

    /**
     * 获取用户默认信息列表
     *
     * @param userId userId
     * @return 获取用户列表
     */
    List<UserDefaultInfoDTO> listUserDefaultInfosDTO(@Param("id") String userId);
}
