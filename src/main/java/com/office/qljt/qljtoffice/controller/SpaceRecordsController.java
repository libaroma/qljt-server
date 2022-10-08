package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.SpaceRecordsDTO;
import com.office.qljt.qljtoffice.service.SpaceRecordsService;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SpaceRecordsVO;
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
@Api(tags = "会场预约模块")
@RestController
@RequestMapping("/space/records")
public class SpaceRecordsController {

    @Autowired
    private SpaceRecordsService spaceRecordService;

    /**
     * 查看会场预约历史
     *
     * @return 查看会场预约历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "查看会场预约历史")
    @GetMapping("/list")
    public Result<PageResult<SpaceRecordsDTO>> listSpaceRecordsDTO() {
        return Result.ok(spaceRecordService.listSpaceRecordsDTO());
    }

    /**
     * 查看全部会场预约历史
     *
     * @return 查看全部会场预约历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看全部会场预约历史")
    @GetMapping("/all")
    public Result<PageResult<SpaceRecordsDTO>> listAllSpaceRecordsDTO() {
        return Result.ok(spaceRecordService.listAllSpaceRecordsDTO());
    }

    /**
     * 条件查询会场预约历史
     *
     * @return 条件查询会场预约历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "条件查询会场预约历史")
    @GetMapping("/condition")
    public Result<PageResult<SpaceRecordsDTO>> listSpaceRecordsDTOByCondition(ConditionVO conditionVO) {
        return Result.ok(spaceRecordService.listSpaceRecordsDTOByCondition(conditionVO));
    }

    /**
     * 保存或更新会场预约
     *
     * @param spaceRecordVO 会场预约信息
     * @return 保存或更新会场预约
     */
    @CheckTooFrequentCommit
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存或更新会场预约")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSpaceRecord(@Valid @RequestBody SpaceRecordsVO spaceRecordVO) {
        return spaceRecordService.saveOrUpdateSpaceRecords(spaceRecordVO);
    }
}
