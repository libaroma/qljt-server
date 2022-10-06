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
public class OperationLogDTO {

	/**
	 * 主键id
	 */
	private Long id;

	/**
	 * openid
	 */
	private String openid;

	/**
	 * 操作用户id
	 */
	private String userId;

	/**
	 * 操作模块
	 */
	private String optModule;

	/**
	 * 操作类型
	 */
	private String optType;

	/**
	 * 操作url
	 */
	private String optUrl;

	/**
	 * 操作方法
	 */
	private String optMethod;

	/**
	 * 操作描述
	 */
	private String optDesc;

	/**
	 * 请求参数
	 */
	private String requestParam;

	/**
	 * 请求方式
	 */
	private String requestMethod;

	/**
	 * 返回数据
	 */
	private String responseData;

	/**
	 * 操作ip
	 */
	private String ipAddress;

	/**
	 * 操作地址
	 */
	private String ipSource;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

}
