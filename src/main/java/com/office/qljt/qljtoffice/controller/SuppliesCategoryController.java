package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.SuppliesCategoryDTO;
import com.office.qljt.qljtoffice.service.SuppliesCategoryService;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.OptTypeConst.REMOVE;
import static com.office.qljt.qljtoffice.constant.OptTypeConst.SAVE_OR_UPDATE;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.NORMAL;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Api(tags = "设备分类管理")
@RestController
@RequestMapping("/supplies/category")
public class SuppliesCategoryController {

    @Autowired
    private SuppliesCategoryService suppliesCategoryService;

    /**
     * 获取设备分类列表
     *
     * @return 获取设备分类列表
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "获取设备分类列表")
    @GetMapping("/all")
    public Result<PageResult<SuppliesCategoryDTO>> listSuppliesCategoryDTO() {
        return Result.ok(suppliesCategoryService.listSuppliesCategoryDTO());
    }

    /**
     * 保存/更新设备分类
     *
     * @param suppliesCategoryDTO 设备分类信息
     * @return 保存/更新设备分类
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation("保存/更新设备分类")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSuppliesCategory(@Valid @RequestBody SuppliesCategoryDTO suppliesCategoryDTO) {
        suppliesCategoryService.saveOrUpdateSuppliesCategory(suppliesCategoryDTO);
        return Result.ok();
    }

    /**
     * 删除设备分类
     *
     * @param deleteVO 设备分类信息
     * @return 设备分类会场
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = REMOVE)
    @ApiOperation("删除设备分类")
    @PostMapping("/del")
    public Result<?> saveOrUpdateSuppliesCategory(@Valid @RequestBody StatusVO deleteVO) {
        suppliesCategoryService.updateSuppliesCategoryDelete(deleteVO);
        return Result.ok();
    }

}
