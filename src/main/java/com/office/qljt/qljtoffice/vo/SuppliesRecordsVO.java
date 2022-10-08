package com.office.qljt.qljtoffice.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author  续加仪
 * @date 2022-10-06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_supplies_records")
public class SuppliesRecordsVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;

	/**
	 * openid
	 */
   	@ApiModelProperty(name = "openid",value="openid",dataType = "String")
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
	 * 设备名称
	 */
   	@NotBlank(message = "设备名称不能为空")
	@ApiModelProperty(name = "name",value="设备名称",required = true,dataType = "String")
	private String name;

	/**
	 * 设备分类
	 */
   	@NotBlank(message = "设备分类不能为空")
	@ApiModelProperty(name = "supplies_category",value="设备分类",required = true,dataType = "String")
	private String suppliesCategory;

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
	 * 是否归还/0:未还，1：已还未确认，2：已还
	 */
   	@NotNull(message = "是否归还/0:未还，1：已还未确认，2：已还不能为空")
	@ApiModelProperty(name = "is_return",value="是否归还/0:未还，1：已还未确认，2：已还",required = true,dataType = "Long")
	private Long isReturn;

	/**
	 * 归还时间
	 */
   	@NotNull(message = "归还时间不能为空")
	@ApiModelProperty(name = "return_time",value="归还时间",required = true,dataType = "Date")
	private Date returnTime;

}
