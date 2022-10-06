package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
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

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Api(tags = "公章模块")
@RestController
public class SealController {


    @Autowired
    private SealService sealService;

    /**
     * 查看公章列表
     *
     * @return 查看公章列表
     */
    @ApiOperation(value = "查看公章列表")
    @GetMapping("/seal/list")
    public Result<PageResult<SealDTO>> listSeals() {
        return Result.ok(sealService.listSeals());
    }

    /**
     * 保存公章
     *
     * @param sealVO 保存公章
     * @return 保存公章
     */
//    @OptLog(optType = UPDATE)
    @ApiOperation(value = "保存公章")
    @PostMapping("/seal/sou")
    public Result<?> saveOrUpdateSeals(@Valid @RequestBody SealVO sealVO) {
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
    @ApiOperation(value = "批量删除公章")
    @DeleteMapping("/seal/del")
    public Result<?> dealSeals(@Valid @RequestBody DeleteVO deleteVO) {
        sealService.updateSealDelete(deleteVO);
        return Result.ok();
    }


}
