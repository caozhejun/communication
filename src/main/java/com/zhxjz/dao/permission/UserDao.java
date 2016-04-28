package com.zhxjz.dao.permission;

import java.util.List;

import com.zhxjz.controller.form.UserSearchForm;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.permission.User;

public interface UserDao {

	void add(User user);

	void update(User user);

	void delete(String account);

	void updateName(String account, String name);

	void updatePwd(String account, String pwd);

	User get(String account);

	List<User> page(Pager page);

	boolean checkUser(String account, String pwd);

	List<User> listAll();

	void updateStatus(String account, int status);

	boolean existAccount(String account);

	List<User> page(Pager page, UserSearchForm form);

}
