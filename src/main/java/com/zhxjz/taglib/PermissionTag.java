package com.zhxjz.taglib;

import java.io.IOException;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhxjz.framework.session.SessionUtil;

/**
 * 判断用户是否有访问某个url的权限的标签
 * 
 * @author caozj
 *
 */
public class PermissionTag extends SuperTag {

	private static final long serialVersionUID = 8989690814100321160L;

	private static final Log logger = LogFactory.getLog(PermissionTag.class);

	private String url;

	private HttpSession session;

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public int doEndTag() throws JspException {
		if (getBodyContent() != null) {
			String body = getBodyContent().getString();
			if (StringUtils.isNotBlank(body)) {
				content = body;
			}
		}
		if (StringUtils.isBlank(content)) {
			return EVAL_PAGE;
		}
		JspWriter out = pageContext.getOut();
		try {
			if (SessionUtil.hasPermission(session, url)) {
				out.print(content);
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return EVAL_PAGE;
	}
}
