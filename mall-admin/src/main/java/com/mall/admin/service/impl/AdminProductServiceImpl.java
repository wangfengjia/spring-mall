package com.mall.admin.service.impl;

import com.mall.admin.dao.AdminProductRepository;
import com.mall.admin.dao.AdminProductVerifyRecordRepository;
import com.mall.admin.dto.*;
import com.mall.admin.entity.AdminProduct;
import com.mall.admin.entity.AdminProductVerifyRecord;
import com.mall.admin.enums.AdminRecordStatusEnum;
import com.mall.admin.service.IAdminProductService;
import com.mall.common.dto.Pagination;
import com.mall.common.exception.BusinessException;
import com.mall.common.specification.SimpleSpecificationBuilder;
import com.mall.common.specification.SpecificationOperator;
import com.mall.common.utils.ObjectMapperUtils;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 商品相关service接口的实现
 * @date 2019/04/21 17:54
 * @author wangyongchun
 * @version 1.0
 */
@Service
public class AdminProductServiceImpl implements IAdminProductService {

    @Autowired
    private AdminProductRepository productRepository;
    @Autowired
    private AdminProductVerifyRecordRepository productVerifyRecordRepository;

    @Override
    public void create(AdminBrandParams params) {
        AdminProduct product = new AdminProduct();
        BeanUtils.copyProperties(params, product);
        product.setDeleteStatus(AdminRecordStatusEnum.NOEMAL.getCode());
        productRepository.save(product);

        // todo
        //会员价格
        //阶梯价格
        //满减价格
        //处理sku的编码
        //添加sku库存信息
        //添加商品参数,添加自定义商品规格
        //关联专题
        //关联优选
    }

    @Override
    public AdminProductResult getUpdateInfo(Long id) {
        AdminProduct product = productRepository.findById(id).orElse(null);
        return ObjectMapperUtils.map(product, AdminProductResult.class);
    }

    @Override
    public void update(Long id, AdminBrandParams updateParams) {
        AdminProduct product = productRepository.findById(id).orElse(null);
        if (product == null){
            throw new BusinessException("id为[" + id + "]的商品不存在");
        }
        AdminProduct updateProduct = new AdminProduct();
        BeanUtils.copyProperties(updateParams, updateProduct);
        updateProduct.setDeleteStatus(AdminRecordStatusEnum.NOEMAL.getCode());
        productRepository.save(updateProduct);
        // todo
        //会员价格
        //阶梯价格
        //满减价格
        //修改sku库存信息
        ////修改商品参数,添加自定义商品规格
        //关联专题
        //关联优选
    }

    @Override
    public Pagination<AdminProductResult> getList(AdminProductQueryParams params, Integer page, Integer pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        SimpleSpecificationBuilder<AdminProduct> builder = new SimpleSpecificationBuilder<>();
        Integer publishStatus = params.getPublishStatus();
        if (publishStatus != null){
            builder.addAnd("publishStatus", SpecificationOperator.Operator.EQ, publishStatus);
        }
        Integer verifyStatus = params.getVerifyStatus();
        if (verifyStatus != null){
            builder.addAnd("verifyStatus", SpecificationOperator.Operator.EQ, verifyStatus);
        }
        String keyword = params.getKeyword();
        if (!StringUtils.isEmpty(keyword)){
            builder.addAnd("keyword", SpecificationOperator.Operator.LIKEALL, keyword);
        }
        String productSn = params.getProductSn();
        if (!StringUtils.isEmpty(productSn)){
            builder.addAnd("productSn", SpecificationOperator.Operator.LIKEALL, productSn);
        }
        Long brandId = params.getBrandId();
        if (brandId != null){
            builder.addAnd("brandId", SpecificationOperator.Operator.EQ, brandId);
        }
        Long productCategoryId = params.getProductCategoryId();
        if (productCategoryId != null){
            builder.addAnd("productCategoryId", SpecificationOperator.Operator.EQ, productCategoryId);
        }
        builder.addAnd("deleteStatus", SpecificationOperator.Operator.EQ, AdminRecordStatusEnum.NOEMAL.getCode());
        Specification<AdminProduct> specification = builder.generateSpecification();
        Page<AdminProduct> products = productRepository.findAll(specification, pageable);
        return PaginationFormatUtils.format(products, AdminProductResult.class);
    }

    @Override
    public void updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        productRepository.updateVarifyStatus(ids, verifyStatus);
        //修改完审核状态后添加到审核记录
        List<AdminProductVerifyRecord> recordList = new ArrayList<>();
        for (Long id : ids){
            AdminProductVerifyRecord record = new AdminProductVerifyRecord();
            record.setProductId(id);
            recordList.add(record);
        }
        productVerifyRecordRepository.saveAll(recordList);
    }

    @Override
    public void updatePublishStatus(List<Long> ids, Integer publishStatus) {
        productRepository.updatePublishStatus(ids, publishStatus);
    }

    @Override
    public void updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        productRepository.updateRecommendStatus(ids, recommendStatus);
    }

    @Override
    public void updateNewStatus(List<Long> ids, Integer newStatus) {
        productRepository.updateNewStatus(ids, newStatus);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        productRepository.batchDelete(ids, AdminRecordStatusEnum.DELETED.getCode());
    }
}
