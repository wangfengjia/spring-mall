package com.mall.admin.dao;

import com.mall.admin.entity.AdminUserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminUserRoleRelationRepository
        extends JpaSpecificationExecutor<AdminUserRoleRelation>, JpaRepository<AdminUserRoleRelation, Long> {

    void deleteAdminUserRoleRelationByAdminId(long adminId);
}
