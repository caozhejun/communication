package com.zhxjz.dao.permission;

import java.util.List;

import com.zhxjz.model.permission.RolePermission;

public interface RolePermissionDao {

	void add(RolePermission rolePermission);

	void deleteByRoleName(String roleName);

	void deleteByPermissionName(String permissionName);

	List<RolePermission> listByRoleName(String roleName);
}
