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
public class SealDTO {

	/**
	 * id
	 */
	private String id;

	/**
	 * 公章名称
	 */
	private String seal;

	/**
	 * 状态(1可用，0删除)
	 */
	private Long status;

	/**
	 * 创建时间
	 */
	private Date time;

}
