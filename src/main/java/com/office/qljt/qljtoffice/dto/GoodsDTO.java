package com.office.qljt.qljtoffice.dto;

import lombok.Data;
import lombok.Builder;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author  续加仪
 * @date 2022-10-06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {

	/**
	 * id
	 */
	private String id;

	/**
	 * 物资名称
	 */
	private String name;

	/**
	 * 物资描述
	 */
	private String description;

	/**
	 * 照片
	 */
	private String cover;

	/**
	 * 总数量
	 */
	private Long totalCount;

	/**
	 * 剩余数量
	 */
	private Long leftCount;

	/**
	 * 状态(1可用，0删除)
	 */
	private Long status;

	/**
	 * 时间
	 */
	private Date time;

}
