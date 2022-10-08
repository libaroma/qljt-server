package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.SealRecordsDTO;
import com.office.qljt.qljtoffice.service.SealRecordsService;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SealRecordsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.OptTypeConst.SAVE_OR_UPDATE;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.NORMAL;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Api(tags = "印信申请模块")
@RestController
@RequestMapping("/seal/records")
public class SealRecordsController {
    @Autowired
    private SealRecordsService sealRecordService;

    /**
     * 查看印信申请历史
     *
     * @return 查看印信申请历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "查看印信申请历史")
    @GetMapping("/list")
    public Result<PageResult<SealRecordsDTO>> listSealRecordsDTO() {
        return Result.ok(sealRecordService.listSealRecordsDTO());
    }

    /**
     * 查看印信申请历史
     *
     * @return 查看印信申请历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看全部印信申请历史")
    @GetMapping("/all")
    public Result<PageResult<SealRecordsDTO>> listAllSealRecordsDTO() {
        return Result.ok(sealRecordService.listAllSealRecordsDTO());
    }

    /**
     * 条件查询印信申请信息
     *
     * @return 条件查询印信申请信息
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "条件查询印信申请信息")
    @GetMapping("/condition")
    public Result<PageResult<SealRecordsDTO>> listSealRecordsDTOByCondition(ConditionVO conditionVO) {
        return Result.ok(sealRecordService.listSealRecordsDTOByCondition(conditionVO));
    }
    /**
     * 保存或更新印信申请
     *
     * @param sealRecordVO 印信申请信息
     * @return 保存或更新印信申请
     */
    @CheckTooFrequentCommit
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存或更新印信申请")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSealRecord(@Valid @RequestBody SealRecordsVO sealRecordVO) {
        return sealRecordService.saveOrUpdateSealRecords(sealRecordVO);
    }
}
