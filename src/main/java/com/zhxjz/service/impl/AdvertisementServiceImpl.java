package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.controller.form.AdvertisementSearchForm;
import com.zhxjz.dao.AdvertisementDao;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Advertisement;
import com.zhxjz.service.AdvertisementService;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class AdvertisementServiceImpl implements AdvertisementService {

	@Autowired
	private AdvertisementDao advertisementDao;

	@Override
	public void add(Advertisement advertisement) {
		advertisementDao.add(advertisement);
	}

	@Override
	public void delete(int id) {
		advertisementDao.delete(id);
	}

	@Override
	public Advertisement get(int id) {
		return advertisementDao.get(id);
	}

	@Override
	public List<Advertisement> page(Pager page) {
		return advertisementDao.page(page);
	}

	@Override
	public int count() {
		return advertisementDao.count();
	}

	@Override
	public List<Advertisement> listAll() {
		return advertisementDao.listAll();
	}

	@Override
	public void update(Advertisement advertisement) {
		advertisementDao.update(advertisement);
	}

	@Override
	public void batchDelete(List<Integer> idList) {
		for (Integer id : idList) {
			this.delete(id);
		}
	}

	@Override
	public List<Advertisement> page(Pager page, AdvertisementSearchForm searchForm) {
		return advertisementDao.page(page, searchForm);
	}
}
