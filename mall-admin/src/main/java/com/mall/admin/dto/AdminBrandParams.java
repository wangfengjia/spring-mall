package com.mall.admin.dto;

import com.mall.common.validator.FlagValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@ApiModel
public class AdminBrandParams {

    @ApiModelProperty(value = "品牌名称", required = true)
    @NotEmpty(message = "品牌名称不能为空")
    private String name;

    @ApiModelProperty(value = "首字母")
    private String firstLetter;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "是否为品牌制造商")
    @FlagValidator(required = true, value = {"0", "1"}, message = "是否为品牌制造商:0表示不是,1表示是")
    private Integer factoryStatus;

    @ApiModelProperty(value = "是否进行显示", required = true)
    @FlagValidator(required = true, value = {"1", "2"}, message = "显示状态不正确")
    private Integer showStatus;

    @ApiModelProperty(value = "产品数量")
    private Integer productCount;
    @ApiModelProperty(value = "产品评论数量")
    private Integer productCommentCount;
    @ApiModelProperty(value = "品牌logo")
    private String logo;
    @ApiModelProperty(value = "专区大图")
    private String bigPic;
    @ApiModelProperty(value = "品牌故事")
    private String brandStory;
}
