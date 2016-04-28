package com.zhxjz.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.controller.form.AdvertisementSearchForm;
import com.zhxjz.dao.AdvertisementDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.Advertisement;
import com.zhxjz.model.constant.ConstantData;

/**
 * 
 * 
 * @author caozj
 * 
 */
@Repository
public class AdvertisementDaoImpl implements AdvertisementDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "advertisement";

	@Override
	public void add(Advertisement advertisement) {
		String sql = "insert into " + table + "( title, content, time, userAccount, orgId, status, orderNo, startTime, endTime) values(?,?,?,?,?,?,?,?,?)";
		jdbc.updateForBoolean(sql, advertisement.getTitle(), advertisement.getContent(), advertisement.getTime(), advertisement.getUserAccount(), advertisement.getOrgId(), advertisement.getStatus(),
				advertisement.getOrderNo(), advertisement.getStartTime(), advertisement.getEndTime());
	}

	@Override
	public void update(Advertisement advertisement) {
		String sql = "update " + table + " set  title = ? , content = ? , time = ? , userAccount = ? , orgId = ? , status = ? , orderNo = ? , startTime = ? , endTime = ?  where id = ?  ";
		jdbc.updateForBoolean(sql, advertisement.getTitle(), advertisement.getContent(), advertisement.getTime(), advertisement.getUserAccount(), advertisement.getOrgId(), advertisement.getStatus(),
				advertisement.getOrderNo(), advertisement.getStartTime(), advertisement.getEndTime(), advertisement.getId());
	}

	@Override
	public void delete(int id) {
		String sql = "delete from " + table + " where id = ? ";
		jdbc.updateForBoolean(sql, id);
	}

	@Override
	public List<Advertisement> page(Pager page) {
		String sql = "select * from " + table;
		return jdbc.queryForPage(sql, Advertisement.class, page);
	}

	@Override
	public List<Advertisement> listAll() {
		String sql = "select * from " + table;
		return jdbc.queryForList(sql, Advertisement.class);
	}

	@Override
	public int count() {
		String sql = "select count(*) from " + table;
		return jdbc.queryForInt(sql);
	}

	@Override
	public Advertisement get(int id) {
		String sql = "select * from " + table + " where id = ? ";
		return jdbc.query(sql, Advertisement.class, id);
	}

	@Override
	public List<Advertisement> page(Pager page, AdvertisementSearchForm searchForm) {
		String sql = "select * from " + table + " where 1=1 ";
		StatementParameter param = new StatementParameter();
		if (searchForm.getOrgId() != ConstantData.ADMIN_ORGID) {
			sql += " and orgId = ? ";
			param.setInt(searchForm.getOrgId());
		}
		if (searchForm.getStatus() > 0) {
			sql += " and status = ? ";
			param.setInt(searchForm.getStatus());
		}
		if (StringUtils.isNotEmpty(searchForm.getUserAccount())) {
			sql += " and userAccount = ? ";
			param.setString(searchForm.getUserAccount());
		}
		if (searchForm.getAvoid() == 1) {
			sql += " and startTime <= ? and endTime >= ? ";
			Date now = new Date();
			param.setDate(now);
			param.setDate(now);
		}
		sql += " order by status asc,orderNo asc ,time desc";
		return jdbc.queryForPage(sql, Advertisement.class, page, param);
	}

}
