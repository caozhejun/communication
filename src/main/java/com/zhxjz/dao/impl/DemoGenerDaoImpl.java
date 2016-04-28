package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.DemoGenerDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.DemoGener;

/**
 * 
 * 
 * @author caozj
 *  
 */
@Repository
public class DemoGenerDaoImpl implements DemoGenerDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "demoGener";

	@Override
	public void add(DemoGener demoGener) {
		String sql = "insert into " + table + "( name, age, num, d, f) values(?,?,?,?,?)";
		jdbc.updateForBoolean(sql, demoGener.getName(),demoGener.getAge(),demoGener.getNum(),demoGener.getD(),demoGener.getF());
	}

	@Override
	public void update(DemoGener demoGener) {
		String sql = "update " + table + " set  name = ? , age = ? , num = ? , d = ? , f = ?  where id = ?  ";
		jdbc.updateForBoolean(sql,demoGener.getName(),demoGener.getAge(),demoGener.getNum(),demoGener.getD(),demoGener.getF(), demoGener.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<DemoGener> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, DemoGener.class, page);
	}

	@Override
	public List<DemoGener> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, DemoGener.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public DemoGener get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, DemoGener.class, id);
	}

}
