package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SpaceDao;
import com.office.qljt.qljtoffice.dto.SpaceDTO;
import com.office.qljt.qljtoffice.entity.Space;
import com.office.qljt.qljtoffice.service.SpaceService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.SpaceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class SpaceServiceImpl extends ServiceImpl<SpaceDao, Space> implements SpaceService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private SpaceDao spaceDao;

    @Override
    public PageResult<SpaceDTO> listSpacesDTO() {
        return new PageResult<>(spaceDao.listSpacesDTO(), spaceDao.selectCount((null)));
    }

    @Override
    public void saveOrUpdateSpace(SpaceVO spaceVO) {
        Space space = BeanCopyUtils.copyObject(spaceVO, Space.class);
        if (space.getStatus() == null) space.setStatus(1L);
        if (space.getSequence() == null) space.setSequence(1L);
        if (TextUtils.isEmpty(space.getId())) space.setId(idWorker.nextId() + "");
        this.saveOrUpdate(space);
    }

    @Override
    public void updateSpaceDelete(StatusVO deleteVO) {
        List<Space> spaceList = deleteVO.getIdList().stream()
                .map(id -> Space.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()
                ).collect(Collectors.toList());
        this.updateBatchById(spaceList);
    }
}
