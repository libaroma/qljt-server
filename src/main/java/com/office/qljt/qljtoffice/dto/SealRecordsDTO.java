package com.office.qljt.qljtoffice.dto;

import lombok.Data;
import lombok.Builder;
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
public class SealRecordsDTO {

	/**
	 * id
	 */
	private String id;

	/**
	 * openid
	 */
	private String openid;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 单位
	 */
	private String company;

	/**
	 * 批准人
	 */
	private String confirmUser;

	/**
	 * 公章
	 */
	private String seal;

	/**
	 * 原因描述
	 */
	private String description;

	/**
	 * 电话
	 */
	private String contact;

	/**
	 * 时间
	 */
	private Date time;

	/**
	 * userInfo
	 */
	private UserDTO userInfo;

	/**
	 * sealInfo
	 */
	private SealDTO sealInfo;

}
