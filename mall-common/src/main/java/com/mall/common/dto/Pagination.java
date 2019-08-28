package com.mall.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Pagination<T> implements Serializable{

    private int page;

    private int pageSize;

    private Long totalCounts;

    private int totalPages;

    private List<T> list;

}
