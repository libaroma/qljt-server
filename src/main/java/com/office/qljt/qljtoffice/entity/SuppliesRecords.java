package com.office.qljt.qljtoffice.entity;

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
@TableName("tb_supplies_records")
public class SuppliesRecords {

	/**
	 * id
	 */
	@TableId(value = "id")
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
	 * 物资名称
	 */
	private String name;

	/**
	 * 物资类别
	 */
	private String category;

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
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date time;

	/**
	 * 归还时间
	 */
	private Date returnTime;

}
