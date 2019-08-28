package com.mall.admin.dto;

import com.mall.common.validator.FlagValidator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 后天新增商品参数
 * @date 2019/04/21 16:08
 * @author wangyongchun
 * @version 1.0
 */
@Data
@ApiModel
public class AdminProductParams{

    @ApiModelProperty(value = "品牌id", required = true)
    @NotNull(message = "品牌id不能为空")
    private Long brandId;

    @ApiModelProperty(value = "商品种类id", required = true)
    @NotNull(message = "productCategoryId不能为空")
    private Long productCategoryId;

    @ApiModelProperty(value = "feightTemplateId", required = true)
    @NotNull(message = "feightTemplateId不能为空")
    private Long feightTemplateId;

    @ApiModelProperty(value = "商品属性种类id", required = true)
    @NotNull(message = "productAttributeCategoryId不能为空")
    private Long productAttributeCategoryId;

    @ApiModelProperty(value = "商品名称", required = true)
    @NotEmpty(message = "name不能为空")
    @Length(max = 32, message = "商品名称长度不能超过32个字符")
    private String name;

    @ApiModelProperty(value = "商品图片", required = true)
    @NotEmpty(message = "pic不能为空")
    private String pic;

    @ApiModelProperty(value = "商品货号", required = true)
    @NotEmpty(message = "productSn不能为空")
    private String productSn;

    @ApiModelProperty(value = "上架状态", required = true)
    @FlagValidator(required = true, value = {"0", "1"}, message = "publishStatus值不正确")
    private Integer publishStatus;

    @ApiModelProperty(value = "是否新品", required = true)
    @FlagValidator(required = true, value = {"0", "1"}, message = "newStatus的值不正确,0表示不是新品,1表示是新品")
    private Integer newStatus;

    @ApiModelProperty(value = "推荐状态,0:表示不推荐,1表示推荐", required = true)
    @FlagValidator(required = true, value = {"0", "1"}, message = "recommandStatus值不正确")
    private Integer recommandStatus;

    @ApiModelProperty(value = "审核状态,0->未审核,1->审核", required = true)
    @FlagValidator(required = true, value = {"0", "1"}, message = "verifyStatus值不正确")
    private Integer verifyStatus;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "价格", required = true)
    @NotNull(message = "price不能为空")
    private Double price;

    @ApiModelProperty(value = "促销价格")
    private Double promotionPrice;

    @ApiModelProperty(value = "赠送的成长值", required = true)
    @NotNull(message = "giftGrowth字段值不能为空")
    private Integer giftGrowth;

    @ApiModelProperty(value = "赠送的积分", required = true)
    @NotNull(message = "giftPoint字段值不能为空")
    private Integer giftPoint;

    @ApiModelProperty(value = "限制使用的积分数", required = true)
    @NotNull(message = "usePointLimit字段值不能为空")
    private Integer usePointLimit;

    @ApiModelProperty(value = "副标题", required = true)
    @NotEmpty(message = "subTitle字段值不能为空")
    private String subTitle;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "市场价", required = true)
    @NotNull(message = "originalPrice字段值必填")
    private Double originalPrice;

    @ApiModelProperty(value = "库存量", required = true)
    @NotNull(message = "stock字段值必填")
    private Integer stock;

    @ApiModelProperty(value = "库存预警值", required = true)
    @NotNull(message = "lowStock字段值不能为空")
    private Integer lowStock;

    @ApiModelProperty(value = "单位", required = true)
    @NotEmpty(message = "unit字段值不能为空")
    private String unit;

    @ApiModelProperty(value = "商品重量,单位为克", required = true)
    @NotNull(message = "weight字段值不能为空")
    private Double weight;

    @ApiModelProperty(value = "是否为预告商品,0->不是,1->不是", required = true)
    @FlagValidator(required = true, value = {"0", "1"}, message = "previewStatus字段值不正确")
    private Integer previewStatus;

    @ApiModelProperty(value = "以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮", required = true)
    @FlagValidator(required = true, value = {"1", "2", "3"}, message = "serviceIds字段值不正确")
    private String serviceIds;

    @ApiModelProperty(value = "关键词", required = true)
    @NotEmpty(message = "keywords字段值必填")
    private String keywords;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;

    @ApiModelProperty(value = "商品详情标题", required = true)
    @NotEmpty(message = "detailTitle字段值必填")
    @Length(max = 255, message = "商品详情标题最大长度为255")
    private String detailTitle;

    @ApiModelProperty(value = "产品详情描述", required = true)
    @NotEmpty(message = "detailDesc字段值必填")
    private String detailDesc;

    @ApiModelProperty(value = "产品详情网页内容")
    private String detailHtml;

    @ApiModelProperty(value = "移动端网页详情")
    private String detailMobileHtml;

    @ApiModelProperty(value = "促销开始时间")
    private Date promotionStartTime;

    @ApiModelProperty(value = "促销结束时间")
    private Date promotionEndTime;

    @ApiModelProperty(value = "活动限购数量")
    private Integer promotionPerLimit;

    @ApiModelProperty(value = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    @FlagValidator(value = {"0", "1", "2", "3", "4", "5"}, message = "promotionType字段值不正确")
    private Integer promotionType;
}
