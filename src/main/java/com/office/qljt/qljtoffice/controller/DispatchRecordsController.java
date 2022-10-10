package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.DispatchRecordsDTO;
import com.office.qljt.qljtoffice.service.DispatchRecordsService;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.DispatchRecordsVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
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
 * @date 2022/10/6
 */
@Api(tags = "发文登记模块")
@RestController
@RequestMapping("/dispatch/records")
public class DispatchRecordsController {
    @Autowired
    private DispatchRecordsService dispatchRecordService;

    /**
     * 查看发文登记历史
     *
     * @return 查看发文登记历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看发文登记历史")
    @GetMapping("/list")
    public Result<PageResult<DispatchRecordsDTO>> listDispatchRecordsDTO() {
        return Result.ok(dispatchRecordService.listDispatchRecordsDTO());
    }

    /**
     * 查看全部发文登记历史
     *
     * @return 查看全部发文登记历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看全部发文登记历史")
    @GetMapping("/all")
    public Result<PageResult<DispatchRecordsDTO>> listAllDispatchRecordsDTO() {
        return Result.ok(dispatchRecordService.listAllDispatchRecordsDTO());
    }

    /**
     * 条件查询发文登记历史
     *
     * @return 条件查询发文登记历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "条件查询发文登记历史")
    @GetMapping("/condition")
    public Result<PageResult<DispatchRecordsDTO>> listAllSpaceRecordsDTOByCondition(ConditionVO conditionVO) {
        return Result.ok(dispatchRecordService.listAllSpaceRecordsDTOByCondition(conditionVO));
    }
    /**
     * 保存或更新发文登记
     *
     * @param dispatchRecordVO 发文登记信息
     * @return 保存或更新发文登记
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存或更新发文登记")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateDispatchRecord(@Valid @RequestBody DispatchRecordsVO dispatchRecordVO) {
        return dispatchRecordService.saveOrUpdateDispatchRecords(dispatchRecordVO);
    }
}
