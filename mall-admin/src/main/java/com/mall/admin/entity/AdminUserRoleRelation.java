package com.mall.admin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 用户与角色对应关系实体
 * created by wangyongchun 2019/02/24 00:38
 */
@Entity
@Table(name = "admin_role_relation")
@Data
public class AdminUserRoleRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id")
    private Long adminId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
