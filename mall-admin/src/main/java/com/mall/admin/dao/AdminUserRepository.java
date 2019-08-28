package com.mall.admin.dao;

import com.mall.admin.entity.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long>,
        JpaSpecificationExecutor<AdminUser> {

    AdminUser findAdminUserByUsername(String username);

    @Query(nativeQuery = true,
            value = "update admin_user set login_time=?1 where username=?2")
    Boolean updateByUsername(Timestamp loginTime, String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "update admin_user set login_time=?1 where username=?2")
    int updateLoginTimeByUsername(Timestamp loginTime, String username);


}
