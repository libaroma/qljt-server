package com.office.qljt.qljtoffice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 查询条件
 *
 * @author lib
 * @date 2021/07/29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "查询条件")
public class ConditionVO {

    /**
     * 页码
     */
    @ApiModelProperty(name = "current", value = "页码", dataType = "Long")
    private Long current;

    /**
     * 条数
     */
    @ApiModelProperty(name = "size", value = "条数", dataType = "Long")
    private Long size;

    /**
     * 用户Id
     */
    @ApiModelProperty(name = "userId", value = "用户Id", dataType = "String")
    private String userId;

    /**
     * 搜索内容
     */
    @ApiModelProperty(name = "keywords", value = "搜索内容", dataType = "String")
    private String keywords;

    /**
     * 状态
     */
    @ApiModelProperty(name = "status", value = "状态", dataType = "Integer")
    private Long status;


    //----------------用户------------------------------

    /**
     * 用户 格式为 2022-10-7
     */
    @ApiModelProperty(name = "role", value = "用户", dataType = "Integer")
    private Long role;
    //----------------会场------------------------------

    /**
     * 会场预约日期 格式为 2022-10-7
     */
    @ApiModelProperty(name = "date", value = "会场预约日期", dataType = "String")
    private String date;

    /**
     * 会场 space id
     */
    @ApiModelProperty(name = "space", value = "会场", dataType = "String")
    private String space;

    //-----------------发文-----------------------------
    /**
     * 发文 method id
     */
    @ApiModelProperty(name = "method", value = "发文方式", dataType = "String")
    private String method;

    /**
     * 发文年份 year
     */
    @ApiModelProperty(name = "year", value = "发文年份", dataType = "Integer")
    private Long year;

    //----------------设备------------------------------
    /**
     * 设备分类
     */
    @ApiModelProperty(name = "suppliesCategory", value = "设备分类", dataType = "String")
    private String suppliesCategory;/**

     * 设备归还
     */
    @ApiModelProperty(name = "isReturn", value = "设备归还", dataType = "Integer")
    private Long isReturn;

    //----------------物资------------------------------
    /**
     * 物资
     */
    @ApiModelProperty(name = "goodsId", value = "物资", dataType = "String")
    private String goodsId;

    //----------------印信------------------------------
    /**
     * 公章
     */
    @ApiModelProperty(name = "seal", value = "公章", dataType = "String")
    private String seal;

}
