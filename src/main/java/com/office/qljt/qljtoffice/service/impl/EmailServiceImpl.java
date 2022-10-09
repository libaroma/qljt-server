package com.office.qljt.qljtoffice.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.EmailDao;
import com.office.qljt.qljtoffice.dto.*;
import com.office.qljt.qljtoffice.entity.*;
import com.office.qljt.qljtoffice.service.EmailService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.EmailVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import com.office.qljt.qljtoffice.vo.Result;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.office.qljt.qljtoffice.constant.MQPrefixConst.EMAIL_EXCHANGE;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
@Service
public class EmailServiceImpl extends ServiceImpl<EmailDao, Email> implements EmailService {


    @Autowired
    private IdWorker idWorker;

    @Autowired
    private EmailDao emailDao;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Result<?> sendEmail(String email, String subject, String content) {

//        List<EmailAttachmentDTO> attachment = new ArrayList<>();
//        attachment.add(EmailAttachmentDTO.builder()
//                .id("p0001").url("E:\\Mirror\\Projects\\Intellij\\qljt-office\\qljt-office\\src\\main\\resources\\225619-16604889791536.jpg").build());

        CompletableFuture.runAsync(() -> send(email, subject, content + "<img src='cid:p0001'/>", null));
        return Result.ok("发送成功!");
    }

    @Override
    public PageResult<EmailDTO> listEmailsDTO() {
        return new PageResult<>(emailDao.listEmailsDTO(), emailDao.selectCount(null));
    }

    @Override
    public Result<?> savaOrUpdateEmail(EmailVO emailVO) {
        Email email = BeanCopyUtils.copyObject(emailVO, Email.class);
        if (email.getStatus() == null) email.setStatus(1L);
        if (TextUtils.isEmpty(email.getId())) {
            email.setId(idWorker.nextId() + "");
            EmailDTO emailDTOByEmail = emailDao.getEmailDTOByEmail(email.getEmail());
            if (emailDTOByEmail != null) return Result.fail("邮箱已存在！");
        }
        this.saveOrUpdate(email);
        return Result.ok(email);
    }


