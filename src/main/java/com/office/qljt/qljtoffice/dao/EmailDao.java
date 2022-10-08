package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.EmailDTO;
import com.office.qljt.qljtoffice.entity.Email;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface EmailDao extends BaseMapper<Email> {

    /**
     * 获取邮箱列表
     *
     * @return 获取邮箱列表
     */
    List<EmailDTO> listEmailsDTO();

    /**
     * 获取邮箱
     * @param email 邮箱
     * @return 获取邮箱
     */
    EmailDTO getEmailDTOByEmail(@Param("email") String email);

}
