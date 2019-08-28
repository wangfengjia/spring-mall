package com.mall.common.utils;

import com.mall.common.dto.Pagination;
import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationFormatUtils {

    public static <T> Pagination<T> format(Page page, Class<T> outClass) {
        if (page == null){
            return null;
        }
        List content = page.getContent();
        content = ObjectMapperUtils.mapAll(content, outClass);
        Pagination<T> tPagination = new Pagination<>();
        tPagination.setPage(page.getPageable().getPageNumber());
        tPagination.setPageSize(page.getPageable().getPageSize());
        tPagination.setTotalPages(page.getTotalPages());
        tPagination.setTotalCounts(page.getTotalElements());
        tPagination.setList(content);

        return tPagination;
    }
}
