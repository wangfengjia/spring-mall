package com.mall.admin.controller;

import com.mall.admin.dto.AdminRoleParams;
import com.mall.admin.dto.AdminRoleResult;
import com.mall.admin.dto.DispatchRoleParams;
import com.mall.admin.service.IAdminRoleService;
import com.mall.common.dto.Pagination;
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
 * 后台用户角色管理
 * created by wangyongchun 2019/02/23 16:13
 */
@RestController
@Api(tags = "AdminRoleController", value = "后台用户角色管理")
@RequestMapping(value = "/admin/role")
@Slf4j
@Validated
public class AdminRoleController {

    @Autowired
    private IAdminRoleService adminRoleService;

    @ApiOperation(value = "获取用户的角色信息")
    @ApiImplicitParam(name = "adminId", value = "用户id", required = true, dataType = "int")
    @GetMapping(value = "/get_user_roles")
    public RestResponse<List<AdminRoleResult>> getUserRoles(@RequestParam int adminId){
        List<AdminRoleResult> adminRoleResultList = adminRoleService.getRolesByAdminId(adminId);
        return RestResponse.success(adminRoleResultList);
    }

    @ApiOperation(value = "给用户分配角色")
    @ApiImplicitParam(name = "dispatchRoleParams", value = "dispatchRoleParams参数", required = true, dataType = "DispatchRoleParams")
    @PostMapping(value = "/dispatch")
    public RestResponse dispatchRole(@RequestBody @Validated DispatchRoleParams dispatchRoleParams){
        //todo 写法
        try {
            int i = adminRoleService.updateRoles(dispatchRoleParams.getAdminId(), dispatchRoleParams.getRoleIds());
            if (i >= 0){
                return RestResponse.success();
            }
            return RestResponse.fail();
        }catch (Exception e){
            String message = "给用户分配角色失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }
    }

    @ApiOperation(value = "新增角色")
    @ApiImplicitParam(name = "params", value = "AdminRoleParams参数", dataType = "AdminRoleParams", required = true)
    @PostMapping(value = "/create")
    public RestResponse create(@RequestBody @Validated AdminRoleParams params){
        try {
            adminRoleService.create(params);
        }catch (Exception e){
            String message = "新建角色失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "更新角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", dataType = "Long", required = true),
            @ApiImplicitParam(name = "params", value = "AdminRoleParams参数", dataType = "AdminRoleParams", required = true)
    })
    @PostMapping(value = "/update/{id}")
    public RestResponse update(@PathVariable Long id, @RequestBody @Validated AdminRoleParams params){
        try {
            adminRoleService.update(id, params);
        }catch (Exception e){
            String message = "更新角色信息失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "批量删除角色")
    @ApiImplicitParam(name = "ids", value = "角色id数组", dataType = "List<Long>", required = true)
    @PostMapping(value = "/batch_delete")
    public RestResponse batchDelete(@RequestParam("ids") List<Long> ids){
        try {
            adminRoleService.batchDelete(ids);
        }catch (Exception e){
            String message = "批量删除角色失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = false, dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = false, dataType = "int"),
            @ApiImplicitParam(name = "name", value = "角色名称", required = false, dataType = "String")
    })
    @GetMapping(value = "/get_list")
    public RestResponse<Pagination<AdminRoleResult>> getList(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") int pageSize,
            @RequestParam(value = "name", required = false) String name
    ){
        Pagination<AdminRoleResult> list = adminRoleService.getList(page, pageSize, name);
        return RestResponse.success(list);
    }
}