    @Override
    public void updateEmailDelete(DeleteVO deleteVO) {
        List<Email> emailList = deleteVO.getIdList().stream()
                .map(id -> Email.builder()
                        .id(id)
                        .status(deleteVO.getStatus())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(emailList);
    }

    public static final String prefix_d = "<div class=\"line\">";
    public static final String endfix_d = "</div>\n";
    public static final String style = "<style>h4{text-decoration:underline;}.line{display:flex;flex-direction:row;align-items:center;}</style>";

    @Override
    public void sendEmail(DispatchRecords dispatchRecords, DispatchMethodsDTO dispatchMethodsDTO, UserDTO userDTO) {
        String content = "<h2>这是一个登记发文信息通知</h2>\n" +
                prefix_d + " <h4>" + userDTO.getSduInfo().getSduName() + " </h4>登记了一个发文信息" + endfix_d +
                prefix_d + " 他登记的发文标题为<h4>" + dispatchRecords.getTitle() + "</h4>" + endfix_d +
                prefix_d + " 他登记的发文字号为" + endfix_d + " \n" +
                prefix_d + " <h4 style=\"font-family:fangsong;text-align:center;\">" + dispatchMethodsDTO.getOffice() + "〔" + dispatchRecords.getYear() + "〕" + dispatchRecords.getSequence() + "号</h4>" + endfix_d +
                prefix_d + " 他登记的加盖公章为<h4>" + dispatchMethodsDTO.getSealInfo().getSeal() + "</h4>" + endfix_d +
                prefix_d + " 他登记的发文日期为<h4>" + dispatchRecords.getDate() + "</h4>" + endfix_d +
                prefix_d + " 他登记的成文方式为<h4>" + dispatchMethodsDTO.getMethod() + " </h4 > " + endfix_d +
                (dispatchRecords.getMeetingDate() == null ? prefix_d + "\"他登记的会议日期为<h4>\"" + dispatchRecords.getMeetingDate() + "</h4>" + endfix_d : "") +
                style;
        sendEmailNotice("登记发文信息通知", content, null);
    }

    @Override
    public void sendEmail(GoodsRecords goodsRecords, Goods goods, UserDTO userDTO) {
        String content = "<h2>这是一个物资领取通知</h2>\n" +
                prefix_d + " <h4>" + userDTO.getSduInfo().getSduName() + " </h4>领取了<h4>" + goodsRecords.getCount() + " </h4>个(包、本)<h4>" + goods.getName() + "</h4>" + endfix_d +
                prefix_d + " 现在仓库剩余<h4>" + goods.getLeftCount() + "</h4>个(包、本)<h4>" + goods.getName() + "</h4>" + endfix_d +
                prefix_d + " 领用原因是<h4>" + goodsRecords.getDescription() + "</h4>" + endfix_d +
                prefix_d + " 联系方式是<h4>" + goodsRecords.getContact() + "</h4>" + endfix_d +
                prefix_d + " 他的单位是<h4>" + goodsRecords.getCompany() + "</h4>" + endfix_d +
                style;


        sendEmailNotice("物资领取通知", content, null);
    }

    @Override
    public void sendEmail(SealRecords sealRecords, SealDTO sealDTO, UserDTO userDTO) {
        String content = "<h2>这是一个盖章通知通知</h2>\n" +
                prefix_d + " <h4>" + userDTO.getSduInfo().getSduName() + " </h4>盖了<h4>" + sealDTO.getSeal() + " </h4>" + endfix_d +
                prefix_d + " 盖章原因是<h4>" + sealRecords.getDescription() + "</h4>" + endfix_d +
                prefix_d + " 批准人是<h4>" + sealRecords.getConfirmUser() + "</h4>" + endfix_d +
                prefix_d + " 联系方式是<h4>" + sealRecords.getContact() + "</h4>" + endfix_d +
                prefix_d + " 他的单位是<h4>" + sealRecords.getCompany() + "</h4>" + endfix_d +
                style;
        sendEmailNotice("盖章通知", content, null);
    }

    @Value("${path}")
    public String path;

    @Override
    public void sendEmail(SuppliesRecords suppliesRecords, SuppliesCategoryDTO suppliesCategoryDTO, UserDTO userDTO) {
        String content = "<h2>这是一个物资借出通知</h2>\n" +
                prefix_d + " <h4>" + userDTO.getSduInfo().getSduName() + " </h4>借用了<h4>" + suppliesRecords.getCount() + " </h4>个(包、本)<h4>" + suppliesRecords.getName() + "</h4>" + endfix_d +
                prefix_d + " 物资分类是<h4>" + suppliesCategoryDTO.getCategory() + "</h4>" + endfix_d +
                prefix_d + " 借用原因是<h4>" + suppliesRecords.getDescription() + "</h4>" + endfix_d +
                prefix_d + " 联系方式是<h4>" + suppliesRecords.getContact() + "</h4>" + endfix_d +
                prefix_d + " 他的单位是<h4>" + suppliesRecords.getCompany() + "</h4>" + endfix_d +
                "<p><img src='cid:p0001'/></p>" +
                style;

        List<EmailAttachmentDTO> attachment = new ArrayList<>();
        //cover:https://qljt.hyz.cool/files/912bb06dd394422a06f99de95c135c8d.jpg
        String[] split = suppliesRecords.getCover().split("/");
        attachment.add(EmailAttachmentDTO.builder()
                .id("p0001").url(path+split[split.length-1]).build());
        sendEmailNotice("物资借出通知", content, attachment);
    }

    @Override
    public void sendEmail(SpaceRecords spaceRecords, SpaceDTO spaceDTO, UserDTO userDTO) {
        String[] week = "日一二三四五六".split("");
        String content = "<h2>这是一个会场预约通知</h2>\n" +
                prefix_d + " <h4>" + userDTO.getSduInfo().getSduName() + " </h4>预约了<h4>" + spaceDTO.getName() + " </h4>" + endfix_d +
                prefix_d + " 预约的时间段为<h4>" + spaceRecords.getDate() + " 星期" + week[Integer.parseInt(spaceRecords.getDay())] + " " + spaceRecords.getBegin() + "-" + spaceRecords.getEnd() + "</h4>" + endfix_d +
                prefix_d + " 预约原因是<h4>" + spaceRecords.getDescription() + "</h4>" + endfix_d +
                prefix_d + " 批准人是<h4>" + spaceRecords.getConfirmUser() + "</h4>" + endfix_d +
                prefix_d + " 联系方式是<h4>" + spaceRecords.getContact() + "</h4>" + endfix_d +
                prefix_d + " 他的单位是<h4>" + spaceRecords.getCompany() + "</h4>" + endfix_d +
                prefix_d + " 预计人数是<h4>" + spaceRecords.getCompany() + "</h4>" + endfix_d +
                prefix_d + " <h4>" + (spaceRecords.getMedia() > 0 ? "" : "不") + "需要</h4>使用媒体设备" + endfix_d +
                style;
        sendEmailNotice("会场预约通知", content, null);

    }

    /**
     * 通知评论用户
     *
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendEmailNotice(String subject, String content, List<EmailAttachmentDTO> attachmentList) {
        // 查询回复用户邮箱号
        emailDao.listEmailsDTO()
                .forEach(emailDTO -> CompletableFuture.runAsync(() -> send(emailDTO.getEmail(), subject, content, attachmentList)));
    }

    public void send(String email, String subject, String content, List<EmailAttachmentDTO> attachmentList) {
        if (StringUtils.isNotBlank(email)) {
            // 发送消息
            EmailNoticeInfoDTO emailDTO = EmailNoticeInfoDTO.builder()
                    .email(email)
                    .subject(subject)
                    .attachmentList(attachmentList)
                    .content(content).build();
            //加入队列
            rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        }
    }

}
