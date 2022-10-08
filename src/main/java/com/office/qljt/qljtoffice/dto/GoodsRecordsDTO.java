package com.office.qljt.qljtoffice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 续加仪
 * @date 2022-10-06
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsRecordsDTO {

    /**
     * id
     */
    private String id;

    /**
     * openid
     */
    private String openid;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 单位
     */
    private String company;

    /**
     * 物资id
     */
    private String goodsId;

    /**
     * 原因描述
     */
    private String description;

    /**
     * 电话
     */
    private String contact;

    /**
     * 数量
     */
    private Long count;

    /**
     * 总数量
     */
    private Long goodsTotalCount;

    /**
     * 剩余数量
     */
    private Long goodsLeftCount;

    /**
     * 时间
     */
    private Date time;

    /**
     * userInfo
     */
    private UserDTO userInfo;

    /**
     * 物资信息
     */
    private GoodsDTO goodsInfo;

}
