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
public class SpaceRecordsDTO {

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
	 * 批准人
	 */
	private String confirmUser;

	/**
	 * 单位
	 */
	private String company;

	/**
	 * 日期/星期
	 */
	private String day;

	/**
	 * 日期/年月日
	 */
	private String date;

	/**
	 * 开始时间
	 */
	private String begin;

	/**
	 * 结束时间
	 */
	private String end;

	/**
	 * 会场
	 */
	private String space;

	/**
	 * 原因描述
	 */
	private String description;

	/**
	 * 电话
	 */
	private String contact;

	/**
	 * 人数
	 */
	private Long count;

	/**
	 * 多媒体
	 */
	private Long media;

	/**
	 * 时间
	 */
	private Date time;

}
