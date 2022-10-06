package com.office.qljt.qljtoffice.dto;

import lombok.Data;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author  续加仪
 * @date 2022-10-05
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
	 * openid
	 */
	private String openid;

	/**
	 * 学号/工号
	 */
	private String sduId;

	/**
	 * 物资类别
	 */
	private String category;

	/**
	 * 昵称
	 */
	private String userNickname;

	/**
	 * 头像
	 */
	private String userAvatar;

	/**
	 * 省份
	 */
	private String userProvince;

	/**
	 * 城市
	 */
	private String userCity;

	/**
	 * 国家
	 */
	private String userCountry;

	/**
	 * 语言
	 */
	private String userLanguage;

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

}
