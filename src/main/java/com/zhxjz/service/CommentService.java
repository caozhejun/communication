package com.zhxjz.service;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Comment;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface CommentService {

	void add(Comment comment);

	void update(Comment comment);

	void delete(int id);

	List<Comment> listAll();

	int count();

	List<Comment> page(Pager page);

	Comment get(int id);

	void batchDelete(List<Integer> idList);

	List<Comment> list(int id, int type);

}
