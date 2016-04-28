package com.zhxjz.dao;

import java.util.List;

import com.zhxjz.controller.form.NoticeSearchForm;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Notice;

/**
 * 
 * 
 * @author caozj
 * 
 */
public interface NoticeDao {

	void add(Notice notice);

	void update(Notice notice);

	void delete(int id);

	List<Notice> listAll();

	int count();

	List<Notice> page(Pager page);

	Notice get(int id);

	List<Notice> page(Pager page, NoticeSearchForm searchForm);

}
