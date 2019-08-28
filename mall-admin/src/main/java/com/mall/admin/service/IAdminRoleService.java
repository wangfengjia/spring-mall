package com.mall.admin.service;

import com.mall.admin.dto.AdminRoleParams;
import com.mall.admin.dto.AdminRoleResult;
import com.mall.common.dto.Pagination;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IAdminRoleService {

    /**
     * 获取指定用户的所有角色
     * @param adminId
     * @return
     */
    List<AdminRoleResult> getRolesByAdminId(long adminId);

    /**
     * 给用户分配角色
     * @param adminId
     * @param roleIds
     * @return
     */
    @Transactional
    int updateRoles(long adminId, List<Long> roleIds);

    /**
     * 新增角色
     * @param roleParams
     */
    void create(AdminRoleParams roleParams);

    /**
     * 更新角色信息
     * @param id
     * @param updateRoleParams
     */
    void update(Long id, AdminRoleParams updateRoleParams);

    /**
     * 批量删除角色
     * @param ids
     */
    @Transactional
    void batchDelete(List<Long> ids);

    /**
     * 获取角色列表
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    Pagination<AdminRoleResult> getList(int page, int pageSize, String name);
}
