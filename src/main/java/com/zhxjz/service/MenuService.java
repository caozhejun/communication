package com.zhxjz.service;

import java.util.List;

import com.zhxjz.model.Menu;

/**
 * 菜单service
 * 
 * @author caozj
 * 
 */
public interface MenuService {

	void add(Menu menu);

	void update(Menu menu);

	void delete(int id);

	List<Menu> listChildren(int parentID);

	void batchDelete(List<Integer> ids);

	Menu get(int id);

}
