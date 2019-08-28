package com.mall.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 新建角色时的参数
 * @author wangyongchun
 * @version 1.0
 * @date 2019/03/23 14:34
 */
@Data
@ApiModel
public class AdminRoleParams {

    @ApiModelProperty(value = "角色名称", required = true)
    @NotEmpty(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty(value = "角色描述", required = true)
    @NotEmpty(message = "角色描述不能为空")
    private String description;

    @ApiModelProperty(value = "后台用户数量", required = false)
    private Integer adminCount;

    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
}
