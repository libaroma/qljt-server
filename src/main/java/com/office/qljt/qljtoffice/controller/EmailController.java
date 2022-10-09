package com.office.qljt.qljtoffice.controller;

import com.office.qljt.qljtoffice.annotation.CheckTooFrequentCommit;
import com.office.qljt.qljtoffice.annotation.CheckUserAuth;
import com.office.qljt.qljtoffice.annotation.OptLog;
import com.office.qljt.qljtoffice.dto.EmailDTO;
import com.office.qljt.qljtoffice.service.EmailService;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.EmailVO;
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
@Api(tags = "邮箱信息模块")
@RestController
@RequestMapping("/email")
public class EmailController {


    @Autowired
    private EmailService emailService;

    /**
     * 查看邮箱列表
     *
     * @return 查看邮箱列表
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "发送邮件")
    @GetMapping("/send")
    public Result<?> sendEmail(@RequestParam("email") String email, @RequestParam("subject") String subject, @RequestParam("content") String content) {
        return emailService.sendEmail(email, subject, content);
    }

    /**
     * 查看邮箱列表
     *
     * @return 查看邮箱列表
     */
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "查看邮箱列表")
    @GetMapping("/list")
    public Result<PageResult<EmailDTO>> listEmailsDTO() {
        return Result.ok(emailService.listEmailsDTO());
    }

    /**
     * 保存邮箱
     *
     * @param emailVO 保存邮箱
     * @return 保存邮箱
     */

    @CheckUserAuth(role = ADMIN)
    @OptLog(optType = UPDATE)
    @ApiOperation(value = "保存邮箱")
    @PostMapping("/sou")
    public Result<?> saveOrUpdateEmail(@Valid @RequestBody EmailVO emailVO) {
        return emailService.savaOrUpdateEmail(emailVO);
    }

    /**
     * 批量删除邮箱
     *
     * @param deleteVO 删除列表
     * @return 批量删除邮箱
     */
    @CheckTooFrequentCommit
    @CheckUserAuth(role = ADMIN)
    @ApiOperation(value = "批量删除邮箱")
    @DeleteMapping("/del")
    public Result<?> updateEmailDelete(@Valid @RequestBody DeleteVO deleteVO) {
        emailService.updateEmailDelete(deleteVO);
        return Result.ok();
    }


}
