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
public class GoodsRecordsDTO {

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
	 * 名称
	 */
	private String goods;

	/**
	 * 物资类别
	 */
	private String category;

	/**
	 * 物资描述
	 */
	private String goodsOptions;

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
	private Date time;

}
