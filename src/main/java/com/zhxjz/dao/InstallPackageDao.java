package com.zhxjz.dao;

import java.util.List;

import com.zhxjz.model.InstallPackage;

/**
 * 安装包Dao
 * 
 * @author caozj
 * 
 */
public interface InstallPackageDao {

	void add(InstallPackage installPackage);

	void update(InstallPackage installPackage);

	void delete(String installVersion);

	List<InstallPackage> listAll();

	InstallPackage getNewest();

	int count();

	void updateNewest(String version);

	void updateAllOld();

	InstallPackage get(String installVersion);

}
