package com.mall.admin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "admin_login_log")
public class AdminLoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "admin_id")
    private Long adminId;

    private String ip;

    private String address;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;
}
