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
public class SpaceDTO {

	/**
	 * id
	 */
	private String id;

	/**
	 * 会场名称
	 */
	private String name;

	/**
	 * 封面
	 */
	private String image;

	/**
	 * 次序
	 */
	private Long order;

	/**
	 * 状态(1可用，0删除)
	 */
	private Long status;

	/**
	 * 时间
	 */
	private Date time;

}
