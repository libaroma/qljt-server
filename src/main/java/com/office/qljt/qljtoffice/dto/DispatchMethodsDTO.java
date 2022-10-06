package com.office.qljt.qljtoffice.dto;

import lombok.Data;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author  续加仪
 * @date 2022-10-05
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DispatchMethodsDTO {

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 方式:党委会议研究决定
	 */
	private String method;

	/**
	 * 机关代字:院党字
	 */
	private String office;

	/**
	 * 公章类别
	 */
	private String seal;

	/**
	 * 状态(1可用，0删除)
	 */
	private Long status;

	/**
	 * 时间
	 */
	private Date time;

}
