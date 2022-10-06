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
@TableName("tb_dispatch_records")
public class DispatchRecordsVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;

	/**
	 * openid
	 */
   	@ApiModelProperty(name = "_openid",value="openid",dataType = "String")
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
	 * 发文日期
	 */
   	@NotBlank(message = "发文日期不能为空")
	@ApiModelProperty(name = "confirm_date",value="发文日期",required = true,dataType = "String")
	private String confirmDate;

	/**
	 * 方式
	 */
   	@NotBlank(message = "方式不能为空")
	@ApiModelProperty(name = "method",value="方式",required = true,dataType = "String")
	private String method;

	/**
	 * 日期
	 */
   	@NotBlank(message = "日期不能为空")
	@ApiModelProperty(name = "date",value="日期",required = true,dataType = "String")
	private String date;

	/**
	 * 会议日期
	 */
   	@ApiModelProperty(name = "meeting_date",value="会议日期",dataType = "String")
	private String meetingDate;

	/**
	 * 物资描述
	 */
   	@NotBlank(message = "物资描述不能为空")
	@ApiModelProperty(name = "confirm_user",value="物资描述",required = true,dataType = "String")
	private String confirmUser;

	/**
	 * 原因描述
	 */
   	@NotBlank(message = "原因描述不能为空")
	@ApiModelProperty(name = "title",value="原因描述",required = true,dataType = "String")
	private String title;

	/**
	 * 年份
	 */
   	@NotNull(message = "年份不能为空")
	@ApiModelProperty(name = "year",value="年份",required = true,dataType = "Long")
	private Long year;

	/**
	 * 序号
	 */
   	@NotNull(message = "序号不能为空")
	@ApiModelProperty(name = "index",value="序号",required = true,dataType = "Long")
	private Long index;

}
