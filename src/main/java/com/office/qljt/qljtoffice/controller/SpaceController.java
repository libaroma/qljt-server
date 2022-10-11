package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.SpaceDTO;
import com.office.qljt.qljtoffice.service.SpaceService;
import com.office.qljt.qljtoffice.vo.StatusVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import com.office.qljt.qljtoffice.vo.SpaceVO;
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
@Api(tags = "会场信息管理")
@RestController
@RequestMapping("/space")
public class SpaceController {

    @Autowired
    private SpaceService spaceService;

    /**
     * 获取会场列表
     *
     * @return 获取会场列表
     */
    @CheckUserAuth(role = NORMAL)
    @ApiOperation(value = "获取会场列表")
    @GetMapping("/all")
    public Result<PageResult<SpaceDTO>> listSpacesDTO() {
        return Result.ok(spaceService.listSpacesDTO());
    }

    /**
     * 保存/更新会场
     *
     * @param spaceVO 会场信息
     * @return 保存/更新会场
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = SAVE_OR_UPDATE)
    @ApiOperation("保存/更新会场")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateSpace(@Valid @RequestBody SpaceVO spaceVO) {
        spaceService.saveOrUpdateSpace(spaceVO);
        return Result.ok();
    }

    /**
     * 删除会场
     *
     * @param statusVO 会场信息
     * @return 删除会场
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = REMOVE)
    @ApiOperation("删除会场")
    @DeleteMapping("/del")
    public Result<?> saveOrUpdateSpace(@Valid @RequestBody StatusVO statusVO) {
        spaceService.updateSpaceDelete(statusVO);
        return Result.ok();
    }

}
