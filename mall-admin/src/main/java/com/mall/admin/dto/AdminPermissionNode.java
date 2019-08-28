package com.mall.admin.dto;

import com.mall.admin.entity.AdminPermission;
import lombok.Data;

import java.util.List;

@Data
public class AdminPermissionNode extends AdminPermission {

    private List<AdminPermissionNode> children;
}
