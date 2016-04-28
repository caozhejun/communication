package com.zhxjz.service.impl.permission;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhxjz.controller.form.UserSearchForm;
import com.zhxjz.dao.permission.RoleDao;
import com.zhxjz.dao.permission.UserDao;
import com.zhxjz.dao.permission.UserRoleDao;
import com.zhxjz.framework.util.common.EncryptUtil;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Role;
import com.zhxjz.model.permission.User;
import com.zhxjz.model.permission.UserRole;
import com.zhxjz.service.permission.UserService;

/**
 * 用户service实现
 * 
 * @author caozj
 * 
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Value("${key}")
	private String key;

	@Value("${default.pwd}")
	private String defaultPwd;

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserRoleDao userRoleDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public void add(User user) {
		if (StringUtils.isEmpty(user.getPwd())) {
			user.setPwd(defaultPwd);
		}
		userDao.add(user);
	}

	@Override
	public void delete(String account) {
		userDao.delete(account);
		userRoleDao.deleteByUserAccount(account);
	}

	@Override
	public void updateName(String account, String name) {
		userDao.updateName(account, name);
	}

	@Override
	public void updatePwd(String account, String pwd) {
		pwd = EncryptUtil.md5(pwd + key);
		userDao.updatePwd(account, pwd);
	}

	@Override
	public User get(String account) {
		return userDao.get(account);
	}

	@Override
	public List<User> page(Pager page) {
		return userDao.page(page);
	}

	@Override
	public boolean checkUser(String account, String pwd) {
		pwd = EncryptUtil.md5(pwd + key);
		return userDao.checkUser(account, pwd);
	}

	@Override
	public List<User> listAll() {
		return userDao.listAll();
	}

	@Override
	public void assignRole(String userAccount, String roleName) {
		userRoleDao.deleteByUserAccount(userAccount);
		userRoleDao.add(new UserRole(userAccount, roleName));
	}

	@Override
	public void updatePwd(String account, String oldPwd, String newPwd) {
		if (!checkUser(account, oldPwd)) {
			throw new RuntimeException("旧密码错误");
		}
		updatePwd(account, newPwd);
	}

	@Override
	public void resetPwd(String account) {
		userDao.updatePwd(account, defaultPwd);
	}

	@Override
	public void batchDelete(List<String> accountList) {
		for (String account : accountList) {
			delete(account);
		}
	}

	@Override
	public List<Role> listRoleByAccount(String account) {
		List<UserRole> userRoleList = userRoleDao.listByUserAccount(account);
		List<Role> roleList = new ArrayList<Role>(userRoleList.size());
		for (UserRole userRole : userRoleList) {
			String roleName = userRole.getRoleName();
			Role role = roleDao.get(roleName);
			if (role != null) {
				roleList.add(role);
			}
		}
		return roleList;
	}

	@Override
	public void assignRoles(String userAccount, List<String> roleNames) {
		userRoleDao.deleteByUserAccount(userAccount);
		for (String roleName : roleNames) {
			if (StringUtils.isNotEmpty(roleName)) {
				userRoleDao.add(new UserRole(userAccount, roleName));
			}
		}
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public boolean existAccount(String account) {
		return userDao.existAccount(account);
	}

	@Override
	public void batchUpdateStatus(List<String> accounts, int status) {
		for (String account : accounts) {
			userDao.updateStatus(account, status);
		}
	}

	@Override
	public List<User> page(Pager page, UserSearchForm form) {
		return userDao.page(page, form);
	}
}
