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
@TableName("tb_goods_records")
public class GoodsRecords {

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
	 * 单位
	 */
	private String company;

	/**
	 * 物资id
	 */
	private String goodsId;

	/**
	 * 原因描述
	 */
	private String description;

	/**
	 * 电话
	 */
	private String contact;

	/**
	 * 数量
	 */
	private Long count;

	/**
	 * 总数量
	 */
	private Long goodsTotalCount;

	/**
	 * 剩余数量
	 */
	private Long goodsLeftCount;

	/**
	 * 时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date time;

}
