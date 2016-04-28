package com.zhxjz.framework.util.velocity;

import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * velocity模板工具类
 * 
 * @author caozj
 * 
 */
@Component
public class VelocityUtil {

	@Autowired
	private VelocityEngine velocityEngine;

	@Value("${vm.path}")
	private String vmPath;

	private String encode = "UTF-8";

	/**
	 * 解析velocigy模板，返回String
	 * 
	 * @param fileName
	 *            vm文件名，文件必须放在resource/vm/下，可以自己自定义子路径
	 * @param model
	 *            参数传递
	 * @return
	 */
	public String toString(String fileName, Map<String, Object> model) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmPath + fileName, encode, model);
	}

	/**
	 * 解析velocigy模板，返回String
	 * 
	 * @param fileName
	 *            vm文件名，文件必须放在resource/vm/下，可以自己自定义子路径
	 * @param encode
	 *            编码集
	 * @param model
	 *            参数传递
	 * @return
	 */
	public String toString(String fileName, String encode, Map<String, Object> model) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, vmPath + fileName, encode, model);
	}
}
