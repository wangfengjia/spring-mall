package com.mall.admin.dao;

import com.mall.admin.entity.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AdminPermissionRepository
        extends JpaRepository<AdminPermission, Long>, JpaSpecificationExecutor<AdminPermission> {

    @Query(nativeQuery = true,
            value = "select a.id, a.pid, a.name, a.value, a.icon, a.type, a.uri, a.status, a.sort, a.created_at, a.updated_at " +
                    "from admin_permission as a left join admin_role_permission_relation as b on a.id = b.permission_id " +
                    "where b.role_id in ?1 and a.status = 2 order by id asc")
    List<AdminPermission> findPermissionsByRole(Long[] roleIds);

    @Modifying
    @Query(nativeQuery = true, value = "update admin_permission set status=?1 where id in ?2")
    void batchDelete(int status, List<Long> ids);
}
