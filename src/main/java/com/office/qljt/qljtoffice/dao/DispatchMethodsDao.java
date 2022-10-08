package com.office.qljt.qljtoffice.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.office.qljt.qljtoffice.dto.DispatchMethodsDTO;
import com.office.qljt.qljtoffice.entity.DispatchMethods;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-05
 */

@Repository
public interface DispatchMethodsDao extends BaseMapper<DispatchMethods> {

    /**
     * 获取全部发文登记方式列表
     *
     * @return 获取全部发文登记方式列表
     */
    List<DispatchMethodsDTO> listDispatchMethodsDTO();

    /**
     * 获取发文登记方式
     *
     * @return 获取全部发文登记方式
     */
    DispatchMethodsDTO getDispatchMethodsDTO(@Param("id") String id);
}
