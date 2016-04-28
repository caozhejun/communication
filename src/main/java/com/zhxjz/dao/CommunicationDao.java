package com.zhxjz.dao;

import java.util.List;

import com.zhxjz.controller.form.CommunicationSearchForm;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Communication;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface CommunicationDao {

	void add(Communication communication);

	void update(Communication communication);

	void delete(int id);

	List<Communication> listAll();

	int count();

	List<Communication> page(Pager page);

	Communication get(int id);

	List<Communication> page(Pager page, CommunicationSearchForm searchForm);

}
