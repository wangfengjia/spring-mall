package com.mall.admin.dto;

import com.mall.common.validator.FlagValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 产品查询参数
 * @date 2019/04/21 17:28
 * @author wangyongchun
 * @version 1.0
 */
@Data
@ApiModel
public class AdminProductQueryParams {

    @ApiModelProperty("上架状态：0->下架；1->上架")
    @FlagValidator(value = {"0", "1"}, message = "publishStatus字段值不正确")
    private Integer publishStatus;
    @ApiModelProperty("审核状态：0->未审核；1->审核通过")
    @FlagValidator(value = {"0", "1"}, message = "verifyStatus字段值不正确")
    private Integer verifyStatus;
    @ApiModelProperty("商品名称模糊关键字")
    private String keyword;
    @ApiModelProperty("商品货号")
    private String productSn;
    @ApiModelProperty("商品分类编号")
    private Long productCategoryId;
    @ApiModelProperty("商品品牌编号")
    private Long brandId;
}
