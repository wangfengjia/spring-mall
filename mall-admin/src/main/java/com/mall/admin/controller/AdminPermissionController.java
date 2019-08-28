package com.mall.admin.controller;

import com.mall.admin.dto.AdminPermissionNode;
import com.mall.admin.dto.AdminPermissionParams;
import com.mall.admin.dto.AdminPermissionResult;
import com.mall.admin.dto.UpdateRolePermissionRelationParams;
import com.mall.admin.service.IAdminPermissionService;
import com.mall.common.dto.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户权限管理
 * created by wangyongchun 2019/02/23 17:04
 */
@RestController
@RequestMapping(value = "/admin/permission")
@Api(tags = "AdminPermissionController", value = "后台用户权限管理")
@Slf4j
@Validated
public class AdminPermissionController {

    @Autowired
    private IAdminPermissionService permissionService;

    @ApiOperation(value = "获取用户权限信息")
    @ApiImplicitParam(name = "adminId", value = "用户id", required = true, dataType = "int")
    @GetMapping(value = "/get_user_permissions")
    public RestResponse<List<AdminPermissionResult>> getUserPermissions(@RequestParam int adminId){
        List<AdminPermissionResult> permissionResultList = permissionService.getPermissionsByAdminId(adminId);
        return RestResponse.success(permissionResultList);
    }

    @ApiOperation(value = "添加权限")
    @ApiImplicitParam(name = "params", value = "新增权限参数", required = true, dataType = "AdminPermissionParams")
    @PostMapping(value = "/create")
    public RestResponse<Long> create(@RequestBody @Validated AdminPermissionParams params){
        Long id;
        try {
            id = permissionService.create(params);
        }catch (Exception e){
            String message = "添加权限失败,失败原因是:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }
        return RestResponse.success(id);
    }

    @ApiOperation(value = "更新权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "权限记录id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "params", value = "权限更新时的参数", required = true, dataType = "AdminPermissionParams")
    })
    @PostMapping(value = "/update/{id}")
    public RestResponse update(@PathVariable Long id, @RequestBody @Validated AdminPermissionParams params){
        try {
            permissionService.update(id, params);
        }catch (Exception e){
            String errorMessage = "权限更新失败,失败原因:" + e.getMessage();
            log.error(errorMessage, e);
            return RestResponse.fail(errorMessage);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "批量删除")
    @ApiImplicitParam(name = "ids", value = "权限记录id数组", dataType = "List<Long>", required = true)
    @PostMapping(value = "/batch_delete")
    public RestResponse batchDelete(@RequestParam("ids") List<Long> ids){
        try {
            permissionService.batchDelete(ids);
        }catch (Exception e){
            String message = "批量删除权限失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "以层级结构返回所有权限")
    @GetMapping(value = "/treeList")
    public RestResponse<List<AdminPermissionNode>> treeList(){
        List<AdminPermissionNode> nodeList = permissionService.treeList();
        return RestResponse.success(nodeList);
    }

    @ApiOperation("获取所有权限列表")
    @GetMapping(value = "/list")
    public RestResponse<List<AdminPermissionResult>> list(){
        List<AdminPermissionResult> permissionList = permissionService.list();
        return RestResponse.success(permissionList);
    }

    @ApiOperation(value = "获取角色权限信息")
    @ApiImplicitParam(name = "roleId", value = "角色id", dataType = "Long", required = true)
    @GetMapping(value = "/get_role_permissions")
    public RestResponse<List<AdminPermissionResult>> getRolePermissions(@RequestParam("roleId") Long roleId){
        List<AdminPermissionResult> permissionResultList = permissionService.getPermissionsByRoleId(roleId);
        return RestResponse.success(permissionResultList);
    }

    @ApiOperation(value = "更新角色权限关系")
    @ApiImplicitParam(name = "params", value = "更新角色和权限关系的参数", dataType = "UpdateRolePermissionRelationParams", required = true)
    @PostMapping(value = "update_role_permissions")
    public RestResponse updateRolePermissions(@RequestBody @Validated UpdateRolePermissionRelationParams params){
        try {
            permissionService.updateRolePermissionRelations(params.getRoleId(), params.getPermissionIds());
        }catch (Exception e){
            String message = "更新角色和权限关系失败,失败原因:" + e.getMessage();
            log.error(message, e);
        }

        return RestResponse.success();
    }
}
