package com.zhxjz.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.controller.form.CommunicationSearchForm;
import com.zhxjz.dao.CommunicationDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.Communication;
import com.zhxjz.model.constant.ConstantData;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class CommunicationDaoImpl implements CommunicationDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "communication";

	@Override
	public void add(Communication communication) {
		String sql = "insert into " + table + "( title, content, time, userAccount, orgId, userName) values(?,?,?,?,?,?)";
		jdbc.updateForBoolean(sql, communication.getTitle(), communication.getContent(), communication.getTime(), communication.getUserAccount(), communication.getOrgId(), communication.getUserName());
	}

	@Override
	public void update(Communication communication) {
		String sql = "update " + table + " set  title = ? , content = ? , time = ? , userAccount = ? , orgId = ? , userName = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, communication.getTitle(), communication.getContent(), communication.getTime(), communication.getUserAccount(), communication.getOrgId(),
				communication.getUserName(), communication.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Communication> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Communication.class, page);
	}

	@Override
	public List<Communication> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Communication.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public Communication get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Communication.class, id);
	}

	@Override
	public List<Communication> page(Pager page, CommunicationSearchForm searchForm) {
		String sql = "select * from " + table + " where 1=1 ";
		StatementParameter param = new StatementParameter();
		if (searchForm.getOrgId() != ConstantData.ADMIN_ORGID) {
			sql += " and orgId = ? ";
			param.setInt(searchForm.getOrgId());
		}
		if (StringUtils.isNotEmpty(searchForm.getUserAccount())) {
			sql += " and userAccount = ? ";
			param.setString(searchForm.getUserAccount());
		}
		sql += " order by time desc ";
		return jdbc.queryForPage(sql, Communication.class, page, param);
	}

}
