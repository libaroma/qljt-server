package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.EmailDTO;
import com.office.qljt.qljtoffice.entity.Email;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.EmailVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface EmailService extends IService<Email> {


    /**
     * 获取邮箱列表
     *
     * @return 获取邮箱列表
     */
    PageResult<EmailDTO> listEmailsDTO();

    /**
     * 保存/更新邮箱
     *
     * @param emailVO 邮箱信息
     */
    void savaOrUpdateEmail(EmailVO emailVO);

    /**
     * 删除邮箱
     *
     * @param deleteVO 邮箱id列表
     */
    void updateEmailDelete(DeleteVO deleteVO);
}
