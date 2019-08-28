package com.mall.admin.dao;

import com.mall.admin.entity.AdminBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminBrandRepository extends
        JpaRepository<AdminBrand, Long>, JpaSpecificationExecutor<AdminBrand> {

    /**
     * 批量删除
     * @param ids
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "delete from admin_brand where id in ?1")
    void batchDelete(List<Long> ids);

    /**
     * 批量更新品牌的显示状态
     * @param ids
     * @param showStatus
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update admin_brand set show_status = ?2 where id in ?1")
    void batchUpdateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 批量更新是否为品牌制造商
     * @param ids
     * @param factoryStatus
     */
    @Modifying
    @Query(nativeQuery = true,
            value = "update admin_brand set factory_status = ?2 where id in ?1")
    void batchUpdateFactoryStatus(List<Long> ids, Integer factoryStatus);
}
