package com.office.qljt.qljtoffice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.office.qljt.qljtoffice.dto.OperationLogDTO;
import com.office.qljt.qljtoffice.entity.OperationLog;
import com.office.qljt.qljtoffice.vo.ConditionVO;
import com.office.qljt.qljtoffice.vo.PageResult;

/**
 * 操作日志服务
 *
 * @author lib
 * @date 2021/07/29
 */
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 查询日志列表
     *
     * @param conditionVO 条件
     * @return 日志列表
     */
    PageResult<OperationLogDTO> listOperationLogs(ConditionVO conditionVO);

}
