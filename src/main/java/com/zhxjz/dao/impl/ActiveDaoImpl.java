package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.ActiveDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Active;

/**
 * 
 * 
 * @author caozj
 *  
 */
@Repository
public class ActiveDaoImpl implements ActiveDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "active";

	@Override
	public void add(Active active) {
		String sql = "insert into " + table + "( title, content, endTime, userAccount, orgId, startTime) values(?,?,?,?,?,?)";
		jdbc.updateForBoolean(sql, active.getTitle(),active.getContent(),active.getEndTime(),active.getUserAccount(),active.getOrgId(),active.getStartTime());
	}

	@Override
	public void update(Active active) {
		String sql = "update " + table + " set  title = ? , content = ? , endTime = ? , userAccount = ? , orgId = ? , startTime = ?  where id = ?  ";
		jdbc.updateForBoolean(sql,active.getTitle(),active.getContent(),active.getEndTime(),active.getUserAccount(),active.getOrgId(),active.getStartTime(), active.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Active> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Active.class, page);
	}

	@Override
	public List<Active> listAll(){
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Active.class);
	}

	@Override
	public int count(){
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql); 
	}

	@Override
	public Active get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Active.class, id);
	}

}
