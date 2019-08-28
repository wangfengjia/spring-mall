package com.mall.admin.dao;

import com.mall.admin.entity.HomeBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 首页推荐品牌的repository类
 * @author wangyongchun
 * @version 1.0
 * @date 2019/04/07 21:30
 */
public interface HomeBrandRepository extends
        JpaRepository<HomeBrand, Long>, JpaSpecificationExecutor<HomeBrand> {

    /**
     * 更改首页品牌推荐排序
     * @param id
     * @param sort
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update home_brand set sort = ?2 where id = ?1")
    void updateSort(Long id, Integer sort);

    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update home_brand set delete_status = 1 where id in ?1")
    void batchDelete(List<Long> ids);

    /**
     * 更新首页品牌推荐状态值
     * @param id
     * @param recommendStatus
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update home_brand set recommend_status = ?2 where id = ?1")
    void updateRecommendStatus(Long id, Integer recommendStatus);
}
