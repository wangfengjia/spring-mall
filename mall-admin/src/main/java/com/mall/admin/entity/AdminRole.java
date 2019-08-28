package com.mall.admin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin_role")
@Data
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Column(name = "admin_count")
    private Integer adminCount;

    private Integer status;

    private Integer sort;

    @Column(name = "created_at")
    @CreationTimestamp
    @DateTimeFormat(style = "yyyy-MM-dd hh:mm:ss")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @DateTimeFormat(style = "yyyy-MM-dd hh:mm:ss")
    private Timestamp updatedAt;
}
