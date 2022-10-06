package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.entity.Seal;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SealDao extends BaseMapper<Seal> {

    List<SealDTO> listSealsDTO();

    SealDTO getSeal(String seal);
}
