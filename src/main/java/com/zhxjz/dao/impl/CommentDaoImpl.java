package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.CommentDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.Comment;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "comment";

	@Override
	public void add(Comment comment) {
		String sql = "insert into " + table + "( content, time, userAccount, name, type, masterId) values(?,?,?,?,?,?)";
		jdbc.updateForBoolean(sql, comment.getContent(), comment.getTime(), comment.getUserAccount(), comment.getName(), comment.getType(), comment.getMasterId());
	}

	@Override
	public void update(Comment comment) {
		String sql = "update " + table + " set  content = ? , time = ? , userAccount = ? , name = ? , type = ? , masterId = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, comment.getContent(), comment.getTime(), comment.getUserAccount(), comment.getName(), comment.getType(), comment.getMasterId(), comment.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Comment> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Comment.class, page);
	}

	@Override
	public List<Comment> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Comment.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public Comment get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Comment.class, id);
	}

	@Override
	public List<Comment> list(int id, int type) {
		String sql = "select * from " + table + " where masterId = ? and type = ? ";
		StatementParameter param = new StatementParameter();
		param.setInt(id);
		param.setInt(type);
		return jdbc.queryForList(sql, Comment.class, param);
	}

}
