package com.office.qljt.qljtoffice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 逻辑删除
 *
 * @author lib
 * @date 2021/07/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "状态改变")
public class StatusVO {

    /**
     * id列表
     */
    @NotNull(message = "id不能为空")
    @ApiModelProperty(name = "idList", value = "id列表", required = true, dataType = "List<String>")
    private List<String> idList;

    /**
     * 状态值,0为删除，1为正常
     */
    @NotNull(message = "状态值不能为空")
    @ApiModelProperty(name = "status", value = "删除状态", required = true, dataType = "Integer")
    private Long status;

    /**
     * 角色信息,0为普通用户，1为管理，2为办公室老师，3为超级管理
     */
    @ApiModelProperty(name = "role", value = "角色信息", required = true, dataType = "Integer")
    private Long role;

}
