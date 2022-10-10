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
@TableName("tb_operation_log")
public class OperationLogVO {

	/**
	 * 主键id
	 */
   	@ApiModelProperty(name = "id",value="主键id",dataType = "Long")
	private Long id;


	/**
	 * 操作用户id
	 */
   	@NotBlank(message = "操作用户id不能为空")
	@ApiModelProperty(name = "user_id",value="操作用户id",required = true,dataType = "String")
	private String userId;

	/**
	 * 操作模块
	 */
   	@NotBlank(message = "操作模块不能为空")
	@ApiModelProperty(name = "opt_module",value="操作模块",required = true,dataType = "String")
	private String optModule;

	/**
	 * 操作类型
	 */
   	@NotBlank(message = "操作类型不能为空")
	@ApiModelProperty(name = "opt_type",value="操作类型",required = true,dataType = "String")
	private String optType;

	/**
	 * 操作url
	 */
   	@NotBlank(message = "操作url不能为空")
	@ApiModelProperty(name = "opt_url",value="操作url",required = true,dataType = "String")
	private String optUrl;

	/**
	 * 操作方法
	 */
   	@NotBlank(message = "操作方法不能为空")
	@ApiModelProperty(name = "opt_method",value="操作方法",required = true,dataType = "String")
	private String optMethod;

	/**
	 * 操作描述
	 */
   	@NotBlank(message = "操作描述不能为空")
	@ApiModelProperty(name = "opt_desc",value="操作描述",required = true,dataType = "String")
	private String optDesc;

	/**
	 * 请求参数
	 */
   	@NotBlank(message = "请求参数不能为空")
	@ApiModelProperty(name = "request_param",value="请求参数",required = true,dataType = "String")
	private String requestParam;

	/**
	 * 请求方式
	 */
   	@NotBlank(message = "请求方式不能为空")
	@ApiModelProperty(name = "request_method",value="请求方式",required = true,dataType = "String")
	private String requestMethod;

	/**
	 * 返回数据
	 */
   	@NotBlank(message = "返回数据不能为空")
	@ApiModelProperty(name = "response_data",value="返回数据",required = true,dataType = "String")
	private String responseData;

	/**
	 * 操作ip
	 */
   	@NotBlank(message = "操作ip不能为空")
	@ApiModelProperty(name = "ip_address",value="操作ip",required = true,dataType = "String")
	private String ipAddress;

	/**
	 * 操作地址
	 */
   	@NotBlank(message = "操作地址不能为空")
	@ApiModelProperty(name = "ip_source",value="操作地址",required = true,dataType = "String")
	private String ipSource;

}
