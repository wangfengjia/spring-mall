package com.mall.admin.service;

import com.mall.admin.dto.*;
import com.mall.common.dto.Pagination;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台商品相关的service接口
 * @date 2019/04/21 16:11
 * @version 1.0
 * @author wangyongchun
 */
public interface IAdminProductService {

    /**
     * 创建
     * @param params
     */
    void create(AdminBrandParams params);

    /**
     * 根据商品编号获取更新信息
     * @param id
     * @return
     */
    AdminProductResult getUpdateInfo(Long id);


    /**
     * 更新商品信息
     * @param id
     * @param updateParams
     */
    void update(Long id, AdminBrandParams updateParams);

    /**
     * 获取商品列表
     * @param params
     * @param page
     * @param pageSize
     * @return
     */
    Pagination<AdminProductResult> getList(AdminProductQueryParams params, Integer page, Integer pageSize);

    /**
     * 更新商品审核状态
     * @param ids 商品id数组
     * @param verifyStatus 审核状态
     * @param detail 审核详情
     */
    @Transactional
    void updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);


    /**
     * 批量修改商品上架状态
     * @param ids 商品ids数组
     * @param publishStatus 上架状态
     */
    @Transactional
    void updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量修改商品推荐状态
     * @param ids 商品id数组
     * @param recommendStatus 推荐状态
     */
    @Transactional
    void updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 批量修改新品状态
     * @param ids 商品id数组
     * @param newStatus 新品状态
     */
    @Transactional
    void updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量删除商品
     * @param ids 商品id数组
     */
    @Transactional
    void batchDelete(List<Long> ids);

}
