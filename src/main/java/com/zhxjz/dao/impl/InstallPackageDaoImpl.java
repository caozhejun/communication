package com.zhxjz.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.dao.InstallPackageDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.InstallPackage;

@Repository("installPackageDao")
public class InstallPackageDaoImpl implements InstallPackageDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "installPackage";

	@Override
	public void add(InstallPackage installPackage) {
		String sql = "insert into " + table + "(installVersion,detail,fileName,newest,publishDate) values(?,?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setString(installPackage.getInstallVersion());
		param.setString(installPackage.getDetail());
		param.setString(installPackage.getFileName());
		param.setInt(installPackage.getNewest());
		param.setString(installPackage.getPublishDate());
		jdbc.updateForBoolean(sql, param);
	}

	@Override
	public void update(InstallPackage installPackage) {
		String sql = "update " + table + " set detail=?,fileName=?,newest=?,publishDate=? where installVersion = ? ";
		StatementParameter param = new StatementParameter();
		param.setString(installPackage.getDetail());
		param.setString(installPackage.getFileName());
		param.setInt(installPackage.getNewest());
		param.setString(installPackage.getPublishDate());
		param.setString(installPackage.getInstallVersion());
		jdbc.updateForBoolean(sql, param);

	}

	@Override
	public void delete(String installVersion) {
		String sql = "delete from " + table + " where installVersion = ? ";
		jdbc.updateForBoolean(sql, installVersion);
	}

	@Override
	public List<InstallPackage> listAll() {
		String sql = "select * from " + table + " order by publishDate desc ";
		return jdbc.queryForList(sql, InstallPackage.class);
	}

	@Override
	public InstallPackage getNewest() {
		String sql = "select * from " + table + " where newest = 1";
		return jdbc.query(sql, InstallPackage.class);
	}

	@Override
	public int count() {
		String sql = "select count(1) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public void updateNewest(String version) {
		String sql = "update " + table + " set newest = 1 where installVersion = ? ";
		jdbc.updateForBoolean(sql, version);
	}

	@Override
	public void updateAllOld() {
		String sql = "update " + table + " set  newest = 0 ";
		jdbc.updateForBoolean(sql);
	}

	@Override
	public InstallPackage get(String installVersion) {
		String sql = "select * from " + table + " where installVersion = ? ";
		return jdbc.query(sql, InstallPackage.class, installVersion);
	}

}
