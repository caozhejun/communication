package com.zhxjz.framework.util.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.zhxjz.framework.session.page.PageParameter;
import com.zhxjz.framework.session.page.PageParameterUtil;

/**
 * 所有的bean初始化时执行的操作
 * 
 * @author caozj
 *
 */
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof PageParameter) {
			PageParameterUtil.registerPageParameter((PageParameter) bean);
		}
		return bean;
	}

}
