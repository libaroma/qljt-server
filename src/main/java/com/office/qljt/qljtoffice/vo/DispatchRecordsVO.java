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
@TableName("tb_dispatch_records")
public class DispatchRecordsVO {

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
	 * 方式
	 */
   	@NotBlank(message = "方式不能为空")
	@ApiModelProperty(name = "method",value="方式",required = true,dataType = "String")
	private String method;

	/**
	 * 日期
	 */
   	@NotBlank(message = "发文日期不能为空")
	@ApiModelProperty(name = "date",value="发文日期",required = true,dataType = "String")
	private String date;

	/**
	 * 会议日期
	 */
   	@ApiModelProperty(name = "meeting_date",value="会议日期",dataType = "String")
	private String meetingDate;

	/**
	 * 发文标题
	 */
   	@NotBlank(message = "发文标题不能为空")
	@ApiModelProperty(name = "title",value="发文标题",required = true,dataType = "String")
	private String title;

	/**
	 * 签发领导
	 */
	@ApiModelProperty(name = "confirmUser",value="签发领导",required = true,dataType = "String")
	private String confirmUser;

	/**
	 * 年份
	 */
   	@NotNull(message = "年份不能为空")
	@ApiModelProperty(name = "year",value="年份",required = true,dataType = "Long")
	private Long year;


}
