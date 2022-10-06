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
public class UserDefaultInfoDTO {

	/**
	 * id
	 */
	private String id;

	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 单位
	 */
	private String company;

	/**
	 * 电话
	 */
	private String contact;

	/**
	 * 状态(1可用，0删除)
	 */
	private Long status;

	/**
	 * 时间
	 */
	private Date time;

}
