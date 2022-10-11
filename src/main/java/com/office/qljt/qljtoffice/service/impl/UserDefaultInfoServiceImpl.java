package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.UserDefaultInfoDao;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.dto.UserDefaultInfoDTO;
import com.office.qljt.qljtoffice.entity.UserDefaultInfo;
import com.office.qljt.qljtoffice.service.UserDefaultInfoService;
import com.office.qljt.qljtoffice.service.UserService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.UserDefaultInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class UserDefaultInfoServiceImpl extends ServiceImpl<UserDefaultInfoDao, UserDefaultInfo> implements UserDefaultInfoService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserDefaultInfoDao userDefaultInfoDao;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisServiceImpl redisService;


    @Override
    public Result<?> saveOrUpdateUserDefaultInfo(UserDefaultInfoVO userDefaultInfoVO) {
        UserDefaultInfo userDefaultInfo = BeanCopyUtils.copyObject(userDefaultInfoVO, UserDefaultInfo.class);
        //获取登录用户
        UserDTO loginUser = userService.getLoginUser();
        if (loginUser == null || !loginUser.getId().equals(userDefaultInfo.getUserId()))
            return Result.fail("无权限！用户id与登录用户不一致");
        if (TextUtils.isEmpty(userDefaultInfo.getId())) userDefaultInfo.setId(idWorker.nextId() + "");
        if (userDefaultInfo.getStatus() == null) userDefaultInfo.setStatus(1L);
        this.saveOrUpdate(userDefaultInfo);
        redisService.del("login_" + loginUser.getId());
        return Result.ok(userDefaultInfo);
    }

    @Override
    public PageResult<UserDefaultInfoDTO> listUserDefaultInfosDTO(String userId) {
        if (TextUtils.isEmpty(userId)) return null;
        return new PageResult<>(userDefaultInfoDao.listUserDefaultInfosDTO(userId),
                PageUtils.getCurrent(), PageUtils.getSize(), userDefaultInfoDao.selectCount(null));
    }


    @Override
    public void updateUserDefaultInfosDelete(StatusVO deleteVO) {
        List<UserDefaultInfo> userDefaultInfoList = deleteVO.getIdList().stream()
                .map(id -> UserDefaultInfo.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()).collect(Collectors.toList());
        this.updateBatchById(userDefaultInfoList);
    }
}
