package com.mall.admin.service;

import com.mall.admin.dto.AdminPermissionNode;
import com.mall.admin.dto.AdminPermissionParams;
import com.mall.admin.dto.AdminPermissionResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IAdminPermissionService {

    /**
     * 获取用户所有权限
     * @param adminId
     * @return
     */
    List<AdminPermissionResult> getPermissionsByAdminId(long adminId);

    /**
     * 新增权限
     * @param permissionParams
     * @return
     */
    long create(AdminPermissionParams permissionParams);

    /**
     * 更新权限
     * @param id
     * @param permissionParams
     * @return
     */
    void update(Long id, AdminPermissionParams permissionParams);

    /**
     * 批量删除权限
     * @param ids
     * @return
     */
    @Transactional
    void batchDelete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     * @return
     */
    List<AdminPermissionNode> treeList();

    /**
     * 获取所有权限
     * @return
     */
    List<AdminPermissionResult> list();

    /**
     * 获取单个权限的详情
     * @param id
     * @return
     */
    AdminPermissionResult getById(long id);

    /**
     * 获取角色相应的权限
     * @param roleId
     * @return
     */
    List<AdminPermissionResult> getPermissionsByRoleId(Long roleId);

    /**
     * 更新角色和权限的关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Transactional
    int updateRolePermissionRelations(Long roleId, List<Long> permissionIds);
}
