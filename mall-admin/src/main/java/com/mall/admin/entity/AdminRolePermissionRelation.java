package com.mall.admin.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 后台用户角色和权限对应关系模型
 * created by wangyongchun 2019/02/23 16:49
 */
@Entity
@Table(name = "admin_role_permission_relation")
@Data
public class AdminRolePermissionRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
