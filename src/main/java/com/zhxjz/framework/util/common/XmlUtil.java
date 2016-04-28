package com.zhxjz.framework.util.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.XStream;

/**
 * xml工具类
 * 
 * @author caozj
 * 
 */
public class XmlUtil {

	/**
	 * 根据class类型，获取XStream对象
	 * 
	 * @param cs
	 * @return
	 */
	private static XStream getXStream(Class<?>... cs) {
		XStream xStream = new XStream();
		for (Class<?> c : cs) {
			xStream.alias(c.getSimpleName().toLowerCase(), c);
			xStream.processAnnotations(c);
		}
		return xStream;
	}

	/**
	 * 将list转化成XML
	 * 
	 * @param list
	 * @return
	 */
	public static String fromList(List<?> list) {
		if (CollectionUtils.isEmpty(list)) {
			return StringUtils.EMPTY;
		}
		Class<?> c = list.get(0).getClass();
		XStream xStream = getXStream(c);
		return xStream.toXML(list);
	}

	/**
	 * 将对象转化成XML
	 * 
	 * @param object
	 * @return
	 */
	public static String fromObject(Object object) {
		XStream xStream = getXStream(object.getClass());
		return xStream.toXML(object);
	}

	/**
	 * 将xml转化成对象
	 * 
	 * @param xml
	 * @param t
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static <T> T toOject(String xml, Class<T> t) {
		XStream xStream = getXStream(t);
		return (T) xStream.fromXML(xml);
	}

}
