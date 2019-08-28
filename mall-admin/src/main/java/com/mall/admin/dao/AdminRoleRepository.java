package com.mall.admin.dao;

import com.mall.admin.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminRoleRepository
        extends JpaRepository<AdminRole, Long>, JpaSpecificationExecutor<AdminRole> {

    @Query(nativeQuery = true, value =
            "select a.id, a.name, a.description, a.admin_count, a.status, a.sort, a.created_at, a.updated_at " +
                    "from admin_role as a left join admin_role_relation as r " +
                    "on a.id = r.role_id where r.admin_id=?1 and a.status = 2")
    List<AdminRole> findByAdminId(long adminId);


    @Modifying
    @Query(nativeQuery = true,
            value = "update admin_role set status = ?1 where id in ?2")
    void batchDelete(int status, List<Long> ids);

}
