package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.dto.SealRecordDTO;
import com.office.qljt.qljtoffice.service.SealRecordService;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SealRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Api(tags = "印信申请模块")
@RestController
public class SealRecordsController {
    @Autowired
    private SealRecordService sealRecordService;

    /**
     * 查看印信申请历史
     *
     * @return 查看印信申请历史
     */
    @ApiOperation(value = "查看全部印信申请历史")
    @GetMapping("/seal/record/list")
    public Result<PageResult<SealRecordDTO>> listSealRecords() {
        return Result.ok(sealRecordService.listSealRecords());
    }

    /**
     * 查看印信申请历史
     *
     * @return 查看印信申请历史
     */
    @ApiOperation(value = "查看全部印信申请历史")
    @GetMapping("/seal/record/all")
    public Result<PageResult<SealRecordDTO>> listAllSealRecords() {
        return Result.ok(sealRecordService.listAllSealRecords());
    }

    /**
     * 保存或更新印信申请
     *
     * @param sealRecordVO 印信申请信息
     * @return 保存或更新印信申请
     */
    @CheckTooFrequentCommit
    @ApiOperation(value = "保存或更新印信申请")
    @PostMapping("/seal/record/sou")
    public Result<?> saveOrUpdateSealRecord(@Valid @RequestBody SealRecordVO sealRecordVO) {
        return sealRecordService.saveOrUpdateSealRecord(sealRecordVO);
    }
}
