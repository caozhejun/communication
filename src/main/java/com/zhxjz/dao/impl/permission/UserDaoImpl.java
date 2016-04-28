package com.zhxjz.dao.impl.permission;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zhxjz.controller.form.UserSearchForm;
import com.zhxjz.dao.permission.UserDao;
import com.zhxjz.framework.util.jdbc.Jdbc;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.framework.util.jdbc.StatementParameter;
import com.zhxjz.model.constant.ConstantData;
import com.zhxjz.model.permission.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private Jdbc jdbc;

	private static final String table = "systemUser";

	@Override
	public void add(User user) {
		String sql = "insert into " + table + "(account,name,pwd,orgId,status,email,telNo,floorNo,unitNo,roomNo,idNo,createTime,reason) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		StatementParameter param = new StatementParameter();
		param.setString(user.getAccount());
		param.setString(user.getName());
		param.setString(user.getPwd());
		param.setInt(user.getOrgId());
		param.setInt(user.getStatus());
		param.setString(user.getEmail());
		param.setString(user.getTelNo());
		param.setString(user.getFloorNo());
		param.setString(user.getUnitNo());
		param.setString(user.getRoomNo());
		param.setString(user.getIdNo());
		param.setDate(user.getCreateTime());
		param.setString(user.getReason());
		jdbc.updateForBoolean(sql, param);
	}

	@Override
	public void update(User user) {
		String sql = "update " + table + " set name =? ,pwd=? ,orgId=?,status=?,email=?,telNo=?,floorNo=?,unitNo=?,roomNo=?,idNo=?,createTime=?,reason=? where account = ?  ";
		StatementParameter param = new StatementParameter();
		param.setString(user.getName());
		param.setString(user.getPwd());
		param.setInt(user.getOrgId());
		param.setInt(user.getStatus());
		param.setString(user.getEmail());
		param.setString(user.getTelNo());
		param.setString(user.getFloorNo());
		param.setString(user.getUnitNo());
		param.setString(user.getRoomNo());
		param.setString(user.getIdNo());
		param.setDate(user.getCreateTime());
		param.setString(user.getReason());
		param.setString(user.getAccount());
		jdbc.updateForBoolean(sql, param);
	}

	@Override
	public void delete(String account) {
		String sql = "delete from " + table + " where account = ? ";
		jdbc.updateForBoolean(sql, account);
	}

	@Override
	public void updateName(String account, String name) {
		String sql = "update " + table + " set name =  ? where account = ? ";
		jdbc.updateForBoolean(sql, name, account);
	}

	@Override
	public void updatePwd(String account, String pwd) {
		String sql = "update " + table + " set pwd =  ? where account = ? ";
		jdbc.updateForBoolean(sql, pwd, account);
	}

	@Override
	public User get(String account) {
		String sql = "select * from " + table + " where account = ? ";
		return jdbc.query(sql, User.class, account);
	}

	@Override
	public List<User> page(Pager page) {
		String sql = "select * from " + table + " order by createTime desc ";
		return jdbc.queryForPage(sql, User.class, page);
	}

	@Override
	public boolean checkUser(String account, String pwd) {
		String sql = "select count(*) from " + table + " where account = ? and pwd = ? ";
		return jdbc.queryForInt(sql, account, pwd) > 0;
	}

	@Override
	public List<User> listAll() {
		String sql = "select * from " + table + " order by createTime desc ";
		return jdbc.queryForList(sql, User.class);
	}

	@Override
	public void updateStatus(String account, int status) {
		String sql = "update " + table + " set status = ? where account = ? ";
		jdbc.updateForBoolean(sql, status, account);
	}

	@Override
	public boolean existAccount(String account) {
		String sql = "select count(*) from " + table + " where account = ?";
		return jdbc.queryForInt(sql, account) > 0;
	}

	@Override
	public List<User> page(Pager page, UserSearchForm form) {
		String sql = "select * from " + table + " where 1=1 ";
		StatementParameter param = new StatementParameter();
		if (form.getOrgId() != ConstantData.ADMIN_ORGID) {
			sql += " and orgId = ? ";
			param.setInt(form.getOrgId());
		}
		if (form.getStatus() != 0) {
			sql += " and status = ? ";
			param.setInt(form.getStatus());
		}
		if (StringUtils.isNotEmpty(form.getEmail())) {
			sql += " and email = ? ";
			param.setString(form.getEmail());
		}
		if (StringUtils.isNotEmpty(form.getName())) {
			sql += " and name = ? ";
			param.setString(form.getName());
		}
		if (StringUtils.isNotEmpty(form.getTelNo())) {
			sql += " and telNo = ? ";
			param.setString(form.getTelNo());
		}
		if (StringUtils.isNotEmpty(form.getFloorNo())) {
			sql += " and floorNo = ? ";
			param.setString(form.getFloorNo());
		}
		if (StringUtils.isNotEmpty(form.getUnitNo())) {
			sql += " and unitNo = ? ";
			param.setString(form.getUnitNo());
		}
		if (StringUtils.isNotEmpty(form.getRoomNo())) {
			sql += " and roomNo = ? ";
			param.setString(form.getRoomNo());
		}
		if (StringUtils.isNotEmpty(form.getIdNo())) {
			sql += " and idNo = ? ";
			param.setString(form.getIdNo());
		}
		sql += " order by createTime desc ,status asc";
		return jdbc.queryForPage(sql, User.class, page, param);
	}

}
