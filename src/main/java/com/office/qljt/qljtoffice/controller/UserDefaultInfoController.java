package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.UserDefaultInfoDTO;
import com.office.qljt.qljtoffice.service.UserDefaultInfoService;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.UserDefaultInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.OptTypeConst.REMOVE;
import static com.office.qljt.qljtoffice.constant.OptTypeConst.SAVE_OR_UPDATE;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.NORMAL;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Api(tags = "默认信息模块")
@RestController
@RequestMapping("/user/info")
public class UserDefaultInfoController {

    @Autowired
    private UserDefaultInfoService userDefaultInfoService;

    /**
     * 获取用户默认信息列表
     *
     * @return 获取用户默认信息列表
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "获取用户默认信息列表")
    @GetMapping("/all/{userId}")
    public Result<PageResult<UserDefaultInfoDTO>> listUserDefaultInfosDTO(@PathVariable("userId") String userId) {
        return Result.ok(userDefaultInfoService.listUserDefaultInfosDTO(userId));
    }

    /**
     * 保存/更新用户默认信息
     *
     * @param userDefaultInfoVO 用户默认信息
     * @return 保存/更新用户默认信息
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = NORMAL)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存/更新用户默认信息")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateUserDefaultInfo(@Valid @RequestBody UserDefaultInfoVO userDefaultInfoVO) {
        return userDefaultInfoService.saveOrUpdateUserDefaultInfo(userDefaultInfoVO);
    }

    /**
     * 删除用户默认信息
     *
     * @param deleteVO 用户id列表
     * @return 删除用户默认信息
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = NORMAL)
    @OptLog(optType = REMOVE)
    @ApiOperation(value = "删除用户默认信息")
    @DeleteMapping("/del")
    public Result<?> updateUserDefaultInfosDelete(@Valid @RequestBody DeleteVO deleteVO) {
        userDefaultInfoService.updateUserDefaultInfosDelete(deleteVO);
        return Result.ok();
    }

}
