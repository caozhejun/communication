package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.BussDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.model.Buss;

@Repository("bussDao")
public class BussDaoImpl implements BussDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "buss";

	@Override
	public void add(Buss buss) {
		String sql = "insert into " + table + "(name,description,script) values(?,?,?)";
		jdbc.updateForBoolean(sql, buss.getName(), buss.getDescription(), buss.getScript());
	}

	@Override
	public void update(Buss buss) {
		String sql = "update " + table + " set description=?,script=? where name=?";
		jdbc.updateForBoolean(sql, buss.getDescription(), buss.getScript(), buss.getName());
	}

	@Override
	public void delele(String name) {
		String sql = "delete from " + table + " where name = ? ";
		jdbc.updateForBoolean(sql, name);
	}

	@Override
	public List<Buss> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Buss.class);
	}

	@Override
	public Buss get(String name) {
		String sql = "select * from " + table + " where name = ? ";
		return jdbc.query(sql, Buss.class, name);
	}

}
