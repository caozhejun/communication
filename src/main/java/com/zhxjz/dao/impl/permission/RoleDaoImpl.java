package com.zhxjz.dao.impl.permission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.permission.RoleDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Role;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "role";

	@Override
	public void add(Role role) {
		String sql = "insert into " + table + "(name,description) values(?,?)";
		jdbc.updateForBoolean(sql, role.getName(), role.getDescription());
	}

	@Override
	public void update(Role role) {
		String sql = "update " + table + " set description =? where name = ?";
		jdbc.updateForBoolean(sql, role.getDescription(), role.getName());
	}

	@Override
	public void delete(String name) {
		String sql = "delete from " + table + " where name = ?";
		jdbc.updateForBoolean(sql, name);
	}

	@Override
	public List<Role> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Role.class, page);
	}

	@Override
	public Role get(String name) {
		String sql = "select * from " + table + " where name = ?";
		return jdbc.query(sql, Role.class, name);
	}

	@Override
	public List<Role> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Role.class);
	}

}
