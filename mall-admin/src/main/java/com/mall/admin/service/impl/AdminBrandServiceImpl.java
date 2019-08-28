package com.mall.admin.service.impl;

import com.mall.admin.dao.AdminBrandRepository;
import com.mall.admin.dto.AdminBrandParams;
import com.mall.admin.dto.AdminBrandResult;
import com.mall.admin.entity.AdminBrand;
import com.mall.admin.service.IAdminBrandService;
import com.mall.common.dto.Pagination;
import com.mall.common.exception.BusinessException;
import com.mall.common.specification.SimpleSpecificationBuilder;
import com.mall.common.specification.SpecificationOperator;
import com.mall.common.utils.ObjectMapperUtils;
import com.mall.common.utils.PaginationFormatUtils;
import lombok.extern.slf4j.Slf4j;
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

@Service
@Slf4j
public class AdminBrandServiceImpl implements IAdminBrandService {

    @Autowired
    private AdminBrandRepository brandRepository;

    @Override
    public Pagination<AdminBrandResult> getList(int page, int pageSize, String name) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        SimpleSpecificationBuilder<AdminBrand> builder = new SimpleSpecificationBuilder<>();
        if (!StringUtils.isEmpty(name)){
            builder.addAnd("name", SpecificationOperator.Operator.LIKEALL, name);
        }
        Specification<AdminBrand> brandSpecification = builder.generateSpecification();
        Page<AdminBrand> brandList = brandRepository.findAll(brandSpecification, pageable);
        return PaginationFormatUtils.format(brandList, AdminBrandResult.class);
    }

    @Override
    public void add(AdminBrandParams brandParams) {
        AdminBrand brand = new AdminBrand();
        BeanUtils.copyProperties(brandParams, brand);
        //如果创建时首字母为空时,取名称的第一个首字母
        if (StringUtils.isEmpty(brand.getFirstLetter())){
            brand.setFirstLetter(brand.getName().substring(0, 1));
        }
        brandRepository.save(brand);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        brandRepository.batchDelete(ids);
    }

    @Override
    public AdminBrandResult getById(Long id) {
        AdminBrand adminBrand = brandRepository.findById(id).orElse(null);
        return ObjectMapperUtils.map(adminBrand, AdminBrandResult.class);
    }

    @Override
    public void updateShowStatus(List<Long> ids, Integer showStatus) {
        brandRepository.batchUpdateShowStatus(ids, showStatus);
    }

    @Override
    public void updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        brandRepository.batchUpdateFactoryStatus(ids, factoryStatus);
    }

    @Override
    public void update(Long id, AdminBrandParams params) {
        AdminBrand brand = brandRepository.findById(id).orElse(null);
        if (brand == null){
            throw new BusinessException("id为[" + id + "]的品牌不存在");
        }
        AdminBrand updateBrand = new AdminBrand();
        BeanUtils.copyProperties(params, updateBrand);
        updateBrand.setId(id);
        //如果首字母为空,则取名称的第一个首字母
        if (StringUtils.isEmpty(updateBrand.getFirstLetter())){
            updateBrand.setFirstLetter(updateBrand.getName().substring(0, 1));
        }
        brandRepository.save(updateBrand);
    }
}
