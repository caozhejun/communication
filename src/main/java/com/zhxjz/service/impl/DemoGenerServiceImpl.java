package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zhxjz.dao.DemoGenerDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.DemoGener;
import com.zhxjz.service.DemoGenerService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class DemoGenerServiceImpl implements DemoGenerService {

	@Autowired
	private DemoGenerDao demoGenerDao;

	@Override
	public void add(DemoGener demoGener) {
		demoGenerDao.add(demoGener);
	}

	@Override
	public void delete(int id) {
		demoGenerDao.delete(id);
	}

	@Override
	public DemoGener get(int id) {
		return demoGenerDao.get(id);
	}

	@Override
	public List<DemoGener> page(Pager page) {
		return demoGenerDao.page(page);
	}

	@Override
	public int count() {
		return demoGenerDao.count();
	}

	@Override
	public List<DemoGener> listAll() {
		return demoGenerDao.listAll();
	}

	@Override
	public void update(DemoGener demoGener) {
		demoGenerDao.update(demoGener);
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
}
