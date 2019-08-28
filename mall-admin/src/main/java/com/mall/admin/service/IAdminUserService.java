package com.mall.admin.service;

import com.mall.admin.dto.AdminPermissionResult;
import com.mall.admin.dto.AdminUserResult;
import com.mall.admin.dto.ModifyAdminUserParams;
import com.mall.admin.entity.AdminUser;
import com.mall.common.dto.Pagination;

import java.util.List;

public interface IAdminUserService {

    /**
    * 根据用户名获取后台管理员
    */
    AdminUserResult getAdminByUsername(String username);

    /**
    * 注册功能
    */
    void register(ModifyAdminUserParams adminParamsDTO);

    /**
    * 登录功能
    * @param username 用户名
    * @param password 密码
    * @return 生成JWT的token
    */
    String login(String username, String password);

    /**
    * 刷新token的功能
    * @param oldToken 老的token
    * @return 新生成的JWT的token
    */
    String refreshToken(String oldToken);

    /**
    * 根据用户id获取用户
    * @param id 用户id
    */
    AdminUserResult getAdminById(Long id);


    /**
    * 根据用户或者昵称分页查询用户
    * @param username 用户名
    * @param page 页码
    * @param pageSize 每页的数据条数
    */
    Pagination<AdminUserResult> getList(String username, Integer page, Integer pageSize);

    /**
    * 修改指定用户信息
    * @param id 用户id
    * @param adminParamsDTO 用户信息
    */
    AdminUserResult update(Long id, ModifyAdminUserParams adminParamsDTO);

    /**
    * 删除指定的用户
    * @param id 用户id
    */
    AdminUserResult delete(Long id);


    /**
    * 获取用户所有角色
    * @param Integer id 用户id
    */


    /**
    * 获取用户所有权限(包括角色权限和+-权限)
    * @param adminUserId 用户id
    */
    List<AdminPermissionResult> getPermissionList(Long adminUserId);

    /**
     * 获取所有用户
     * @return
     */
    List<AdminUser> getAll();

}

