package com.mall.admin.dto;

import com.mall.admin.entity.AdminProduct;

/**
 * 查询单个商品时的返回结果
 * @date 2019/04/21 16:16
 * @version 1.0
 * @author wangyongchun
 */
public class AdminProductResult extends AdminProduct {

    /**
     * 商品分类的父级id
     */
    private Long categoryParentId;

    public Long getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Long categoryParentId) {
        this.categoryParentId = categoryParentId;
    }
}
