package com.zhxjz.dao.impl.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.permission.RolePermissionDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.model.permission.RolePermission;

@Repository("rolePermissionDao")
public class RolePermissionDaoImpl implements RolePermissionDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "rolePermission";

	@Override
	public void add(RolePermission rolePermission) {
		String sql = "insert into " + table + "(roleName,permissionName) values(?,?)";
		jdbc.updateForBoolean(sql, rolePermission.getRoleName(), rolePermission.getPermissionName());
	}

	@Override
	public void deleteByRoleName(String roleName) {
		String sql = "delete from " + table + " where roleName = ? ";
		jdbc.updateForBoolean(sql, roleName);
	}

	@Override
	public void deleteByPermissionName(String permissionName) {
		String sql = "delete from " + table + " where permissionName = ? ";
		jdbc.updateForBoolean(sql, permissionName);
	}

	@Override
	public List<RolePermission> listByRoleName(String roleName) {
		String sql = "select * from " + table + " where roleName = ? ";
		return jdbc.queryForList(sql, RolePermission.class, roleName);
	}

}
