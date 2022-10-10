package com.office.qljt.qljtoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author 续加仪
 * @date 2022-10-06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    /**
     * id
     */
    private String id;


    /**
     * 学号/工号
     */
    private String sduId;

    /**
     * 昵称
     */
    private String userNickname;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 状态(1可用，0删除)
     */
    private Long status;

    /**
     * 角色(0普通，1管理员,2高级管理员)
     */
    private Long role;

    /**
     * 时间
     */
    private Date time;


    /**
     * sduInfo
     */
    private SduDTO sduInfo;

    /**
     * defaultInfoList
     */
    private List<UserDefaultInfoDTO> defaultInfoList;

}
