package com.zhxjz.service.permission;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Permission;
import com.zhxjz.model.permission.Role;

public interface RoleService {

	void add(Role role);

	void update(Role role);

	void delete(String name);

	List<Role> page(Pager page);

	Role get(String name);

	List<Role> listAll();

	void assignPermission(String roleName, List<String> permissionNameList);

	void batchDelete(List<String> roleNameList);

	List<Permission> listByRole(String roleName);

}
