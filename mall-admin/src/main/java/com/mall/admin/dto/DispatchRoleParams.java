package com.mall.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel
public class DispatchRoleParams {

    @ApiModelProperty(value = "用户id", required =true)
    @NotNull(message = "用户id不能为空")
    private Long adminId;

    @ApiModelProperty(value = "角色id", required = true)
    @NotNull(message = "角色id数组不能为空")
    private List<Long> roleIds;
}
