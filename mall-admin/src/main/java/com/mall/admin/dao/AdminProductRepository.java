package com.mall.admin.dao;

import com.mall.admin.entity.AdminProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminProductRepository
        extends JpaRepository<AdminProduct, Long>, JpaSpecificationExecutor<AdminProduct> {

    /**
     * 批量更新商品审核状态
     * @param ids 商品id数组
     * @param verifyStatus 审核状态
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update `admin_product` set verify_status = ?2 where id in ?1")
    void updateVarifyStatus(List<Long> ids, Integer verifyStatus);

    /**
     * 批量更新上架状态
     * @param ids 商品id数组
     * @param publishStatus 上架状态
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update `admin_product` set `publish_status` = ?2 where `id` in ?1")
    void updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量更新推荐状态
     * @param ids 商品id数组
     * @param recommendStatus 推荐状态
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update `admin_product` set `recommend_status` = ?2 where `id` in ?1")
    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量更新新品状态
     * @param ids 商品id数组
     * @param newStatus 新品状态
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update `admin_product` set `new_status` = ?2 where `id` in ?1")
    void updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量删除商品
     * @param ids 商品id数组
     * @param deleteStatus 删除状态值
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update `admin_product` set `delete_status` = ?2 where `id` in ?1")
    void batchDelete(List<Long> ids, Integer deleteStatus);
}
