package com.zhxjz.controller.permission;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.controller.form.UserSearchForm;
import com.zhxjz.framework.model.ext.ExtPageGrid;
import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.session.SessionUtil;
import com.zhxjz.framework.util.common.EncryptUtil;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.constant.ConstantData;
import com.zhxjz.model.enums.Status;
import com.zhxjz.model.permission.Role;
import com.zhxjz.model.permission.User;
import com.zhxjz.service.OrgService;
import com.zhxjz.service.permission.RoleService;
import com.zhxjz.service.permission.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Value("${key}")
	private String key;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private OrgService orgService;

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "permission/listUser";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit,UserSearchForm form ,int sessOrgId) {
		form.setOrgId(sessOrgId);
		Pager page = new Pager(start, limit);
		List<User> list = userService.page(page,form);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(ModelMap model) {
		model.put("allOrgs", orgService.listAll());
		return "permission/addUser";
	}

	@RequestMapping("/add.do")
	public String add(User user, ModelMap model, int sessOrgId) {
		user.setCreateTime(new Date());
		user.setStatus(Status.APPROVE.getId());
		user.setOrgId(sessOrgId);
		userService.add(user);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(String account, HttpServletRequest request, ModelMap model) throws UnsupportedEncodingException {
		User loginUser = SessionUtil.getUser(request.getSession());
		User user = null;
		boolean self = true;
		if (StringUtils.isNotEmpty(account)) {
			account = URLDecoder.decode(account, ConstantData.DEFAULT_CHARSET);
			user = userService.get(account);
			if (!loginUser.getAccount().equals(account)) {
				self = false;
			}
		} else {
			user = loginUser;
		}
		model.put("self", self);
		model.put("user", user);
		model.put("statusList", Status.values());
		return "permission/updateUser";
	}

	@RequestMapping("/update.do")
	public String update(User user, ModelMap model) {
		User oldUser = userService.get(user.getAccount());
		user.setPwd(oldUser.getPwd());
		user.setCreateTime(new Date());
		userService.update(user);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(String[] accounts, ModelMap model) {
		List<String> accountList = Arrays.asList(accounts);
		userService.batchDelete(accountList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/resetPwd.do")
	public String resetPwd(String account, ModelMap model) {
		userService.resetPwd(account);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toModifyPwd.do")
	public String toModifyPwd() {
		return "user/modifyPwd";
	}

	@RequestMapping("/modifyPwd.do")
	public String modifyPwd(HttpServletRequest request, String oldPwd, String newPwd, String confirmPwd, ModelMap model) {
		if (!confirmPwd.equals(newPwd)) {
			throw new RuntimeException("确认密码和新密码不一致");
		}
		if (oldPwd.equals(newPwd)) {
			throw new RuntimeException("旧密码和新密码不能一样");
		}
		User user = SessionUtil.getUser(request.getSession());
		userService.updatePwd(user.getAccount(), oldPwd, newPwd);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toModifyInfo.do")
	public String toModifyInfo(HttpServletRequest request, ModelMap model) {
		User loginUser = SessionUtil.getUser(request.getSession());
		model.put("user", loginUser);
		return "user/modifyInfo";
	}

	@RequestMapping("/modifyInfo.do")
	public String modifyInfo(User user, HttpServletRequest request, ModelMap model) {
		User loginUser = SessionUtil.getUser(request.getSession());
		User oldUser = userService.get(loginUser.getAccount());
		user.setPwd(oldUser.getPwd());
		user.setCreateTime(new Date());
		userService.update(user);
		SessionUtil.setUser(request.getSession(), user);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toAssignRole.do")
	public String toAssignRole(String account, ModelMap model) {
		List<Role> allRoleList = roleService.listAll();
		List<Role> assignRoleList = userService.listRoleByAccount(account);
		model.put("account", account);
		model.put("allRoleList", allRoleList);
		model.put("assignRoleList", assignRoleList);
		return "permission/assignRole";
	}

	@RequestMapping("/assignRole.do")
	public String assignRole(String[] roleName, String account, HttpServletRequest request, ModelMap model) {
		if (roleName == null || roleName.length == 0) {
			throw new RuntimeException("角色不能为空");
		}
		userService.assignRoles(account, Arrays.asList(roleName));
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/registry.do")
	public String registry(User user, ModelMap model) {
		user.setPwd(EncryptUtil.md5(user.getPwd() + key));
		user.setCreateTime(new Date());
		user.setStatus(Status.DOING.getId());
		userService.add(user);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping
	public String checkAccount(String account, ModelMap model) {
		model.put("message", new JsonResult(userService.existAccount(account)).toJson());
		return "message";
	}

	@RequestMapping
	public String batchUpdateStatus(ModelMap model, int status, String[] accounts) {
		userService.batchUpdateStatus(Arrays.asList(accounts), status);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

}
