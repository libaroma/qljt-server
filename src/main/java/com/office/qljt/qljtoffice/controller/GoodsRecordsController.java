package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.dto.GoodsRecordsDTO;
import com.office.qljt.qljtoffice.service.GoodsRecordsService;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.GoodsRecordsVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.NORMAL;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Api(tags = "物资请领模块")
@RestController
@RequestMapping("/goods/records")
public class GoodsRecordsController {

    @Autowired
    private GoodsRecordsService goodsRecordService;

    /**
     * 查看物资请领历史
     *
     * @return 查看物资请领历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "查看物资请领历史")
    @GetMapping("/list")
    public Result<PageResult<GoodsRecordsDTO>> listGoodsRecordsDTO() {
        return Result.ok(goodsRecordService.listGoodsRecordsDTO());
    }

    /**
     * 查看全部物资请领历史
     *
     * @return 查看全部物资请领历史
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看全部物资请领历史")
    @GetMapping("/all")
    public Result<PageResult<GoodsRecordsDTO>> listAllGoodsRecordsDTO() {
        return Result.ok(goodsRecordService.listAllGoodsRecordsDTO());
    }

    /**
     * 条件查询物资请领历史
     *
     * @return 条件查询物资请领历史
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "条件查询物资请领历史")
    @GetMapping("/condition")
    public Result<PageResult<GoodsRecordsDTO>> listGoodsRecordsDTOByCondition(ConditionVO conditionVO) {
        return Result.ok(goodsRecordService.listGoodsRecordsDTOByCondition(conditionVO));
    }

    /**
     * 保存或更新物资请领
     *
     * @param goodsRecordVO 物资请领信息
     * @return 保存或更新物资请领
     */
    @CheckTooFrequentCommit
    @ApiOperation(value = "保存或更新物资请领")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateGoodsRecord(@Valid @RequestBody GoodsRecordsVO goodsRecordVO) {
        return goodsRecordService.saveOrUpdateGoodsRecords(goodsRecordVO);
    }
}
