package com.mall.admin.bo;

import com.mall.admin.dto.AdminPermissionResult;
import com.mall.admin.entity.AdminPermission;
import com.mall.admin.entity.AdminUser;
import com.mall.admin.enums.AdminUserStatusEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
* SpringSecurity需要的用户详情
* create by wangyongchun 2019-01-27 14:41
*/
public class AdminUserDetails implements UserDetails {

    private AdminUser adminUser;

    private List<AdminPermissionResult> permissionList;

    public AdminUserDetails(AdminUser adminUser, List<AdminPermissionResult> permissionList){
        this.adminUser = adminUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return adminUser.getPassword();
    }

    @Override
    public String getUsername() {
        return adminUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return adminUser.getStatus().equals(AdminUserStatusEnum.ADMIN_USER_STATUS_NORMAL.getCode());
    }
}
