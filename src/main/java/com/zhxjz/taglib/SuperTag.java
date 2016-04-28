package com.zhxjz.taglib;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 父标签 主要是取到Spring的ApplicationContext容器，方便获取Bean引用
 * 
 * @author caozj
 *
 */
public class SuperTag extends BodyTagSupport {

	private static final long serialVersionUID = -4948632853691953405L;

	private ApplicationContext context;

	public ApplicationContext getContext() {
		return context;
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		super.setPageContext(pageContext);
		context = WebApplicationContextUtils.getRequiredWebApplicationContext(pageContext.getServletContext());
	}

	/**
	 * 取Bean实例
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	/**
	 * 取Bean实例
	 * 
	 * @param beanId
	 * @return
	 */
	public Object getBean(String beanId) {
		return getContext().getBean(beanId);
	}
}
