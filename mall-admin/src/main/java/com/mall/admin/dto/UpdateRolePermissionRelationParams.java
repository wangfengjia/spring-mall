package com.mall.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel
public class UpdateRolePermissionRelationParams {

    @ApiModelProperty(value = "角色id", required = true)
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @ApiModelProperty(value = "权限id数组", required = true)
    @NotNull(message = "权限id数组不能为空")
    private List<Long> permissionIds;


}
