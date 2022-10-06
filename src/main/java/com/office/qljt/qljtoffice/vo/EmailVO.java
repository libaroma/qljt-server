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
@TableName("tb_email")
public class EmailVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;

	/**
	 * 名称
	 */
   	@NotBlank(message = "名称不能为空")
	@ApiModelProperty(name = "name",value="名称",required = true,dataType = "String")
	private String name;

	/**
	 * 邮箱
	 */
   	@NotBlank(message = "邮箱不能为空")
	@ApiModelProperty(name = "email",value="邮箱",required = true,dataType = "String")
	private String email;

	/**
	 * 状态(1可用，0删除)
	 */
   	@ApiModelProperty(name = "status",value="状态(1可用，0删除)",dataType = "Long")
	private Long status;

}
