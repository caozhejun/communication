package com.zhxjz.service.permission;

import java.util.List;

import com.zhxjz.controller.form.UserSearchForm;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.Role;
import com.zhxjz.model.permission.User;

/**
 * 用户service(传进方法里的密码全部为明文密码)
 * 
 * @author caozj
 * 
 */
public interface UserService {

	void add(User user);

	void delete(String account);

	void update(User user);

	void updateName(String account, String name);

	/**
	 * 修改密码
	 * 
	 * @param account
	 *            账号
	 * @param pwd
	 *            密码(明文)
	 */
	void updatePwd(String account, String pwd);

	/**
	 * 修改密码
	 * 
	 * @param account
	 *            账号
	 * @param oldPwd
	 *            旧密码(明文)
	 * @param newPwd
	 *            新密码(明文)
	 */
	void updatePwd(String account, String oldPwd, String newPwd);

	User get(String account);

	List<User> page(Pager page);

	/**
	 * 检查用户是否账号密码正确
	 * 
	 * @param account
	 *            账号
	 * @param pwd
	 *            明文密码
	 * @return
	 */
	boolean checkUser(String account, String pwd);

	List<User> listAll();

	void assignRole(String userAccount, String roleName);

	void assignRoles(String userAccount, List<String> roleNames);

	void resetPwd(String account);

	void batchDelete(List<String> accountList);

	List<Role> listRoleByAccount(String account);

	boolean existAccount(String account);

	void batchUpdateStatus(List<String> accounts, int status);

	List<User> page(Pager page, UserSearchForm form);

}
