package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.dao.MenuDao;
import com.zhxjz.model.Menu;
import com.zhxjz.service.MenuService;

/**
 * 菜单service实现类
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	@Override
	public void add(Menu menu) {
		menuDao.add(menu);
	}

	@Override
	public void update(Menu menu) {
		menuDao.update(menu);
	}

	@Override
	public void delete(int id) {
		List<Menu> menuList = menuDao.listChildren(id);
		if (menuList.size() > 0) {
			throw new RuntimeException("菜单下有子菜单，不能删除，请先删除子菜单");
		}
		menuDao.delete(id);
	}

	@Override
	public List<Menu> listChildren(int parentID) {
		List<Menu> menuList = menuDao.listChildren(parentID);
		for (Menu menu : menuList) {
			int childrenCount = menuDao.countChildren(menu.getId());
			if (childrenCount > 0) {
				menu.setLeaf(false);
				menu.setState("closed");
			} else {
				menu.setLeaf(true);
				menu.setState("open");
			}
		}
		return menuList;
	}

	@Override
	public void batchDelete(List<Integer> ids) {
		for (int id : ids) {
			this.delete(id);
		}
	}

	@Override
	public Menu get(int id) {
		return menuDao.get(id);
	}

}
