package com.zhxjz.framework.session;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.zhxjz.framework.util.common.PathUtil;
import com.zhxjz.model.Org;
import com.zhxjz.model.permission.Permission;
import com.zhxjz.model.permission.Role;
import com.zhxjz.model.permission.User;

/**
 * Session工具类
 * 
 * @author caozj
 * 
 */
public class SessionUtil {

	private static final String orgKey = "sessOrg";

	// 存在session里的用户对象的key
	private static final String userKey = "sessUser";

	// 存在session里的角色对象的key
	private static final String roleKey = "sessRole";

	// 存在session里的权限对象的key
	private static final String permissionKey = "sessPermission";

	// 存在session里的 用户有权限访问的所有的url地址的key
	private static final String permissionUrlKey = "sessPermissionUrls";

	// 存在session里的标志，代表用户是否为超级管理员
	private static final String isAdminKey = "sessAdmin";

	public static void setOrg(HttpSession session, Org org) {
		session.setAttribute(orgKey, org);
	}

	public static Org getOrg(HttpSession session) {
		return (Org) session.getAttribute(orgKey);
	}

	public static void setAdmin(HttpSession session) {
		session.setAttribute(isAdminKey, true);
	}

	public static boolean isAdmin(HttpSession session) {
		Boolean isAdmin = (Boolean) session.getAttribute(isAdminKey);
		return isAdmin != null && isAdmin;
	}

	public static User getUser(HttpSession session) {
		return (User) session.getAttribute(userKey);
	}

	public static void setUser(HttpSession session, User user) {
		session.setAttribute(userKey, user);
	}

	@SuppressWarnings("unchecked")
	public static List<Role> getRoles(HttpSession session) {
		List<Role> list = (List<Role>) session.getAttribute(roleKey);
		return list != null ? list : new ArrayList<Role>(0);
	}

	public static void setRoles(HttpSession session, List<Role> roles) {
		session.setAttribute(roleKey, roles);
	}

	@SuppressWarnings("unchecked")
	public static List<Permission> getPermissions(HttpSession session) {
		List<Permission> list = (List<Permission>) session.getAttribute(permissionKey);
		return list != null ? list : new ArrayList<Permission>(0);
	}

	public static void setPermissions(HttpSession session, List<Permission> permissionList) {
		session.setAttribute(permissionKey, permissionList);
		Set<String> permissionUrlSet = new HashSet<String>();
		for (Permission permission : permissionList) {
			String url = permission.getUrl();
			String[] urls = url.split(",");
			for (String regUrl : urls) {
				if (StringUtils.isNotEmpty(regUrl)) {
					permissionUrlSet.add(regUrl);
				}
			}
		}
		session.setAttribute(permissionUrlKey, permissionUrlSet);
	}

	@SuppressWarnings("unchecked")
	public static Set<String> getPermissionUrl(HttpSession session) {
		Set<String> set = (Set<String>) session.getAttribute(permissionUrlKey);
		return set != null ? set : new HashSet<String>(0);
	}

	public static boolean hasPermission(HttpSession session, String uri) {
		if (isAdmin(session)) {
			return true;
		}
		if (StringUtils.isBlank(uri)) {
			return false;
		}
		Set<String> permissionUrlSet = SessionUtil.getPermissionUrl(session);
		boolean hasPermission = false;
		for (String permissionUrl : permissionUrlSet) {
			if (PathUtil.match(uri, permissionUrl)) {
				hasPermission = true;
				break;
			}
		}
		return hasPermission;
	}

	public static boolean hasPermission(HttpServletRequest request) {
		String uri = getRequestUri(request);
		return hasPermission(request.getSession(), uri);
	}

	/**
	 * 用户是否登录
	 * 
	 * @param session
	 * @return
	 */
	public static boolean isLogin(HttpSession session) {
		return getUser(session) != null;
	}

	/**
	 * 从request中取出uri.经过处理，已经过滤了上下文路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestUri(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (StringUtils.isNotEmpty(contextPath) && !"/".equals(contextPath)) {
			uri = uri.substring(contextPath.length());
		}
		return uri;
	}

	public static void clearSession(HttpSession session) {
		session.invalidate();
	}
}
