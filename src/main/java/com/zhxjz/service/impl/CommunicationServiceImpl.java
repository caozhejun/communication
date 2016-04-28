package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.controller.form.CommunicationSearchForm;
import com.zhxjz.dao.CommunicationDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Communication;
import com.zhxjz.service.CommunicationService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class CommunicationServiceImpl implements CommunicationService {

	@Autowired
	private CommunicationDao communicationDao;

	@Override
	public void add(Communication communication) {
		communicationDao.add(communication);
	}

	@Override
	public void delete(int id) {
		communicationDao.delete(id);
	}

	@Override
	public Communication get(int id) {
		return communicationDao.get(id);
	}

	@Override
	public List<Communication> page(Pager page) {
		return communicationDao.page(page);
	}

	@Override
	public int count() {
		return communicationDao.count();
	}

	@Override
	public List<Communication> listAll() {
		return communicationDao.listAll();
	}

	@Override
	public void update(Communication communication) {
		communicationDao.update(communication);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public List<Communication> page(Pager page, CommunicationSearchForm searchForm) {
		return communicationDao.page(page, searchForm);
	}
}
