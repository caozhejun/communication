package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zhxjz.dao.ActiveDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Active;
import com.zhxjz.service.ActiveService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class ActiveServiceImpl implements ActiveService {

	@Autowired
	private ActiveDao activeDao;

	@Override
	public void add(Active active) {
		activeDao.add(active);
	}

	@Override
	public void delete(int id) {
		activeDao.delete(id);
	}

	@Override
	public Active get(int id) {
		return activeDao.get(id);
	}

	@Override
	public List<Active> page(Pager page) {
		return activeDao.page(page);
	}

	@Override
	public int count() {
		return activeDao.count();
	}

	@Override
	public List<Active> listAll() {
		return activeDao.listAll();
	}

	@Override
	public void update(Active active) {
		activeDao.update(active);
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
}
