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
@TableName("tb_space_records")
public class SpaceRecordsVO {

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
	 * 批准人
	 */
   	@NotBlank(message = "批准人不能为空")
	@ApiModelProperty(name = "confirm_user",value="批准人",required = true,dataType = "String")
	private String confirmUser;

	/**
	 * 单位
	 */
   	@NotBlank(message = "单位不能为空")
	@ApiModelProperty(name = "company",value="单位",required = true,dataType = "String")
	private String company;

	/**
	 * 日期/星期
	 */
   	@NotBlank(message = "日期/星期不能为空")
	@ApiModelProperty(name = "day",value="日期/星期",required = true,dataType = "String")
	private String day;

	/**
	 * 日期/年月日
	 */
   	@NotBlank(message = "日期/年月日不能为空")
	@ApiModelProperty(name = "date",value="日期/年月日",required = true,dataType = "String")
	private String date;

	/**
	 * 开始时间
	 */
   	@NotBlank(message = "开始时间不能为空")
	@ApiModelProperty(name = "begin",value="开始时间",required = true,dataType = "String")
	private String begin;

	/**
	 * 结束时间
	 */
   	@NotBlank(message = "结束时间不能为空")
	@ApiModelProperty(name = "end",value="结束时间",required = true,dataType = "String")
	private String end;

	/**
	 * 会场
	 */
   	@NotBlank(message = "会场不能为空")
	@ApiModelProperty(name = "space",value="会场",required = true,dataType = "String")
	private String space;

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

	/**
	 * 人数
	 */
   	@NotNull(message = "人数不能为空")
	@ApiModelProperty(name = "count",value="人数",required = true,dataType = "Long")
	private Long count;

	/**
	 * 多媒体
	 */
   	@NotNull(message = "多媒体不能为空")
	@ApiModelProperty(name = "media",value="多媒体",required = true,dataType = "Long")
	private Long media;

}
