package com.zhxjz.service.impl.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.dao.permission.PermissionDao;
import com.zhxjz.dao.permission.RoleDao;
import com.zhxjz.dao.permission.RolePermissionDao;
import com.zhxjz.dao.permission.UserRoleDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Permission;
import com.zhxjz.model.permission.Role;
import com.zhxjz.model.permission.RolePermission;
import com.zhxjz.service.permission.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private PermissionDao permissionDao;

	@Override
	public void add(Role role) {
		roleDao.add(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(String name) {
		roleDao.delete(name);
		userRoleDao.deleteByRole(name);
		rolePermissionDao.deleteByRoleName(name);
	}

	@Override
	public List<Role> page(Pager page) {
		return roleDao.page(page);
	}

	@Override
	public Role get(String name) {
		return roleDao.get(name);
	}

	@Override
	public List<Role> listAll() {
		return roleDao.listAll();
	}

	@Override
	public void assignPermission(String roleName, List<String> permissionNameList) {
		rolePermissionDao.deleteByRoleName(roleName);
		for (String permissionName : permissionNameList) {
			if (StringUtils.isNotEmpty(permissionName)) {
				rolePermissionDao.add(new RolePermission(roleName, permissionName));
			}
		}
	}

	@Override
	public void batchDelete(List<String> roleNameList) {
		for (String roleName : roleNameList) {
			delete(roleName);
		}
	}

	@Override
	public List<Permission> listByRole(String roleName) {
		List<RolePermission> rolePermissionList = rolePermissionDao.listByRoleName(roleName);
		List<Permission> permissionList = new ArrayList<Permission>(rolePermissionList.size());
		for (RolePermission rolePermission : rolePermissionList) {
			Permission permission = permissionDao.get(rolePermission.getPermissionName());
			permissionList.add(permission);
		}
		return permissionList;
	}

}
