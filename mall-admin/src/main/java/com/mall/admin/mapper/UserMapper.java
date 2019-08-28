package com.mall.admin.mapper;

import com.mall.admin.entity.AdminUser;

import java.util.List;

public interface UserMapper {

    List<AdminUser> getAll();

    AdminUser getOne(Long id);
}
