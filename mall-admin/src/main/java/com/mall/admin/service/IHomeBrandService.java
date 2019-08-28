package com.mall.admin.service;

import com.mall.admin.dto.HomeBrandParams;
import com.mall.admin.dto.HomeBrandResult;
import com.mall.common.dto.Pagination;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 首页推荐品牌的service接口
 * @author wangyongchun
 * @version 1.0
 * @date 2019/04/07
 */
public interface IHomeBrandService {

    /**
     * 添加首页品牌推荐
     * @param brandParams
     */
    void create(HomeBrandParams brandParams);

    /**
     * 修改首页品牌推荐排序
     * @param id
     * @param sort
     */
    @Transactional
    void updateSort(Long id, Integer sort);

    /**
     * 批量删除首页品牌推荐
     * @param ids
     */
    @Transactional
    void batchDelete(List<Long> ids);

    /**
     * 更新首页品牌推荐状态
     * @param id
     * @param recommendStatus
     */
    @Transactional
    void updateRecommendStatus(Long id, Integer recommendStatus);

    /**
     * 获取首页品牌推荐列表
     * @param brandName
     * @param recommendStatus
     * @param page
     * @param pageSize
     * @return
     */
    Pagination<HomeBrandResult> getList(String brandName, Integer recommendStatus, Integer page, Integer pageSize);
}
