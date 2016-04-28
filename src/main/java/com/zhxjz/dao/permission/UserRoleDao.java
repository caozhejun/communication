package com.zhxjz.dao.permission;

import java.util.List;

import com.zhxjz.model.permission.UserRole;

public interface UserRoleDao {

	void add(UserRole userRole);

	void deleteByUserAccount(String userAccount);

	void deleteByRole(String roleName);

	List<UserRole> listByUserAccount(String userAccount);

}
