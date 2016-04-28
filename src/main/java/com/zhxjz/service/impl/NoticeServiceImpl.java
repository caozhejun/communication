package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.controller.form.NoticeSearchForm;
import com.zhxjz.dao.NoticeDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Notice;
import com.zhxjz.service.NoticeService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;

	@Override
	public void add(Notice notice) {
		noticeDao.add(notice);
	}

	@Override
	public void delete(int id) {
		noticeDao.delete(id);
	}

	@Override
	public Notice get(int id) {
		return noticeDao.get(id);
	}

	@Override
	public List<Notice> page(Pager page) {
		return noticeDao.page(page);
	}

	@Override
	public int count() {
		return noticeDao.count();
	}

	@Override
	public List<Notice> listAll() {
		return noticeDao.listAll();
	}

	@Override
	public void update(Notice notice) {
		noticeDao.update(notice);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public List<Notice> page(Pager page, NoticeSearchForm searchForm) {
		return noticeDao.page(page, searchForm);
	}
}
