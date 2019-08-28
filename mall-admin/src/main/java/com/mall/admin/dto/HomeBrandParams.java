package com.mall.admin.dto;

import com.mall.common.validator.FlagValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 首页品牌推荐的新增和更新参数
 * @author wangyongchun
 * @version 1.0
 * @date 2019/04/07 21:44
 */
@ApiModel
@Data
public class HomeBrandParams {

    @ApiModelProperty(value = "品牌id")
    @NotNull(message = "品牌id不能为空")
    private Long brandId;
    @ApiModelProperty(value = "推荐状态")
    @FlagValidator(required = true, value = {"1", "2"}, message = "推荐状态:1表示推荐,2表示不推荐")
    private Integer recommendStatus;
    @ApiModelProperty(value = "排序")
    private Integer sort;
}
