package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.EmailDTO;
import com.office.qljt.qljtoffice.entity.Email;
import org.springframework.stereotype.Repository;

/**
 * @author  续加仪
 * @date 2022-10-05
 */

@Repository
public interface EmailDao extends BaseMapper<Email> {

}
