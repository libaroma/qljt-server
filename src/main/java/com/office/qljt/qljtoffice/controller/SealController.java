package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.SealDTO;
import com.office.qljt.qljtoffice.service.SealService;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.OptTypeConst.REMOVE;
import static com.office.qljt.qljtoffice.constant.OptTypeConst.UPDATE;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.NORMAL;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Api(tags = "公章信息模块")
@RestController
@RequestMapping("/seal")
public class SealController {


    @Autowired
    private SealService sealService;

    /**
     * 查看公章列表
     *
     * @return 查看公章列表
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "查看公章列表")
    @GetMapping("/list")
    public Result<PageResult<SealDTO>> listSealsDTO() {
        return Result.ok(sealService.listSealsDTO());
    }

    /**
     * 保存公章
     *
     * @param sealVO 保存公章
     * @return 保存公章
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = UPDATE)
    @ApiOperation(value = "保存公章")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSeal(@Valid @RequestBody SealVO sealVO) {
        sealService.savaOrUpdateSeal(sealVO);
        return Result.ok();
    }

    /**
     * 批量删除公章
     *
     * @param deleteVO 删除列表
     * @return 批量删除公章
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = REMOVE)
    @ApiOperation(value = "批量删除公章")
    @DeleteMapping("/del")
    public Result<?> updateSealDelete(@Valid @RequestBody DeleteVO deleteVO) {
        sealService.updateSealDelete(deleteVO);
        return Result.ok();
    }


}
