package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.*;
import com.office.qljt.qljtoffice.entity.*;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.EmailVO;
import com.office.qljt.qljtoffice.vo.Result;

import java.util.List;

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
     * 删除邮箱
     *
     * @param deleteVO 邮箱id列表
     */
    void updateEmailDelete(DeleteVO deleteVO);

    /**
     * 保存/更新邮箱
     *
     * @param emailVO 邮箱信息
     * @return 结果
     */
    Result<?> savaOrUpdateEmail(EmailVO emailVO);

    /**
     * 发送邮件
     *
     * @param subject 主题
     * @param content 内容
     * @return 返回值
     */
    Result<?> sendEmail(String email, String subject, String content);

    /**
     * 发文登记邮箱通知
     *
     * @param dispatchRecords    发文登记信息
     * @param dispatchMethodsDTO 发文登记方式信息
     * @param userDTO            用户信息
     */
    void sendEmail(DispatchRecords dispatchRecords, DispatchMethodsDTO dispatchMethodsDTO, UserDTO userDTO);

    /**
     * 物资请领邮箱通知
     *
     * @param goodsRecords 物资请领信息
     * @param goods        物资
     * @param userDTO      用户信息
     */
    void sendEmail(GoodsRecords goodsRecords, Goods goods, UserDTO userDTO);

    /**
     * 印信申请邮箱通知
     *
     * @param sealRecords 印信申请信息
     * @param sealDTO     公章信息
     * @param userDTO     用户信息
     */
    void sendEmail(SealRecords sealRecords, SealDTO sealDTO, UserDTO userDTO);

    /**
     * 设备借用邮箱通知
     *
     * @param suppliesRecords     设备借用信息
     * @param suppliesCategoryDTO 分类
     * @param userDTO             用户信息
     */
    void sendEmail(SuppliesRecords suppliesRecords, SuppliesCategoryDTO suppliesCategoryDTO, UserDTO userDTO);

    /**
     * 会场预约邮箱通知
     *
     * @param spaceRecords 会场预约信息
     * @param spaceDTO     会场信息
     * @param userDTO      用户信息
     */
    void sendEmail(SpaceRecords spaceRecords, SpaceDTO spaceDTO, UserDTO userDTO);

    /**
     * 发送全局邮件通知
     *
     * @param subject        主题
     * @param content        内容
     * @param attachmentList 附件
     */
    void sendEmailNotice(String subject, String content, List<EmailAttachmentDTO> attachmentList);

}
