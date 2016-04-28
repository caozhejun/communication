package com.zhxjz.dao;

import java.util.List;

import com.zhxjz.controller.form.AdvertisementSearchForm;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Advertisement;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface AdvertisementDao {

	void add(Advertisement advertisement);

	void update(Advertisement advertisement);

	void delete(int id);

	List<Advertisement> listAll();

	int count();

	List<Advertisement> page(Pager page);

	Advertisement get(int id);

	List<Advertisement> page(Pager page, AdvertisementSearchForm searchForm);

}
