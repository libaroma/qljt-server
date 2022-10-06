package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.entity.Sdu;
import com.office.qljt.qljtoffice.vo.DeleteVO;
import com.office.qljt.qljtoffice.vo.SduVO;

/**
 * @author 续加仪
 * @date 2022/10/6
 */
public interface SduService extends IService<Sdu> {
    void saveOrUpdate(SduVO sduVO);

    void deleteSdu(DeleteVO deleteVO);
}
