package com.office.qljt.qljtoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author  续加仪
 * @date 2022-10-06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuppliesRecordsDTO {

	/**
	 * id
	 */
	private String id;


	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 单位
	 */
	private String company;

	/**
	 * 设备名称
	 */
	private String name;

	/**
	 * 设备分类
	 */
	private String suppliesCategory;

	/**
	 * 原因描述
	 */
	private String description;

	/**
	 * 照片
	 */
	private String cover;

	/**
	 * 电话
	 */
	private String contact;

	/**
	 * 数量
	 */
	private Long count;

	/**
	 * 是否归还/0:未还，1：已还未确认，2：已还
	 */
	private Long isReturn;

	/**
	 * 时间
	 */
	private Date time;

	/**
	 * 归还时间
	 */
	private Date returnTime;

	/**
	 * userInfo
	 */
	private UserDTO userInfo;

	/**
	 * suppliesCategoryInfo
	 */
	private SuppliesCategoryDTO suppliesCategoryInfo;

}
