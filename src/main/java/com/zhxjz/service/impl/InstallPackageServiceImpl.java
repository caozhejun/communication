package com.zhxjz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.dao.InstallPackageDao;
import com.zhxjz.framework.util.common.DateUtil;
import com.zhxjz.model.InstallPackage;
import com.zhxjz.service.InstallPackageService;

@Service
@Transactional
public class InstallPackageServiceImpl implements InstallPackageService {

	@Autowired
	private InstallPackageDao installPackageDao;

	@Override
	public void add(InstallPackage installPackage) {
		installPackageDao.updateAllOld();
		installPackage.setNewest(1);
		installPackage.setPublishDate(DateUtil.now());
		installPackageDao.add(installPackage);
	}

	@Override
	public void update(InstallPackage installPackage) {
		installPackageDao.update(installPackage);
	}

	@Override
	public void delete(String installVersion) {
		installPackageDao.delete(installVersion);
	}

	@Override
	public List<InstallPackage> listAll() {
		return installPackageDao.listAll();
	}

	@Override
	public InstallPackage getNewest() {
		return installPackageDao.getNewest();
	}

	@Override
	public int count() {
		return installPackageDao.count();
	}

	@Override
	public InstallPackage get(String installVersion) {
		return installPackageDao.get(installVersion);
	}

	@Override
	public void updateNewest(String version) {
		installPackageDao.updateAllOld();
		installPackageDao.updateNewest(version);
	}

	@Override
	public void batchDelete(List<String> installVersionList) {
		for (String version : installVersionList) {
			this.delete(version);
		}
	}

}
