package com.mall.admin.service.impl;

import com.mall.admin.dao.AdminPermissionRepository;
import com.mall.admin.dao.AdminRolePermissionRelationReposity;
import com.mall.admin.dto.AdminPermissionNode;
import com.mall.admin.dto.AdminPermissionParams;
import com.mall.admin.dto.AdminPermissionResult;
import com.mall.admin.dto.AdminRoleResult;
import com.mall.admin.entity.AdminPermission;
import com.mall.admin.entity.AdminRolePermissionRelation;
import com.mall.admin.enums.AdminRecordStatusEnum;
import com.mall.admin.service.IAdminPermissionService;
import com.mall.admin.service.IAdminRoleService;
import com.mall.common.exception.BusinessException;
import com.mall.common.specification.SimpleSpecificationBuilder;
import com.mall.common.specification.SpecificationOperator;
import com.mall.common.utils.ObjectMapperUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminPermissionServiceImpl implements IAdminPermissionService {

    @Autowired
    private IAdminRoleService roleService;
    @Autowired
    private AdminPermissionRepository adminPermissionRepository;
    @Autowired
    private AdminRolePermissionRelationReposity rolePermissionRelationReposity;

    @Override
    public List<AdminPermissionResult> getPermissionsByAdminId(long adminId) {
        List<AdminRoleResult> roleResultList = roleService.getRolesByAdminId(adminId);
        if (roleResultList.isEmpty()){
            return null;
        }
        List<Long> idList = roleResultList.stream()
                .map(AdminRoleResult::getId)
                .collect(Collectors.toList());
        Long[] ids = idList.toArray(new Long[idList.size()]);
        List<AdminPermission> permissions = adminPermissionRepository.findPermissionsByRole(ids);
        return ObjectMapperUtils.mapAll(permissions, AdminPermissionResult.class);
    }

    @Override
    public long create(AdminPermissionParams permissionParams) {
        AdminPermission permission = new AdminPermission();
        BeanUtils.copyProperties(permissionParams, permission);
        permission.setStatus(AdminRecordStatusEnum.NOEMAL.getCode());
        AdminPermission resultPermission = adminPermissionRepository.save(permission);
        return resultPermission.getId();
    }

    @Override
    public void update(Long id, AdminPermissionParams permissionParams) {
        AdminPermissionResult permissionResult = getById(id);
        if (permissionResult == null){
            throw new BusinessException("id为[" + id + "]的权限记录不存在");
        }
        AdminPermission updatePemission = new AdminPermission();
        BeanUtils.copyProperties(permissionParams, updatePemission);
        updatePemission.setId(id);
        adminPermissionRepository.save(updatePemission);
    }

    @Override
    public void batchDelete(List<Long> ids) {
        adminPermissionRepository.batchDelete(AdminRecordStatusEnum.DELETED.getCode(), ids);
    }

    @Override
    public List<AdminPermissionNode> treeList() {
        List<AdminPermission> permissionResultList = adminPermissionRepository.findAll();
        List<AdminPermissionNode> result = permissionResultList.stream()
                .filter(permission -> permission.getId().equals(0L))
                .map(permission -> convert(permission, permissionResultList)).collect(Collectors.toList());
        return result;
    }

    /**
     * 将权限转换为带有子级权限的对象
     * 当找不到子级权限的时候map操作不会再递归调用convert
     * @param permission
     * @param permissionList
     * @return
     */
    private AdminPermissionNode convert(AdminPermission permission, List<AdminPermission> permissionList){
        AdminPermissionNode node = new AdminPermissionNode();
        BeanUtils.copyProperties(permission, node);
        List<AdminPermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> convert(subPermission, permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public List<AdminPermissionResult> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Specification<AdminPermission> specification = new SimpleSpecificationBuilder<AdminPermission>()
                .addAnd("status", SpecificationOperator.Operator.EQ, AdminRecordStatusEnum.NOEMAL.getCode())
                .generateSpecification();
        List<AdminPermission> permissions = adminPermissionRepository.findAll(specification, sort);
        return ObjectMapperUtils.mapAll(permissions, AdminPermissionResult.class);
    }

    @Override
    public AdminPermissionResult getById(long id) {
        Specification<AdminPermission> specification = new SimpleSpecificationBuilder<AdminPermission>()
                .addAnd("id", SpecificationOperator.Operator.EQ, id)
                .addAnd("status", SpecificationOperator.Operator.EQ, AdminRecordStatusEnum.NOEMAL.getCode())
                .generateSpecification();
        AdminPermission permission = adminPermissionRepository.findOne(specification).orElse(null);
        return ObjectMapperUtils.map(permission, AdminPermissionResult.class);
    }

    @Override
    public List<AdminPermissionResult> getPermissionsByRoleId(Long roleId) {
        List<AdminRolePermissionRelation> rolePermissionRelations = rolePermissionRelationReposity.findByRoleId(roleId);
        if (rolePermissionRelations.isEmpty()){
            return null;
        }
        List<Long> roleIds = rolePermissionRelations.stream()
                .map(AdminRolePermissionRelation::getRoleId)
                .collect(Collectors.toList());
        Long[] ids = roleIds.toArray(new Long[roleIds.size()]);
        List<AdminPermission> permissionList = adminPermissionRepository.findPermissionsByRole(ids);
        return ObjectMapperUtils.mapAll(permissionList, AdminPermissionResult.class);
    }

    @Override
    public int updateRolePermissionRelations(Long roleId, List<Long> permissionIds) {
        int count = permissionIds.size();
        //先删除原来的角色和权限的对应关系
        rolePermissionRelationReposity.deleteAdminRolePermissionRelationByRoleId(roleId);
        //建立新的角色和权限对应关系
        List<AdminRolePermissionRelation> relationList = new ArrayList<>();
        for (Long permissionId : permissionIds){
            AdminRolePermissionRelation rolePermissionRelation = new AdminRolePermissionRelation();
            rolePermissionRelation.setPermissionId(permissionId);
            rolePermissionRelation.setRoleId(roleId);
            relationList.add(rolePermissionRelation);
        }
        rolePermissionRelationReposity.saveAll(relationList);

        return count;
    }
}
