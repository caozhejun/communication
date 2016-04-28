package com.zhxjz.framework.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	private Log logger = LogFactory.getLog(this.getClass());

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		ApplicationContextUtil.context = context;
		logger.info("init ApplicationContext successfully");
	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static Object getBean(String beanName) {
		return context.getBean(beanName);
	}

	/**
	 * 取Bean实例
	 * 
	 * @param <T>
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getContext().getBean(clazz);
	}

	public static void registerBean(String beanName, Class<?> beanClass, Map<String, String> properties) {
		BeanDefinitionBuilder userBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(beanClass);
		for (Map.Entry<String, String> entry : properties.entrySet()) {
			userBeanDefinitionBuilder.addPropertyValue(entry.getKey(), entry.getValue());
		}
		DefaultListableBeanFactory defaultListableBeanFactory = getBeanFactory();
		if (defaultListableBeanFactory.containsBean(beanName)) {
			defaultListableBeanFactory.removeBeanDefinition(beanName);
		}
		defaultListableBeanFactory.registerBeanDefinition(beanName, userBeanDefinitionBuilder.getRawBeanDefinition());
	}

	/**
	 * 获取bean工厂
	 * 
	 * @return
	 */
	private static DefaultListableBeanFactory getBeanFactory() {
		ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
		return defaultListableBeanFactory;
	}

}
