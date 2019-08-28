package com.mall.admin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 商品实体
 * @author wangyongchun
 * @version 1,0
 * @date 2019/03/24 20:33
 */
@Entity
@Table(name = "admin_product")
@Data
public class AdminProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "product_category_id")
    private Long productCategoryId;

    @Column(name = "feight_template_id")
    private Long feightTemplateId;

    @Column(name = "product_attribute_category_id")
    private Long productAttributeCategoryId;

    private String name;

    private String pic;

    @Column(name = "product_sn")
    private String productSn;

    @Column(name = "delete_status")
    private Integer deleteStatus;

    @Column(name = "publish_status")
    private Integer publishStatus;

    @Column(name = "new_status")
    private Integer newStatus;

    @Column(name = "recommand_status")
    private Integer recommandStatus;

    private Integer verifyStatus;
    private Integer sort;
    private Integer sale;
    private Double price;
    private Double promotionPrice;
    private Integer giftGrowth;
    private Integer giftPoint;
    private Integer usePointLimit;
    private String subTitle;
    private String description;
    private Double originalPrice;
    private Integer stock;
    private Integer lowStock;
    private String unit;
    private Double weight;
    private Integer previewStatus;
    private String serviceIds;
    private String keywords;
    private String note;
    private String albumPics;
    private String detailTitle;
    private String detailDesc;
    private String detailHtml;
    private String detailMobileHtml;
    private Timestamp promotionStartTime;
    private Timestamp promotionEndTime;
    private Integer promotionPerLimit;
    private Integer promotionType;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
