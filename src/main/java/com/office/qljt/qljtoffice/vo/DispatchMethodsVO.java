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
@TableName("tb_dispatch_methods")
public class DispatchMethodsVO {

	/**
	 * 主键
	 */
   	@ApiModelProperty(name = "id",value="主键",dataType = "String")
	private String id;

	/**
	 * 方式:党委会议研究决定
	 */
   	@NotBlank(message = "方式:党委会议研究决定不能为空")
	@ApiModelProperty(name = "method",value="方式:党委会议研究决定",required = true,dataType = "String")
	private String method;

	/**
	 * 机关代字:院党字
	 */
   	@NotBlank(message = "机关代字:院党字不能为空")
	@ApiModelProperty(name = "office",value="机关代字:院党字",required = true,dataType = "String")
	private String office;

	/**
	 * 公章类别
	 */
   	@NotBlank(message = "公章类别不能为空")
	@ApiModelProperty(name = "seal",value="公章类别",required = true,dataType = "String")
	private String seal;

	/**
	 * 状态(1可用，0删除)
	 */
   	@ApiModelProperty(name = "status",value="状态(1可用，0删除)",dataType = "Long")
	private Long status;

}
