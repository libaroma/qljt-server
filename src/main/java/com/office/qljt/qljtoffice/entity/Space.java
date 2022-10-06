package com.office.qljt.qljtoffice.entity;

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
@TableName("tb_space")
public class Space {

	/**
	 * id
	 */
	@TableId(value = "id")
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
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date time;

}
