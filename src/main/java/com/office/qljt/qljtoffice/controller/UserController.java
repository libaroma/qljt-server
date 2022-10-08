package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.UserDTO;
import com.office.qljt.qljtoffice.service.UserService;
import com.office.qljt.qljtoffice.utils.UserUtils;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.OptTypeConst.REMOVE;
import static com.office.qljt.qljtoffice.constant.OptTypeConst.SAVE_OR_UPDATE;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.NORMAL;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Api(tags = "用户信息模块")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户
     *
     * @return 获取当前登录用户
     */
    @ApiOperation(value = "获取当前登录用户")
    @GetMapping("/get")
    public Result<UserDTO> getUserDTOByUserId() {
        return Result.ok(UserUtils.getLoginUser());
    }

    /**
     * 获取用户
     *
     * @return 获取用户
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "获取用户")
    @GetMapping("/get/{userId}")
    public Result<UserDTO> getUserDTOByUserId(@PathVariable("userId") String userId) {
        return Result.ok(userService.getUserDTOByUserId(userId));
    }

    /**
     * 获取用户列表
     *
     * @return 获取用户列表
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
    public Result<PageResult<UserDTO>> listUsersDTO() {
        return Result.ok(userService.listUsersDTO());
    }

    /**
     * 获取全部用户列表
     *
     * @return 获取全部用户列表
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "获取全部用户列表")
    @GetMapping("/all")
    public Result<PageResult<UserDTO>> listAllUsersDTO() {
        return Result.ok(userService.listAllUsersDTO());
    }

    /**
     * 保存/更新用户
     *
     * @param userVO 用户信息
     * @return 保存/更新用户
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = NORMAL)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存/更新用户")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateUser(@Valid @RequestBody UserVO userVO) {
        return userService.saveOrUpdateUser(userVO);
    }

    /**
     * 删除用户
     *
     * @param deleteVO 用户id列表
     * @return 删除用户
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = REMOVE)
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/del")
    public Result<?> updateUsersDelete(@Valid @RequestBody DeleteVO deleteVO) {
        userService.updateUsersDelete(deleteVO);
        return Result.ok();
    }

}
