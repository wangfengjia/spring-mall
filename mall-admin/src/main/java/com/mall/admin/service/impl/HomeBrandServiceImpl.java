package com.mall.admin.service.impl;

import com.mall.admin.dao.HomeBrandRepository;
import com.mall.admin.dto.AdminBrandResult;
import com.mall.admin.dto.HomeBrandParams;
import com.mall.admin.dto.HomeBrandResult;
import com.mall.admin.entity.HomeBrand;
import com.mall.admin.enums.AdminRecordStatusEnum;
import com.mall.admin.service.IAdminBrandService;
import com.mall.admin.service.IHomeBrandService;
import com.mall.common.dto.Pagination;
import com.mall.common.exception.BusinessException;
import com.mall.common.specification.SimpleSpecificationBuilder;
import com.mall.common.specification.SpecificationOperator;
import com.mall.common.utils.PaginationFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 首页品牌推荐service的实现
 * @author wangyongchun
 * @version 1.0
 * @date 2019/4/7 22:07
 */
@Service
public class HomeBrandServiceImpl implements IHomeBrandService {

    @Autowired
    private HomeBrandRepository homeBrandRepository;
    @Autowired
    private IAdminBrandService adminBrandService;

    @Override
    public void create(HomeBrandParams brandParams) {
        HomeBrand homeBrand = new HomeBrand();
        BeanUtils.copyProperties(brandParams, homeBrand);
        AdminBrandResult adminBrandResult = adminBrandService.getById(homeBrand.getBrandId());
        if (adminBrandResult == null){
            throw new BusinessException("id为[" + homeBrand.getBrandId() + "]的品牌不存在");
        }
        homeBrand.setBrandName(adminBrandResult.getName());
        homeBrand.setDeleteStatus(AdminRecordStatusEnum.NOEMAL.getCode());
        homeBrandRepository.save(homeBrand);
    }

    @Override
    public void updateSort(Long id, Integer sort) {
        homeBrandRepository.updateSort(id, sort);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        homeBrandRepository.batchDelete(ids);
    }

    @Override
    public void updateRecommendStatus(Long id, Integer recommendStatus) {
        homeBrandRepository.updateRecommendStatus(id, recommendStatus);
    }

    @Override
    public Pagination<HomeBrandResult> getList(String brandName, Integer recommendStatus, Integer page, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "sort");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        SimpleSpecificationBuilder<HomeBrand> builder = new SimpleSpecificationBuilder<>();
        builder.addAnd("deleteStatus", SpecificationOperator.Operator.EQ, AdminRecordStatusEnum.NOEMAL.getCode());
        if (!StringUtils.isEmpty(brandName)){
            builder.addAnd("brandName", SpecificationOperator.Operator.LIKEALL, brandName);
        }
        if (recommendStatus != null){
            builder.addAnd("recommendStatus", SpecificationOperator.Operator.EQ, recommendStatus);
        }
        Specification<HomeBrand> specification = builder.generateSpecification();
        Page<HomeBrand> homeBrands = homeBrandRepository.findAll(specification, pageable);
        return PaginationFormatUtils.format(homeBrands, HomeBrandResult.class);
    }
}
