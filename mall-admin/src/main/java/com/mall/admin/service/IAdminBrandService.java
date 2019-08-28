package com.mall.admin.service;

import com.mall.admin.dto.AdminBrandParams;
import com.mall.admin.dto.AdminBrandResult;
import com.mall.common.dto.Pagination;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 品牌的service接口
 * @author wangyongchun
 * @date 2019/03/31 15:08
 * @version 1.0
 */
public interface IAdminBrandService {

    /**
     * 获取品牌了列表
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    Pagination<AdminBrandResult> getList(int page, int pageSize, String name);

    /**
     * 新增品牌
     * @param brandParams
     */
    void add(AdminBrandParams brandParams);

    /**
     * 批量删除品牌
     * @param ids
     */
    @Transactional
    void batchDelete(List<Long> ids);

    /**
     * 获取品牌详情
     * @param id
     * @return
     */
    AdminBrandResult getById(Long id);

    /**
     * 更新品牌的启用状态
     * @param ids
     * @param showStatus
     */
    @Transactional
    void updateShowStatus(List<Long> ids, Integer showStatus);

    /**
     * 更新是否为品牌制造商
     * @param ids
     * @param factoryStatus
     */
    @Transactional
    void updateFactoryStatus(List<Long> ids, Integer factoryStatus);

    /**
     * 更新品牌信息
     * @param id
     * @param params
     */
    @Transactional
    void update(Long id, AdminBrandParams params);
}
