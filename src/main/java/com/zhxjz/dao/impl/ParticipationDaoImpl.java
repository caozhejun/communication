package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.ParticipationDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.Participation;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class ParticipationDaoImpl implements ParticipationDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "participation";

	@Override
	public void add(Participation participation) {
		String sql = "insert into " + table + "( activeId, userAccount, time, num, remark) values(?,?,?,?,?)";
		jdbc.updateForBoolean(sql, participation.getActiveId(), participation.getUserAccount(), participation.getTime(), participation.getNum(), participation.getRemark());
	}

	@Override
	public void update(Participation participation) {
		String sql = "update " + table + " set  activeId = ? , userAccount = ? , time = ? , num = ? , remark = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, participation.getActiveId(), participation.getUserAccount(), participation.getTime(), participation.getNum(), participation.getRemark(), participation.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Participation> page(Pager page) {
		String sql = "select * from " + table + " order by time desc";
		return jdbc.queryForPage(sql, Participation.class, page);
	}

	@Override
	public List<Participation> listAll() {
		String sql = "select * from " + table + " order by time desc";
		return jdbc.queryForList(sql, Participation.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public Participation get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Participation.class, id);
	}

	@Override
	public Participation get(int activeId, String userAccount) {
		String sql = "select * from " + table + " where activeId=? and userAccount=?";
		return jdbc.query(sql, Participation.class, activeId, userAccount);
	}

	@Override
	public List<Participation> page(Pager page, int activeId) {
		String sql = "select * from " + table + " where  activeId=? order by time desc ";
		StatementParameter param = new StatementParameter();
		param.setInt(activeId);
		return jdbc.queryForPage(sql, Participation.class, page, param);
	}

	@Override
	public List<Participation> list(int activeId) {
		String sql = "select * from " + table + " where activeId=? order by time desc";
		StatementParameter param = new StatementParameter();
		param.setInt(activeId);
		return jdbc.queryForList(sql, Participation.class, param);
	}

}
