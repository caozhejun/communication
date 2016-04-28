package com.zhxjz.dao;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Comment;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface CommentDao {

	void add(Comment comment);

	void update(Comment comment);

	void delete(int id);

	List<Comment> listAll();

	int count();

	List<Comment> page(Pager page);

	Comment get(int id);

	List<Comment> list(int id, int type);

}
