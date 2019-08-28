package com.mall.admin.dto;

import com.mall.common.validator.FlagValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 添加权限的参数
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/09 20:05
 */
@Data
@ApiModel
public class AdminPermissionParams {

    @ApiModelProperty(value = "父级权限id", required = true)
    @NotNull(message = "父级权限id不能为空")
    private Long pid;

    @ApiModelProperty(value = "权限名称", required = true)
    @NotEmpty(message = "权限名称不能为空")
    private String name;

    @ApiModelProperty(value = "权限值", required = true)
    @NotEmpty(message = "权限值不能为空")
    private String value;

    @ApiModelProperty(value = "权限图标")
    private String icon;

    @ApiModelProperty(value = "权限类型", required = true)
    @FlagValidator(value = {"1", "2", "3"}, required = true, message = "权限类型只能为1,2,3其中一个")
    private Integer type;

    @ApiModelProperty(value = "前端资源路径", required = true)
    @NotEmpty(message = "前端资源路径不能为空")
    private String uri;

    @ApiModelProperty(value = "排序")
    @Min(value = 0, message = "排序最小为0")
    private Integer sort;
}
