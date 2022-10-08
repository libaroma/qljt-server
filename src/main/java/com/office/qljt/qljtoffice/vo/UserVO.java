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
@TableName("tb_user")
public class UserVO {

	/**
	 * id
	 */
   	@ApiModelProperty(name = "id",value="id",dataType = "String")
	private String id;

	/**
	 * openid
	 */
   	@ApiModelProperty(name = "_openid",value="openid",dataType = "String")
	private String openid;

	/**
	 * 学号/工号
	 */
   	@NotBlank(message = "学号/工号不能为空")
	@ApiModelProperty(name = "sdu_id",value="学号/工号",required = true,dataType = "String")
	private String sduId;

	/**
	 * 昵称
	 */
   	@NotBlank(message = "昵称不能为空")
	@ApiModelProperty(name = "user_nickname",value="昵称",required = true,dataType = "String")
	private String userNickname;

	/**
	 * 头像
	 */
   	@NotBlank(message = "头像不能为空")
	@ApiModelProperty(name = "user_avatar",value="头像",required = true,dataType = "String")
	private String userAvatar;

	/**
	 * 省份
	 */
   	@NotBlank(message = "省份不能为空")
	@ApiModelProperty(name = "user_province",value="省份",required = true,dataType = "String")
	private String userProvince;

	/**
	 * 城市
	 */
   	@NotBlank(message = "城市不能为空")
	@ApiModelProperty(name = "user_city",value="城市",required = true,dataType = "String")
	private String userCity;

	/**
	 * 国家
	 */
   	@NotBlank(message = "国家不能为空")
	@ApiModelProperty(name = "user_country",value="国家",required = true,dataType = "String")
	private String userCountry;

	/**
	 * 语言
	 */
   	@NotBlank(message = "语言不能为空")
	@ApiModelProperty(name = "user_language",value="语言",required = true,dataType = "String")
	private String userLanguage;

	/**
	 * 状态(1可用，0删除)
	 */
   	@ApiModelProperty(name = "status",value="状态(1可用，0删除)",dataType = "Long")
	private Long status;

	/**
	 * 角色(0普通，1管理员,2高级管理员)
	 */
   	@NotNull(message = "角色(0普通，1管理员,2高级管理员)不能为空")
	@ApiModelProperty(name = "role",value="角色(0普通，1管理员,2高级管理员)",required = true,dataType = "Long")
	private Long role;

}
