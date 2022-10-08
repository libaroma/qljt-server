package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.SpaceDTO;
import com.office.qljt.qljtoffice.entity.Space;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface SpaceDao extends BaseMapper<Space> {

    /**
     * 获取会场列表
     *
     * @return 获取会场列表
     */
    List<SpaceDTO> listSpacesDTO();

    /**
     * 获取会场信息
     *
     * @param spaceId spaceid
     * @return 获取会场信息
     */
    SpaceDTO getSpaceDTO(@Param("id") String spaceId);
}
