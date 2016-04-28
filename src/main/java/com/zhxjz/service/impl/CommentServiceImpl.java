package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.dao.CommentDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Comment;
import com.zhxjz.service.CommentService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public void add(Comment comment) {
		commentDao.add(comment);
	}

	@Override
	public void delete(int id) {
		commentDao.delete(id);
	}

	@Override
	public Comment get(int id) {
		return commentDao.get(id);
	}

	@Override
	public List<Comment> page(Pager page) {
		return commentDao.page(page);
	}

	@Override
	public int count() {
		return commentDao.count();
	}

	@Override
	public List<Comment> listAll() {
		return commentDao.listAll();
	}

	@Override
	public void update(Comment comment) {
		commentDao.update(comment);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public List<Comment> list(int id, int type) {
		return commentDao.list(id, type);
	}
}
