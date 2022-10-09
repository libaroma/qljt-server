package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.SduDao;
import com.office.qljt.qljtoffice.dao.UserDao;
import com.office.qljt.qljtoffice.dto.SduDTO;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.entity.User;
import com.office.qljt.qljtoffice.service.UserService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.PageUtils;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SduDao sduDao;


    @Override
    public UserDTO getUserDTOByUserId(String userId) {
        if (TextUtils.isEmpty(userId)) return null;
        return userDao.getUserDTOByUserId(userId);
    }

    @Override
    public Result<?> saveOrUpdateUser(UserVO userVO) {
        User user = BeanCopyUtils.copyObject(userVO, User.class);
        SduDTO sduDTO = sduDao.getSduDTO(user.getSduId());
        if (sduDTO == null) return Result.fail("查无此人，添加失败");
        if (TextUtils.isEmpty(user.getId())) user.setId(idWorker.nextId() + "");
        if (user.getStatus() == null) user.setStatus(1L);
        this.saveOrUpdate(user);
        return Result.ok();
    }

    @Override
    public PageResult<UserDTO> listUsersDTO() {
        return new PageResult<>(userDao.listUsersDTO(PageUtils.getCurrent() * PageUtils.getSize(), PageUtils.getSize()),
                PageUtils.getCurrent(), PageUtils.getSize(), userDao.selectCount(null));
    }

    @Override
    public PageResult<UserDTO> listAllUsersDTO() {
        return new PageResult<>(userDao.listAllUsersDTO(), userDao.selectCount(null));
    }

    @Override
    public void updateUsersDelete(DeleteVO deleteVO) {
        List<User> userList = deleteVO.getIdList().stream()
                .map(id -> User.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build()).collect(Collectors.toList());
        this.updateBatchById(userList);
    }
}
