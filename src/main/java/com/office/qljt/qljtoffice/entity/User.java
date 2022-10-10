package com.office.qljt.qljtoffice.entity;

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
@TableName("tb_user")
public class User {

	/**
	 * id
	 */
	@TableId(value = "id")
	private String id;


	/**
	 * 学号/工号
	 */
	private String sduId;

	/**
	 * 昵称
	 */
	private String userNickname;

	/**
	 * 头像
	 */
	private String userAvatar;

	/**
	 * 状态(1可用，0删除)
	 */
	private Long status;

	/**
	 * 角色(0普通，1管理员,2高级管理员)
	 */
	private Long role;

	/**
	 * 时间
	 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Date time;

}
