package com.zhxjz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.framework.session.SessionUtil;
import com.zhxjz.framework.util.common.EncryptUtil;
import com.zhxjz.model.Org;
import com.zhxjz.model.constant.ConstantData;
import com.zhxjz.model.enums.Status;
import com.zhxjz.model.permission.Permission;
import com.zhxjz.model.permission.Role;
import com.zhxjz.model.permission.User;
import com.zhxjz.service.OrgService;
import com.zhxjz.service.permission.PermissionService;
import com.zhxjz.service.permission.RoleService;
import com.zhxjz.service.permission.UserService;

/**
 * 登陆控制器
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@Value("${administrator.username}")
	private String adminUserName;

	@Value("${administrator.password}")
	private String adminPassword;

	@Value("${key}")
	private String key;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private OrgService orgService;

	/**
	 * 进入登陆界面
	 * 
	 * @return
	 */
	@RequestMapping("/toLogin.do")
	public String toLogin(HttpServletRequest request, ModelMap model) {
		if (SessionUtil.isLogin(request.getSession())) {
			return "index";
		}
		model.put("allOrgs", orgService.listAll());
		return "login";
	}

	/**
	 * 登陆
	 * 
	 * @param userName
	 * @param password
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/login.do")
	public String login(String userName, String password, ModelMap model, HttpServletRequest request) {
		if (SessionUtil.isLogin(request.getSession())) {
			return "index";
		}
		String view = null;
		if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
			view = "login";
			model.put("errMsg", "用户名和密码不能为空");
		} else if (isAdmin(userName, password, request.getSession())) {
			view = "index";
		} else if (userService.checkUser(userName, password)) {
			User user = userService.get(userName);
			if (user.getStatus() == Status.APPROVE.getId()) {
				view = "index";
				List<Role> roleList = userService.listRoleByAccount(userName);
				List<Permission> permissionList = new ArrayList<Permission>();
				for (Role role : roleList) {
					List<Permission> list = roleService.listByRole(role.getName());
					permissionList.addAll(list);
				}
				SessionUtil.setOrg(request.getSession(), orgService.get(user.getOrgId()));
				SessionUtil.setUser(request.getSession(), user);
				SessionUtil.setRoles(request.getSession(), roleList);
				SessionUtil.setPermissions(request.getSession(), permissionList);
			} else if (user.getStatus() == Status.DOING.getId()) {
				view = "login";
				model.put("errMsg", "账号还在审批中，请耐心等待...");
			} else if (user.getStatus() == Status.REFUSE.getId()) {
				view = "login";
				String reason = "";
				if (StringUtils.isNotEmpty(user.getReason())) {
					reason = ",原因为:" + user.getReason();
				}
				model.put("errMsg", "账号登陆失败" + reason);
			}
		} else {
			view = "login";
			model.put("errMsg", "用户名或者密码错误");
		}
		model.put("userName", userName);
		model.put("password", password);
		model.put("allOrgs", orgService.listAll());
		return view;
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest request, ModelMap model) {
		SessionUtil.clearSession(request.getSession());
		model.put("allOrgs", orgService.listAll());
		return "login";
	}

	private boolean isAdmin(String userName, String password, HttpSession session) {
		boolean isAdmin = checkAdmin(userName, password);
		if (isAdmin) {
			User user = new User();
			user.setAccount(userName);
			user.setName("超级管理员");
			Org org = new Org();
			org.setAddress("超级管理员");
			org.setId(ConstantData.ADMIN_ORGID);
			org.setName("超级管理员");
			SessionUtil.setOrg(session, org);
			SessionUtil.setUser(session, user);
			SessionUtil.setAdmin(session);
			return true;
		}
		return false;
	}

	/**
	 * 验证用户名密码是否为超级管理员
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	private boolean checkAdmin(String userName, String password) {
		String un = EncryptUtil.md5(userName + key);
		if (!adminUserName.equals(un)) {
			return false;
		}
		String md = EncryptUtil.md5(password + key);
		if (!md.equals(adminPassword)) {
			return false;
		}
		return true;
	}
}
