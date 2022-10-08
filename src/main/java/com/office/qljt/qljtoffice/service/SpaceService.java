package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.SpaceDTO;
import com.office.qljt.qljtoffice.entity.Space;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.SpaceVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface SpaceService extends IService<Space> {

    /**
     * 获取会场列表
     *
     * @return 获取会场列表
     */
    PageResult<SpaceDTO> listSpacesDTO();

    /**
     * 保存/更新会场
     *
     * @param spaceVO 保存/更新会场
     */
    void saveOrUpdateSpace(SpaceVO spaceVO);

    /**
     * 删除会场
     *
     * @param deleteVO 删除会场
     */
    void updateSpaceDelete(DeleteVO deleteVO);
}
