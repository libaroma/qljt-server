package com.office.qljt.qljtoffice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页对象
 *
 * @author lib
 * @date 2021/08/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "分页对象")
public class PageResult<T> {

    public PageResult(List<T> data, Long count) {
        this(data, 0L, count, count);
    }

    /**
     * 分页列表
     */
    @ApiModelProperty(name = "data", value = "分页列表", required = true, dataType = "List<T>")
    private List<T> data;

    /**
     * 当前页
     */
    @ApiModelProperty(name = "current", value = "当前页", required = true, dataType = "Integer")
    private Long current;

    /**
     * 页面尺寸
     */
    @ApiModelProperty(name = "size", value = "页面尺寸", required = true, dataType = "Integer")
    private Long size;

    /**
     * 总数
     */
    @ApiModelProperty(name = "count", value = "总数", required = true, dataType = "Integer")
    private Long count;

}
