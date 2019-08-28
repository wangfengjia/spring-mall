package com.mall.admin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admin_brand")
@Data
public class AdminBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "first_letter")
    private String firstLetter;

    private Integer sort;

    @Column(name = "factory_status")
    private Integer factoryStatus;

    @Column(name = "product_count")
    private Integer productCount;

    @Column(name = "product_comment_count")
    private Integer productCommentCount;

    private String logo;

    @Column(name = "big_pic")
    private String bigPic;

    @Column(name = "brand_story")
    private String brandStory;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;
}
