package com.mall.admin.dao;

import com.mall.admin.entity.AdminProductVerifyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdminProductVerifyRecordRepository
        extends JpaRepository<AdminProductVerifyRecord, Long>, JpaSpecificationExecutor<AdminProductVerifyRecord> {
}
