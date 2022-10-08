package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.dto.GoodsDTO;
import com.office.qljt.qljtoffice.service.GoodsService;
import com.office.qljt.qljtoffice.vo.DeleteVO;
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
@Api(tags = "物资信息管理")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 获取物资列表
     *
     * @return 获取物资列表
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "获取物资列表")
    @GetMapping("/all")
    public Result<PageResult<GoodsDTO>> listGoodsDTO() {
        return Result.ok(goodsService.listGoodsDTO());
    }

    /**
     * 保存/更新物资
     *
     * @param goodsDTO 物资信息
     * @return 保存/更新物资
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @ApiOperation("保存/更新物资")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateGoods(@Valid @RequestBody GoodsDTO goodsDTO) {
        goodsService.saveOrUpdateGoods(goodsDTO);
        return Result.ok();
    }

    /**
     * 删除物资
     *
     * @param deleteVO 物资信息
     * @return 物资会场
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @ApiOperation("删除物资")
    @PostMapping("/del")
    public Result<?> saveOrUpdateGoods(@Valid @RequestBody DeleteVO deleteVO) {
        goodsService.updateGoodsDelete(deleteVO);
        return Result.ok();
    }

}
