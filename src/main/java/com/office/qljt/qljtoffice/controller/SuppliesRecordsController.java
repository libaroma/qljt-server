package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.SuppliesRecordsDTO;
import com.office.qljt.qljtoffice.service.SuppliesRecordsService;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SuppliesRecordsVO;
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
@Api(tags = "设备借用模块")
@RestController
@RequestMapping("/supplies/records")
public class SuppliesRecordsController {

    @Autowired
    private SuppliesRecordsService suppliesRecordsService;

    /**
     * 查看设备借用历史
     *
     * @return 查看设备借用历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "查看设备借用历史")
    @GetMapping("/list")
    public Result<PageResult<SuppliesRecordsDTO>> listSuppliesRecordsDTO() {
        return Result.ok(suppliesRecordsService.listSuppliesRecordsDTO());
    }

    /**
     * 查看全部设备借用历史
     *
     * @return 查看全部设备借用历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看全部设备借用历史")
    @GetMapping("/all")
    public Result<PageResult<SuppliesRecordsDTO>> listAllSuppliesRecordsDTO() {
        return Result.ok(suppliesRecordsService.listAllSuppliesRecordsDTO());
    }

    /**
     * 条件查询设备借用历史
     *
     * @return 条件查询设备借用历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "条件查询设备借用历史")
    @GetMapping("/condition")
    public Result<PageResult<SuppliesRecordsDTO>> listSpaceRecordsDTOByCondition(ConditionVO conditionVO) {
        return Result.ok(suppliesRecordsService.listSuppliesRecordsDTOByCondition(conditionVO));
    }

    /**
     * 保存或更新设备借用
     *
     * @param suppliesRecordVO 设备借用信息
     * @return 保存或更新设备借用
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = NORMAL)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存或更新设备借用")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSuppliesRecords(@Valid @RequestBody SuppliesRecordsVO suppliesRecordVO) {
        return suppliesRecordsService.saveOrUpdateSuppliesRecords(suppliesRecordVO);
    }
    /**
     * 归还设备借用
     *
     * @param id 设备借用信息id
     * @return 归还设备借用
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = NORMAL)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "归还设备借用")
    @PostMapping("/return/{id}")
    public Result<?> returnSuppliesRecords(@PathVariable("id")String id) {
        return suppliesRecordsService.returnSuppliesRecords(id);
    }
    /**
     * 归还设备借用审核
     *
     * @param id 设备借用信息id
     * @return 归还设备借用审核
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "审核归还申请")
    @PostMapping("/examine/{id}")
    public Result<?> examineSuppliesRecords(@PathVariable("id")String id) {
        return suppliesRecordsService.examineSuppliesRecords(id);
    }
}
