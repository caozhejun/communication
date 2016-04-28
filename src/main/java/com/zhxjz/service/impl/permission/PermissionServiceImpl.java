package com.zhxjz.service.impl.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.dao.permission.PermissionDao;
import com.zhxjz.dao.permission.RolePermissionDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Permission;
import com.zhxjz.service.permission.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	@Autowired
	private RolePermissionDao rolePermissionDao;

	@Override
	public void add(Permission permission) {
		permissionDao.add(permission);
	}

	@Override
	public void update(Permission permission) {
		permissionDao.update(permission);
	}

	@Override
	public void delete(String name) {
		permissionDao.delete(name);
		rolePermissionDao.deleteByPermissionName(name);
	}

	@Override
	public List<Permission> listAll() {
		return permissionDao.listAll();
	}

	@Override
	public List<Permission> page(Pager page) {
		return permissionDao.page(page);
	}

	@Override
	public Permission get(String name) {
		return permissionDao.get(name);
	}

	@Override
	public void batchDelete(List<String> names) {
		for (String name : names) {
			this.delete(name);
		}
	}

}
