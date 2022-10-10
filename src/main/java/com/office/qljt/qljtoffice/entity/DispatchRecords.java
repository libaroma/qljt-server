package com.office.qljt.qljtoffice.entity;

import lombok.Data;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
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
@TableName("tb_dispatch_records")
public class DispatchRecords {

	/**
	 * id
	 */
	@TableId(value = "id")
	private String id;


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
	 * 签发领导
	 */
	private String confirmUser;

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
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date time;

}
