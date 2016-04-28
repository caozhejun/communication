package com.zhxjz.dao.impl.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.permission.PermissionDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.permission.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl implements PermissionDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "permission";

	@Override
	public void add(Permission permission) {
		String sql = "insert into " + table + "(name,description,url) values(?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setString(permission.getName());
		param.setString(permission.getDescription());
		param.setString(permission.getUrl());
		jdbc.updateForBoolean(sql, param);
	}

	@Override
	public void update(Permission permission) {
		String sql = "update " + table + " set description = ? , url = ? where name = ?";
		StatementParameter param = new StatementParameter();
		param.setString(permission.getDescription());
		param.setString(permission.getUrl());
		param.setString(permission.getName());
		jdbc.updateForBoolean(sql, param);

	}

	@Override
	public void delete(String name) {
		String sql = "delete from " + table + " where name = ?";
		jdbc.updateForBoolean(sql, name);
	}

	@Override
	public List<Permission> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Permission.class);
	}

	@Override
	public List<Permission> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Permission.class, page);
	}

	@Override
	public Permission get(String name) {
		String sql = "select * from " + table + " where name = ?";
		return jdbc.query(sql, Permission.class, name);
	}

}
