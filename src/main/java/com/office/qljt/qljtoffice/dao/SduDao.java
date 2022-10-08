package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SduDTO;
import com.office.qljt.qljtoffice.entity.Sdu;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SduDao extends BaseMapper<Sdu> {

    /**
     * 获取Sdu
     *
     * @param sduId sduId
     * @return 获取Sdu
     */
    SduDTO getSduDTO(@Param("sduId") String sduId);

    /**
     * 获取sdu列表
     *
     * @param current 当前页
     * @param size    页尺寸
     * @return 获取sdu列表
     */
    List<SduDTO> listSdusDTO(@Param("current") Long current, @Param("size") Long size);

    /**
     * 获取全部sdu列表
     *
     * @return 获取全部sdu列表
     */
    List<SduDTO> listAllSdusDTO();
}
