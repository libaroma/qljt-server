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
@TableName("tb_goods_category")
public class GoodsCategoryVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;

	/**
	 * 物资名称
	 */
   	@NotBlank(message = "物资名称不能为空")
	@ApiModelProperty(name = "name",value="物资名称",required = true,dataType = "String")
	private String name;

	/**
	 * 物资描述
	 */
   	@NotBlank(message = "物资描述不能为空")
	@ApiModelProperty(name = "options",value="物资描述",required = true,dataType = "String")
	private String options;

	/**
	 * 照片
	 */
   	@NotBlank(message = "照片不能为空")
	@ApiModelProperty(name = "cover",value="照片",required = true,dataType = "String")
	private String cover;

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
	@ApiModelProperty(name = "total_count",value="总数量",required = true,dataType = "Long")
	private Long totalCount;

	/**
	 * 剩余数量
	 */
   	@NotNull(message = "剩余数量不能为空")
	@ApiModelProperty(name = "left_count",value="剩余数量",required = true,dataType = "Long")
	private Long leftCount;

	/**
	 * 状态(1可用，0删除)
	 */
   	@ApiModelProperty(name = "status",value="状态(1可用，0删除)",dataType = "Long")
	private Long status;

}
