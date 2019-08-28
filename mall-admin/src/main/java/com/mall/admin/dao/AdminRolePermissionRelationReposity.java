package com.mall.admin.dao;

import com.mall.admin.entity.AdminRolePermissionRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AdminRolePermissionRelationReposity extends
        JpaRepository<AdminRolePermissionRelation, Long>, JpaSpecificationExecutor<AdminRolePermissionRelation> {

    List<AdminRolePermissionRelation> findByRoleId(Long roleId);

    void deleteAdminRolePermissionRelationByRoleId(Long roleId);
}
