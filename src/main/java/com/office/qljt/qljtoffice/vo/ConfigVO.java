package com.office.qljt.qljtoffice.vo;

import lombok.Data;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;


/**
 * @author  续加仪
 * @date 2022-10-05
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_website_config")
public class ConfigVO {

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
	 * 配置信息
	 */
   	@ApiModelProperty(name = "config",value="配置信息",dataType = "String")
	private String config;

}
