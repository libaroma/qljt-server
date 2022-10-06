package com.office.qljt.qljtoffice.vo;

import lombok.Data;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @author  续加仪
 * @date 2022-10-05
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_goods_records")
public class GoodsRecordsVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;

	/**
	 * openid
	 */
   	@ApiModelProperty(name = "_openid",value="openid",dataType = "String")
	private String openid;

	/**
	 * 用户id
	 */
   	@NotBlank(message = "用户id不能为空")
	@ApiModelProperty(name = "user_id",value="用户id",required = true,dataType = "String")
	private String userId;

	/**
	 * 单位
	 */
   	@NotBlank(message = "单位不能为空")
	@ApiModelProperty(name = "company",value="单位",required = true,dataType = "String")
	private String company;

	/**
	 * 名称
	 */
   	@NotBlank(message = "名称不能为空")
	@ApiModelProperty(name = "goods",value="名称",required = true,dataType = "String")
	private String goods;

	/**
	 * 物资类别
	 */
   	@NotBlank(message = "物资类别不能为空")
	@ApiModelProperty(name = "category",value="物资类别",required = true,dataType = "String")
	private String category;

	/**
	 * 物资描述
	 */
   	@NotBlank(message = "物资描述不能为空")
	@ApiModelProperty(name = "goods_options",value="物资描述",required = true,dataType = "String")
	private String goodsOptions;

	/**
	 * 原因描述
	 */
   	@NotBlank(message = "原因描述不能为空")
	@ApiModelProperty(name = "description",value="原因描述",required = true,dataType = "String")
	private String description;

	/**
	 * 照片
	 */
   	@NotBlank(message = "照片不能为空")
	@ApiModelProperty(name = "cover",value="照片",required = true,dataType = "String")
	private String cover;

	/**
	 * 电话
	 */
   	@NotBlank(message = "电话不能为空")
	@ApiModelProperty(name = "contact",value="电话",required = true,dataType = "String")
	private String contact;

	/**
	 * 数量
	 */
   	@NotNull(message = "数量不能为空")
	@ApiModelProperty(name = "count",value="数量",required = true,dataType = "Long")
	private Long count;

	/**
	 * 总数量
	 */
   	@NotNull(message = "总数量不能为空")
	@ApiModelProperty(name = "goods_total_count",value="总数量",required = true,dataType = "Long")
	private Long goodsTotalCount;

	/**
	 * 剩余数量
	 */
   	@NotNull(message = "剩余数量不能为空")
	@ApiModelProperty(name = "goods_left_count",value="剩余数量",required = true,dataType = "Long")
	private Long goodsLeftCount;

}
