package com.office.qljt.qljtoffice.consumer;

import com.alibaba.fastjson.JSON;
import com.office.qljt.qljtoffice.dto.EmailAttachmentDTO;
import com.office.qljt.qljtoffice.dto.EmailNoticeInfoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

import static com.office.qljt.qljtoffice.constant.MQPrefixConst.EMAIL_QUEUE;

/**
 * 通知邮箱
 *
 * @author lib
 * @date 2021/06/13
 * @since 1.0.0
 **/
@Component
@RabbitListener(queues = EMAIL_QUEUE)
public class EmailConsumer {

    /**
     * 邮箱号
     */
    @Value("${spring.mail.username}")
    private String email;

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitHandler
    public void process(byte[] data) {
        EmailNoticeInfoDTO emailNoticeInfoDTO = JSON.parseObject(new String(data), EmailNoticeInfoDTO.class);
        //简单邮件
        //SimpleMailMessage message = new SimpleMailMessage();
        //复杂邮件
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage,true);
            mimeMessageHelper.setFrom(email);
            List<EmailAttachmentDTO> attachmentList = emailNoticeInfoDTO.getAttachmentList();
            mimeMessageHelper.setTo(emailNoticeInfoDTO.getEmail());
            mimeMessageHelper.setSubject(emailNoticeInfoDTO.getSubject());
            mimeMessageHelper.setText(emailNoticeInfoDTO.getContent(),true);
            if (attachmentList != null) for (EmailAttachmentDTO attachment : attachmentList)
                mimeMessageHelper.addInline(attachment.getId(), new File(attachment.getUrl()));
            javaMailSender.send(mimeMailMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

