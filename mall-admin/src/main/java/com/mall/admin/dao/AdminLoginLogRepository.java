package com.mall.admin.dao;

import com.mall.admin.entity.AdminLoginLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginLogRepository extends JpaRepository<AdminLoginLog, Long> {
}
