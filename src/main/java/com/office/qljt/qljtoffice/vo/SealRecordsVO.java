package com.office.qljt.qljtoffice.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


/**
 * @author  续加仪
 * @date 2022-10-05
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_seal_record")
public class SealRecordsVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;


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
	 * 批准人
	 */
   	@NotBlank(message = "批准人不能为空")
	@ApiModelProperty(name = "confirm_user",value="批准人",required = true,dataType = "String")
	private String confirmUser;

	/**
	 * 公章
	 */
   	@NotBlank(message = "公章不能为空")
	@ApiModelProperty(name = "seal",value="公章",required = true,dataType = "String")
	private String seal;

	/**
	 * 原因描述
	 */
   	@NotBlank(message = "原因描述不能为空")
	@ApiModelProperty(name = "description",value="原因描述",required = true,dataType = "String")
	private String description;

	/**
	 * 电话
	 */
   	@NotBlank(message = "电话不能为空")
	@ApiModelProperty(name = "contact",value="电话",required = true,dataType = "String")
	private String contact;

}
