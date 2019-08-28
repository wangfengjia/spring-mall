package com.mall.admin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 商品审核记录实体
 * @author wangyongchun
 * @date 2019/04/21 16:43
 * @version 1.0
 */
@Entity
@Table(name = "admin_product_vertify_record")
@Data
public class AdminProductVerifyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;
    private Long verifyMemberId;
    private Integer status;
    private String detail;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;
}
