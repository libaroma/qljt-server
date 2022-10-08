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

import static com.office.qljt.qljtoffice.constant.OptTypeConst.REMOVE;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.*;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Api(tags = "设备借用模块")
@RestController
@RequestMapping("/supplies/records")
public class SuppliesRecordsController {

    @Autowired
    private SuppliesRecordsService suppliesRecordService;

    /**
     * 查看设备借用历史
     *
     * @return 查看设备借用历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "查看设备借用历史")
    @GetMapping("/list")
    public Result<PageResult<SuppliesRecordsDTO>> listSuppliesRecordsDTO() {
        return Result.ok(suppliesRecordService.listSuppliesRecordsDTO());
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
        return Result.ok(suppliesRecordService.listAllSuppliesRecordsDTO());
    }

    /**
     * 条件查询设备借用历史
     *
     * @return 条件查询设备借用历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "条件查询设备借用历史")
    @GetMapping("/condition")
    public Result<PageResult<SuppliesRecordsDTO>> listSpaceRecordsDTOByCondition(ConditionVO conditionVO) {
        return Result.ok(suppliesRecordService.listSuppliesRecordsDTOByCondition(conditionVO));
    }

    /**
     * 保存或更新设备借用
     *
     * @param suppliesRecordVO 设备借用信息
     * @return 保存或更新设备借用
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = NORMAL)
    @OptLog(optType = REMOVE)
    @ApiOperation(value = "保存或更新设备借用")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSuppliesRecord(@Valid @RequestBody SuppliesRecordsVO suppliesRecordVO) {
        return suppliesRecordService.saveOrUpdateSuppliesRecord(suppliesRecordVO);
    }
}
