package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.service.SduService;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SduVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Api(tags = "学号信息模块")
@RestController
public class SduController {

    @Autowired
    private SduService sduService;

    /**
     * 保存学号信息
     *
     * @param sduVO 学号信息
     * @return 保存学号信息
     */
    @ApiOperation(value = "保存学号信息")
    @PostMapping("/sdu/sou")
    public Result<?> saveOrUpdateSdu(@Valid @RequestBody SduVO sduVO) {
        sduService.saveOrUpdate(sduVO);
        return Result.ok();
    }


    /**
     * 批量删除学号信息
     *
     * @param sduVO 学号信息
     * @return 保存学号信息
     */
    @ApiOperation(value = "保存学号信息")
    @PostMapping("/sdu/sou")
    public Result<?> deleteSdu(@Valid @RequestBody DeleteVO deleteVO) {
        sduService.deleteSdu(deleteVO);
        return Result.ok();
    }



}
