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
@TableName("tb_sdu")
public class SduVO {

	/**
	 * 工号id
	 */
   	@ApiModelProperty(name = "id",value="工号id",dataType = "String")
	private String id;

	/**
	 * 工号
	 */
   	@NotBlank(message = "工号不能为空")
	@ApiModelProperty(name = "sdu_id",value="工号",required = true,dataType = "String")
	private String sduId;

	/**
	 * 姓名
	 */
   	@NotBlank(message = "姓名不能为空")
	@ApiModelProperty(name = "sdu_name",value="姓名",required = true,dataType = "String")
	private String sduName;

	/**
	 * 状态(1可用，0删除)
	 */
   	@ApiModelProperty(name = "status",value="状态(1可用，0删除)",dataType = "Long")
	private Long status;

}
