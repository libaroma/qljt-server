package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.dto.SduDTO;
import com.office.qljt.qljtoffice.service.SduService;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SduVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Api(tags = "学号信息模块")
@RestController
@RequestMapping("/sdu")
public class SduController {

    @Autowired
    private SduService sduService;

    /**
     * 获取学号列表
     *
     * @return 获取学号列表
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "获取学号列表")
    @GetMapping("/list")
    public Result<PageResult<SduDTO>> listSdusDTO() {
        return Result.ok(sduService.listSdusDTO());
    }

    /**
     * 获取全部学号列表
     *
     * @return 获取全部学号列表
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "获取全部学号列表")
    @GetMapping("/all")
    public Result<PageResult<SduDTO>> listAllSdusDTO() {
        return Result.ok(sduService.listAllSdusDTO());
    }

    /**
     * 保存学号信息
     *
     * @param sduVO 学号信息
     * @return 保存学号信息
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "保存学号信息")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSdu(@Valid @RequestBody SduVO sduVO) {
        sduService.saveOrUpdateSdu(sduVO);
        return Result.ok();
    }


    /**
     * 批量删除学号信息
     *
     * @param deleteVO 学号信息
     * @return 批量删除学号信息
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "删除学号信息")
    @DeleteMapping("/del")
    public Result<?> updateSdusDelete(@Valid @RequestBody StatusVO deleteVO) {
        sduService.updateSdusDelete(deleteVO);
        return Result.ok();
    }
}
