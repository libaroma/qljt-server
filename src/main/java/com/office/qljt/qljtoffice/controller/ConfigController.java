package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.ConfigDTO;
import com.office.qljt.qljtoffice.service.ConfigService;
import com.office.qljt.qljtoffice.vo.ConfigVO;
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
@Api(tags = "配置信息模块")
@RestController
@RequestMapping("/config")
public class ConfigController {


    @Autowired
    private ConfigService configService;

    /**
     * 查看配置列表
     *
     * @return 查看配置列表
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "查看配置列表")
    @GetMapping("/list")
    public Result<PageResult<ConfigDTO>> listConfigsDTO() {
        return Result.ok(configService.listConfigsDTO());
    }

    /**
     * 获取配置
     *
     * @return 获取配置
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "获取配置")
    @GetMapping("/get/{configId}")
    public Result<ConfigDTO> getConfigDTOByConfigId(@PathVariable("configId") String configId) {
        return Result.ok(configService.getConfigDTOByConfigId(configId));
    }

    /**
     * 保存配置
     *
     * @param configVO 保存配置
     * @return 保存配置
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation(value = "保存配置")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateConfig(@Valid @RequestBody ConfigVO configVO) {
        configService.savaOrUpdateConfig(configVO);
        return Result.ok();
    }


}
