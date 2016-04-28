package com.zhxjz.service;

import java.util.List;

import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Participation;

/**
 *
 * 
 * @author caozj
 * 
 */
public interface ParticipationService {

	void add(Participation participation);

	void update(Participation participation);

	void delete(int id);

	List<Participation> listAll();

	int count();

	List<Participation> page(Pager page);

	Participation get(int id);

	void batchDelete(List<Integer> idList);

	Participation get(int activeId, String userAccount);

	List<Participation> page(Pager page, int activeId);
	
	List<Participation> list(int activeId);
}
