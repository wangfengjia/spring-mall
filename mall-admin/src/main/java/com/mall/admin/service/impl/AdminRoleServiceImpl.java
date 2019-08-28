package com.mall.admin.service.impl;

import com.mall.admin.dao.AdminRoleRepository;
import com.mall.admin.dao.AdminUserRoleRelationRepository;
import com.mall.admin.dto.AdminRoleParams;
import com.mall.admin.dto.AdminRoleResult;
import com.mall.admin.entity.AdminRole;
import com.mall.admin.entity.AdminUserRoleRelation;
import com.mall.admin.enums.AdminRecordStatusEnum;
import com.mall.admin.service.IAdminRoleService;
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

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleServiceImpl implements IAdminRoleService {

    @Autowired
    private AdminRoleRepository adminRoleRepository;
    @Autowired
    private AdminUserRoleRelationRepository userRoleRelationRepository;

    /**
     * 获取用户所有角色
     * @param adminId
     * @return
     */
    @Override
    public List<AdminRoleResult> getRolesByAdminId(long adminId) {
        List<AdminRole> adminRoleList = adminRoleRepository.findByAdminId(adminId);
        List<AdminRoleResult> adminRoleResultList = ObjectMapperUtils.mapAll(adminRoleList, AdminRoleResult.class);
        return adminRoleResultList;
    }

    /**
     * 修改用户的角色
     * @param adminId
     * @param roleIds
     * @return
     */
    @Override
    public int updateRoles(long adminId, List<Long> roleIds) {
        int count = roleIds.size();
        //删除原来的关系
        userRoleRelationRepository.deleteAdminUserRoleRelationByAdminId(adminId);
        //建立新的用户与角色的对应关系
        List<AdminUserRoleRelation> userRoleRelations = new ArrayList<>();
        for (Long roleId : roleIds){
            AdminUserRoleRelation userRoleRelation = new AdminUserRoleRelation();
            userRoleRelation.setAdminId(adminId);
            userRoleRelation.setRoleId(roleId);
            userRoleRelations.add(userRoleRelation);
        }
        userRoleRelationRepository.saveAll(userRoleRelations);
        return count;
    }

    @Override
    public void create(AdminRoleParams roleParams) {
        AdminRole role = new AdminRole();
        BeanUtils.copyProperties(roleParams, role);
        role.setStatus(AdminRecordStatusEnum.NOEMAL.getCode());
        adminRoleRepository.save(role);
    }

    @Override
    public void update(Long id, AdminRoleParams updateRoleParams) {
        AdminRole role = adminRoleRepository.findById(id).orElse(null);
        if (role == null){
            throw new BusinessException("id为[" + id + "]的角色记录不存在");
        }
        AdminRole updateRole = new AdminRole();
        BeanUtils.copyProperties(updateRoleParams, updateRole);
        updateRole.setId(id);
        updateRole.setStatus(AdminRecordStatusEnum.NOEMAL.getCode());
        adminRoleRepository.save(updateRole);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        adminRoleRepository.batchDelete(AdminRecordStatusEnum.DELETED.getCode(), ids);
    }

    @Override
    public Pagination<AdminRoleResult> getList(int page, int pageSize, String name) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        Specification<AdminRole> specification = new SimpleSpecificationBuilder<AdminRole>()
                .addAnd("name", SpecificationOperator.Operator.LIKEALL, name)
                .addAnd("status", SpecificationOperator.Operator.EQ, AdminRecordStatusEnum.NOEMAL.getCode())
                .generateSpecification();
        Page<AdminRole> roles = adminRoleRepository.findAll(specification, pageable);
        return PaginationFormatUtils.format(roles, AdminRoleResult.class);
    }
}
