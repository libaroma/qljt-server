package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SpaceDao;
import com.office.qljt.qljtoffice.dao.SpaceRecordsDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SpaceDTO;
import com.office.qljt.qljtoffice.dto.SpaceRecordsDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.SpaceRecords;
import com.office.qljt.qljtoffice.service.SpaceRecordsService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SpaceRecordsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Slf4j
@Service
public class SpaceRecordsServiceImpl extends ServiceImpl<SpaceRecordsDao, SpaceRecords> implements SpaceRecordsService {


    @Autowired
    private IdWorker idWorker;
    @Autowired
    private SpaceDao spaceDao;
    @Autowired
    private SpaceRecordsDao spaceRecordsDao;

    @Autowired
    private UserDao userDao;


    @Override
    public PageResult<SpaceRecordsDTO> listSpaceRecordsDTO() {
        return new PageResult<>(spaceRecordsDao.listSpaceRecordsDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()), PageUtils.getCurrent(), PageUtils.getSize(), spaceRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<SpaceRecordsDTO> listAllSpaceRecordsDTO() {
        return new PageResult<>(spaceRecordsDao.listAllSpaceRecordsDTO(), spaceRecordsDao.selectCount(null));
    }

    @Override
    public PageResult<SpaceRecordsDTO> listSpaceRecordsDTOByCondition(ConditionVO conditionVO) {
        return new PageResult<>(spaceRecordsDao.listSpaceRecordsDTOByCondition(conditionVO), conditionVO.getCurrent() != null ? conditionVO.getCurrent() : 0L, conditionVO.getSize() != null ? conditionVO.getSize() : spaceRecordsDao.selectCount(null), spaceRecordsDao.selectCount(null));
    }

    @Override
    public Result<?> saveOrUpdateSpaceRecords(SpaceRecordsVO spaceRecordVO) {
        SpaceRecords spaceRecords = BeanCopyUtils.copyObject(spaceRecordVO, SpaceRecords.class);
        //判断space 是否存在
        SpaceDTO spaceDTO = spaceDao.getSpaceDTO(spaceRecords.getSpace());
        if (spaceDTO == null) return Result.fail("会场不存在");
        //判断用户是否存在
        UserDTO userDTO = userDao.getUserDTOByUserId(spaceRecords.getUserId());
        if (userDTO == null) return Result.fail("用户不存在");
        //判断时间是否冲突
        //获取时间，并转化格式
        int begin = getTimeStamp(spaceRecords.getBegin());
        int end = getTimeStamp(spaceRecords.getEnd());
        //判断格式是否正确
        if (begin < 0 || end < 0) return Result.fail("预约时间格式不正确，正确格式为 12:00 ");
        if (begin >= end) return Result.fail("预约时间格式不正确，开始时间应当晚于结束时间");
        //获取数据库内当日的所有预约情况
        //生成条件
        ConditionVO conditionVO = ConditionVO.builder().space(spaceRecords.getSpace()).date(spaceRecords.getDate()).build();
        //查询结果
        List<SpaceRecordsDTO> recordsDTOList = spaceRecordsDao.listSpaceRecordsDTOByConditionBrief(conditionVO);
        if (recordsDTOList.size() > 0) {
            //判断时间是否冲突
            for (SpaceRecordsDTO recordsDTO : recordsDTOList) {
                int t_begin = getTimeStamp(recordsDTO.getBegin());
                int t_end = getTimeStamp(recordsDTO.getEnd());
                if (t_begin >= 0 && t_end >= 0) {
                    if (begin > t_begin && begin < t_end) {
                        return Result.fail("预约开始时间冲突，已存在" + recordsDTO.getDate() + " " + recordsDTO.getBegin() + "-" + recordsDTO.getEnd() + "的预约");
                    }
                    if (end > t_begin && end < t_end) {
                        return Result.fail("预约结束时间冲突，已存在" + recordsDTO.getDate() + " " + recordsDTO.getBegin() + "-" + recordsDTO.getEnd() + "的预约");
                    }
                    if (begin <= t_begin && end >= t_end) {
                        return Result.fail("预约时间冲突，已存在" + recordsDTO.getDate() + " " + recordsDTO.getBegin() + "-" + recordsDTO.getEnd() + "的预约");
                    }
                }
            }
        }
        //添加id
        if (TextUtils.isEmpty(spaceRecords.getId())) spaceRecords.setId(idWorker.nextId() + "");
        this.saveOrUpdate(spaceRecords);
        return Result.ok(spaceRecords);
    }

    /**
     * 依据timeStamp获取时间值
     *
     * @param timeStamp 时间，格式为12:00
     * @return 返回时间值：分钟
     */
    public int getTimeStamp(String timeStamp) {
        if (timeStamp.length() != 5 || timeStamp.indexOf(":") != 2) return -1;
        String[] split = timeStamp.split(":");
        if (split.length != 2) return -1;
        int[] timeArr = new int[2];
        timeArr[0] = Integer.parseInt(split[0]);
        timeArr[1] = Integer.parseInt(split[1]);
        return timeArr[0] * 60 + timeArr[1];
    }

}
