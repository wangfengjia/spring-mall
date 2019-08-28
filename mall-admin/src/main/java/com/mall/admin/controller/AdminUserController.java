package com.mall.admin.controller;

import com.mall.admin.dto.AdminRoleResult;
import com.mall.admin.dto.AdminUserLoginParams;
import com.mall.admin.dto.AdminUserResult;
import com.mall.admin.dto.ModifyAdminUserParams;
import com.mall.admin.service.IAdminRoleService;
import com.mall.admin.service.IAdminUserService;
import com.mall.common.dto.Pagination;
import com.mall.common.dto.RestResponse;
import com.mall.common.valid.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * created by wangyongchun 2019/02/17 15：08
 */
@RestController
@Api(tags = "AdminUserController", value = "后台用户管理")
@RequestMapping(value = "/admin/user")
@Slf4j
@Validated
public class AdminUserController {

    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private IAdminRoleService roleService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "后台用户注册")
    @ApiImplicitParam(name = "adminUserParams", value = "后台用户注册参数", required = true, dataType = "ModifyAdminUserParams")
    @PostMapping(value = "/register")
    public RestResponse register(@RequestBody @Validated ModifyAdminUserParams adminUserParams) {
        try {
            adminUserService.register(adminUserParams);
        } catch (Exception e) {
            String message = "后台用户注册失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }

        return RestResponse.success();
    }

    @ApiOperation(value = "后台用户登录")
    @ApiImplicitParam(name = "loginParams", value = "后台用户登录参数", required = true, dataType = "AdminUserLoginParams")
    @PostMapping(value = "/login")
    public RestResponse login(@Validated @RequestBody AdminUserLoginParams loginParams) {
        String token = adminUserService.login(loginParams.getUsername(), loginParams.getPassword());
        if (token == null) {
            return RestResponse.fail("用户名或者密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RestResponse.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/token/refresh")
    public RestResponse refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminUserService.refreshToken(token);
        if (refreshToken == null) {
            String message = "刷新token失败";
            return RestResponse.fail(message);
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RestResponse.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping(value = "/info")
    public RestResponse getAdminUserInfo(Principal principal) {
        String username = principal.getName();
        AdminUserResult user = adminUserService.getAdminByUsername(username);
        List<AdminRoleResult> roleList = roleService.getRolesByAdminId(user.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("role", roleList);
        data.put("icon", user.getIcon());
        return RestResponse.success(data);
    }

    @ApiOperation(value = "用户登出功能")
    @GetMapping(value = "/logout")
    public RestResponse logout() {
        return RestResponse.success();
    }

    @ApiOperation(value = "获取后台用户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String"),
        @ApiImplicitParam(name = "page", value = "页码", required = false, defaultValue = "1", dataType = "int"),
        @ApiImplicitParam(name = "pageSize", value = "每页的数据条数", required = false, defaultValue = "20", dataType = "int")
    })
    @GetMapping(value = "/list")
    public RestResponse<Pagination<AdminUserResult>> getList(
            @RequestParam(name = "username", required = false) String username,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "20", required = false) int pageSize) {
        Pagination<AdminUserResult> list = adminUserService.getList(username, page, pageSize);
        return RestResponse.success(list);
    }

    @ApiOperation(value = "获取指定用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    @GetMapping(value = "/get_by_id")
    public RestResponse<AdminUserResult> getUserById(@RequestParam("id") Long id){
        AdminUserResult userResult = adminUserService.getAdminById(id);
        return RestResponse.success(userResult);
    }

    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParam(name = "adminUserParams", value = "ModifyAdminUserParams", required = true, dataType = "ModifyAdminUserParams")
    @PostMapping(value = "/update")
    public RestResponse update(@Validated(Update.class) @RequestBody ModifyAdminUserParams adminUserParams){
        try {
            Long id = adminUserParams.getId();
            adminUserService.update(id, adminUserParams);
        }catch (Exception e){
            String message = "更新用户信息失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }
        return RestResponse.success();
    }

    @ApiOperation(value = "删除指定用户信息")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long")
    @PostMapping(value = "/delete")
    public RestResponse delete(@RequestParam("id") Long id){
        try {
            adminUserService.delete(id);
        }catch (Exception e){
            String message = "删除用户失败,失败原因:" + e.getMessage();
            log.error(message, e);
            return RestResponse.fail(message);
        }
        return RestResponse.success();
    }
}
