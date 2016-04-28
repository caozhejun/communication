package com.zhxjz.framework.session.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.zhxjz.framework.session.SessionUtil;

@Component
public class UserNamePageParameter implements PageParameter {

	@Override
	public String getKey() {
		return SessionKey.userNameKey;
	}

	@Override
	public String getValue(HttpServletRequest request, HttpServletResponse response) {
		return SessionUtil.getUser(request.getSession()).getName();
	}

}
