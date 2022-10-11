package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.DispatchMethodsDTO;
import com.office.qljt.qljtoffice.service.DispatchMethodsService;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.DispatchMethodsVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.office.qljt.qljtoffice.constant.OptTypeConst.UPDATE;
import static com.office.qljt.qljtoffice.constant.RoleTypeConst.ADMIN;

/**
 * @author 续加仪
 * @date 2022/10/5
 */
@Api(tags = "发文登记方式模块")
@RestController
@RequestMapping("/dispatch/methods")
public class DispatchMethodsController {


    @Autowired
    private DispatchMethodsService dispatchMethodsService;

    /**
     * 查看发文登记方式列表
     *
     * @return 查看发文登记方式列表
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看发文登记方式列表")
    @GetMapping("/list")
    public Result<PageResult<DispatchMethodsDTO>> listDispatchMethodsDTO() {
        return Result.ok(dispatchMethodsService.listDispatchMethodsDTO());
    }

    /**
     * 保存发文登记方式
     *
     * @param dispatchMethodsVO 保存发文登记方式
     * @return 保存发文登记方式
     */

    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = UPDATE)
    @ApiOperation(value = "保存发文登记方式")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateDispatchMethods(@Valid @RequestBody DispatchMethodsVO dispatchMethodsVO) {
        dispatchMethodsService.savaOrUpdateDispatchMethods(dispatchMethodsVO);
        return Result.ok();
    }

    /**
     * 批量删除发文登记方式
     *
     * @param deleteVO 删除列表
     * @return 批量删除发文登记方式
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "批量删除发文登记方式")
    @DeleteMapping("/del")
    public Result<?> updateDispatchMethodsDelete(@Valid @RequestBody StatusVO deleteVO) {
        dispatchMethodsService.updateDispatchMethodsDelete(deleteVO);
        return Result.ok();
    }


}
