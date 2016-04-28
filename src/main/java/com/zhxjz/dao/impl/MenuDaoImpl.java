package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.MenuDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.model.Menu;

/**
 * 菜单dao实现类
 * 
 * @author caozj
 * 
 */
@Repository("menuDao")
public class MenuDaoImpl implements MenuDao {

	@Autowired
	private Jdbc jdbc;

	@Value("${dbType}")
	private String dbType;

	private static final String table = "menu";

	@Override
	public void add(Menu menu) {
		String sql = null;
		if ("mysql".equalsIgnoreCase(dbType)) {
			sql = "insert into " + table + "(text,parentID,url,orderNo) values(?,?,?,?)";
		} else if ("oracle".equalsIgnoreCase(dbType)) {
			sql = "insert into " + table + "(id,text,parentID,url,orderNo) values(auto_incr_id.nextval,?,?,?,?)";
		} else {
			throw new RuntimeException("配置的数据库类型暂时不支持，请自己编写sql语句或者联系开发者支持新的数据库类型:" + dbType);
		}
		jdbc.updateForBoolean(sql, menu.getText(), menu.getParentID(), menu.getUrl(), menu.getOrderNo());
	}

	@Override
	public void update(Menu menu) {
		String sql = "update " + table + " set text =? ,parentID=?,url=?,orderNo=? where id = ?  ";
		jdbc.updateForBoolean(sql, menu.getText(), menu.getParentID(), menu.getUrl(), menu.getOrderNo(), menu.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Menu> listChildren(int parentID) {
		String sql = "select * from " + table + " where parentID = ? order by orderNo";
		return jdbc.queryForList(sql, Menu.class, parentID);
	}

	@Override
	public int countChildren(int parentID) {
		String sql = "select count(*) from " + table + " where parentID = ? ";
		return jdbc.queryForInt(sql, parentID);
	}

	@Override
	public Menu get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Menu.class, id);
	}

}
