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
@TableName("tb_email")
public class Email {

	/**
	 * id
	 */
	@TableId(value = "id")
	private String id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 状态(1可用，0删除)
	 */
	private Long status;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date time;

}
