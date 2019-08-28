package com.mall.admin.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 首页推荐品牌实体类
 * @author wangyongchun
 * @date 2019/04/07 21:23
 * @version 1.0
 */
@Entity
@Table(name = "home_brand")
@Data
public class HomeBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long brandId;
    private String brandName;
    private Integer recommendStatus;
    private Integer deleteStatus;
    private Integer sort;

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

}
