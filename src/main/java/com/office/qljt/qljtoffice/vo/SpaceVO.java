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
 * @date 2022-10-06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tb_space")
public class SpaceVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;

	/**
	 * 会场名称
	 */
   	@NotBlank(message = "会场名称不能为空")
	@ApiModelProperty(name = "name",value="会场名称",required = true,dataType = "String")
	private String name;

	/**
	 * 封面
	 */
   	@NotBlank(message = "封面不能为空")
	@ApiModelProperty(name = "image",value="封面",required = true,dataType = "String")
	private String image;

	/**
	 * 次序
	 */
   	@NotNull(message = "次序不能为空")
	@ApiModelProperty(name = "sequence",value="次序",required = true,dataType = "Long")
	private Long sequence;

	/**
	 * 状态(1可用，0删除)
	 */
   	@ApiModelProperty(name = "status",value="状态(1可用，0删除)",dataType = "Long")
	private Long status;

}
