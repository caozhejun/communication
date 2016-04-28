package com.zhxjz.dao.permission;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Permission;

public interface PermissionDao {

	void add(Permission permission);

	void update(Permission permission);

	void delete(String name);

	List<Permission> listAll();

	List<Permission> page(Pager page);

	Permission get(String name);

}
