package com.office.qljt.qljtoffice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.office.qljt.qljtoffice.dao.EmailDao;
import com.office.qljt.qljtoffice.dto.EmailDTO;
import com.office.qljt.qljtoffice.entity.Email;
import com.office.qljt.qljtoffice.service.EmailService;
import com.office.qljt.qljtoffice.utils.BeanCopyUtils;
import com.office.qljt.qljtoffice.utils.IdWorker;
import com.office.qljt.qljtoffice.utils.TextUtils;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.EmailVO;
import com.office.qljt.qljtoffice.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PageResult<EmailDTO> listEmailsDTO() {
        return new PageResult<>(emailDao.listEmailsDTO(), emailDao.selectCount(null));
    }

    @Override
    public void savaOrUpdateEmail(EmailVO emailVO) {
        Email email = BeanCopyUtils.copyObject(emailVO, Email.class);
        if (email.getStatus() == null) email.setStatus(1L);
        if (TextUtils.isEmpty(email.getId())) {
            email.setId(idWorker.nextId() + "");
            EmailDTO emailDTOByEmail = emailDao.getEmailDTOByEmail(email.getEmail());
            if (emailDao != null) return;
        }
        this.saveOrUpdate(email);
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
}
