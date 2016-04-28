package com.zhxjz.dao.permission;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Role;

public interface RoleDao {

	void add(Role role);

	void update(Role role);

	void delete(String name);

	List<Role> page(Pager page);

	Role get(String name);

	List<Role> listAll();

}
