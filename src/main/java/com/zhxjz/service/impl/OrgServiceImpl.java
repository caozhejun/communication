package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.zhxjz.dao.OrgDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Org;
import com.zhxjz.service.OrgService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class OrgServiceImpl implements OrgService {

	@Autowired
	private OrgDao orgDao;

	@Override
	public void add(Org org) {
		orgDao.add(org);
	}

	@Override
	public void delete(int id) {
		orgDao.delete(id);
	}

	@Override
	public Org get(int id) {
		return orgDao.get(id);
	}

	@Override
	public List<Org> page(Pager page) {
		return orgDao.page(page);
	}

	@Override
	public int count() {
		return orgDao.count();
	}

	@Override
	public List<Org> listAll() {
		return orgDao.listAll();
	}

	@Override
	public void update(Org org) {
		orgDao.update(org);
	}
	
	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}
}
