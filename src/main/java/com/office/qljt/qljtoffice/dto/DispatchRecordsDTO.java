package com.office.qljt.qljtoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 续加仪
 * @date 2022-10-06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchRecordsDTO {

    /**
     * id
     */
    private String id;

    private String openid;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 方式
     */
    private String method;

    /**
     * 发文日期
     */
    private String date;

    /**
     * 会议日期
     */
    private String meetingDate;


    /**
     * 发文标题
     */
    private String title;

    /**
     * 年份
     */
    private Long year;

    /**
     * 序号
     */
    private Long sequence;

    /**
     * 时间
     */
    private Date time;


    /**
     * userInfo
     */
    private UserDTO userInfo;

    /**
     * spaceInfo
     */
    private DispatchMethodsDTO methodInfo;
}
