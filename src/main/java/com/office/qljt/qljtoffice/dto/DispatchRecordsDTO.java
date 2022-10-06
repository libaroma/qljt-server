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
public class DispatchRecordsDTO {

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
	 * 发文日期
	 */
	private String confirmDate;

	/**
	 * 方式
	 */
	private String method;

	/**
	 * 日期
	 */
	private String date;

	/**
	 * 会议日期
	 */
	private String meetingDate;

	/**
	 * 物资描述
	 */
	private String confirmUser;

	/**
	 * 原因描述
	 */
	private String title;

	/**
	 * 年份
	 */
	private Long year;

	/**
	 * 序号
	 */
	private Long index;

	/**
	 * 时间
	 */
	private Date time;

}
