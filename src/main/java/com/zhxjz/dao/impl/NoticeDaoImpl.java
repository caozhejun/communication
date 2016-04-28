package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.controller.form.NoticeSearchForm;
import com.zhxjz.dao.NoticeDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.Notice;
import com.zhxjz.model.constant.ConstantData;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "notice";

	@Override
	public void add(Notice notice) {
		String sql = "insert into " + table + "( title, content, time, userAccount, orgId, type) values(?,?,?,?,?,?)";
		jdbc.updateForBoolean(sql, notice.getTitle(), notice.getContent(), notice.getTime(), notice.getUserAccount(), notice.getOrgId(), notice.getType());
	}

	@Override
	public void update(Notice notice) {
		String sql = "update " + table + " set  title = ? , content = ? , time = ? , userAccount = ? , orgId = ? , type = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, notice.getTitle(), notice.getContent(), notice.getTime(), notice.getUserAccount(), notice.getOrgId(), notice.getType(), notice.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Notice> page(Pager page) {
		String sql = "select * from " + table + " order by time desc ";
		return jdbc.queryForPage(sql, Notice.class, page);
	}

	@Override
	public List<Notice> listAll() {
		String sql = "select * from " + table + " order by time desc";
		return jdbc.queryForList(sql, Notice.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public Notice get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Notice.class, id);
	}

	@Override
	public List<Notice> page(Pager page, NoticeSearchForm searchForm) {
		String sql = "select * from " + table + " where 1 = 1 ";
		StatementParameter param = new StatementParameter();
		if (searchForm.getOrgId() != ConstantData.ADMIN_ORGID) {
			sql += " and orgId = ? ";
			param.setInt(searchForm.getOrgId());
		}
		if (searchForm.getType() > 0) {
			sql += " and type = ? ";
			param.setInt(searchForm.getType());
		}
		sql += " order by time desc ";
		return jdbc.queryForPage(sql, Notice.class, page, param);
	}

}
