package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.OrgDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Org;

/**
 * 
 * 
 * @author caozj
 *  
 */
@Repository
public class OrgDaoImpl implements OrgDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "org";

	@Override
	public void add(Org org) {
		String sql = "insert into " + table + "( name, address) values(?,?)";
		jdbc.updateForBoolean(sql, org.getName(),org.getAddress());
	}

	@Override
	public void update(Org org) {
		String sql = "update " + table + " set  name = ? , address = ?  where id = ?  ";
		jdbc.updateForBoolean(sql,org.getName(),org.getAddress(), org.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Org> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Org.class, page);
	}

	@Override
	public List<Org> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Org.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public Org get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Org.class, id);
	}

}
