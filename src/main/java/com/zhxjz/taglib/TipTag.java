package com.zhxjz.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 省略显示提示文本的标签
 * 
 * @author caozj
 * 
 */
public class TipTag extends SuperTag {

	private static final long serialVersionUID = -6527511914147479880L;

	private static final Log logger = LogFactory.getLog(TipTag.class);

	private String content;

	private int length;

	private String onclick;

	private String target;

	private String href;

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	private String getTipContent() {
		if (length == 0) {
			length = 10;
		}
		String showContent = content;
		if (length < content.length()) {
			showContent = content.substring(0, length) + "...";
		}
		return showContent;
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
			StringBuilder tipHtml = new StringBuilder();
			tipHtml.append("<a ");
			if (StringUtils.isNotEmpty(onclick)) {
				tipHtml.append(" onclick='").append(onclick).append("' ");
			}
			if (StringUtils.isNotEmpty(href)) {
				tipHtml.append(" href='").append(href).append("' ");
			}
			if (StringUtils.isNotEmpty(target)) {
				tipHtml.append(" target='").append(target).append("' ");
			}
			if (StringUtils.isNotEmpty(className)) {
				tipHtml.append(" class='").append(className).append("' ");
			}
			tipHtml.append(" title='").append(content).append("' ");
			tipHtml.append(">");
			tipHtml.append(getTipContent());
			tipHtml.append("</a>");
			out.print(tipHtml.toString());
		} catch (IOException e) {
			logger.error(e);
		}
		return EVAL_PAGE;
	}

}
