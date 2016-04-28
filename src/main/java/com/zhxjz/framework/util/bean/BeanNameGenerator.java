package com.zhxjz.framework.util.bean;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * 修改默认的BeanName的生成策略
 * 
 * @author caozj
 *
 */
public class BeanNameGenerator extends AnnotationBeanNameGenerator {

	@Override
	protected String buildDefaultBeanName(BeanDefinition definition) {
		String beanName = super.buildDefaultBeanName(definition);
		beanName = replaceBeanName(beanName);
		return beanName;
	}

	private String replaceBeanName(String beanName) {
		if (beanName.endsWith("Impl")) {
			beanName = beanName.substring(0, beanName.lastIndexOf("Impl"));
		}
		return beanName;
	}

}
