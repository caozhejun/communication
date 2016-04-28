package com.zhxjz.codegenerate;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zhxjz.framework.util.common.ReflectUtil;
import com.zhxjz.framework.util.common.ServerUtil;
import com.zhxjz.framework.util.common.StringUtil;
import com.zhxjz.framework.util.velocity.VelocityUtil;
import com.zhxjz.model.constant.ConstantData;

/**
 * 代码生成器工具类
 * 
 * @author caozj
 *
 */
@Component
public class CodeGenerateUtil {

	private static final Log logger = LogFactory.getLog(CodeGenerateUtil.class);

	@Autowired
	private VelocityUtil velocityUtil;

	@Value("${code.generate.package}")
	private String packageName;

	/**
	 * 按照class对象生成代码
	 * 
	 * @param c
	 *            class对象
	 * @param dir
	 *            代码输入目录
	 * @throws IOException
	 */
	public void codeGenerate(Class<?> c, File dir) throws IOException {
		logger.info("生成代码" + c.getName() + "开始");
		initPath(dir);
		Map<String, Object> param = buildParam(c);
		generateClassFile(dir, c.getSimpleName(), param);
		logger.info("生成代码" + c.getName() + "结束");
	}

	/**
	 * 构造参数
	 * 
	 * @param c
	 * @return
	 */
	private Map<String, Object> buildParam(Class<?> c) {
		Map<String, Object> param = new HashMap<String, Object>();
		String className = c.getSimpleName();
		String firstLower = StringUtil.getFirstLower(className);
		param.put("className", className);
		param.put("firstLower", firstLower);
		param.put("packageName", packageName);
		List<String> fields = ReflectUtil.getAllPropertiesName(c);
		String updateProperties = "";
		String insertProperties = "";
		String params = "";
		String propertiesCount = "";
		for (String field : fields) {
			if ("id".equals(field) || "serialVersionUID".equals(field)) {
				continue;
			}
			updateProperties += " " + field + " = ? ,";
			insertProperties += " " + field + ",";
			propertiesCount += "?,";
			params += firstLower + ".get" + StringUtil.getFirstUpper(field) + "(),";
		}
		updateProperties = updateProperties.substring(0, updateProperties.length() - 1);
		insertProperties = insertProperties.substring(0, insertProperties.length() - 1);
		propertiesCount = propertiesCount.substring(0, propertiesCount.length() - 1);
		params = params.substring(0, params.length() - 1);
		param.put("updateProperties", updateProperties);
		param.put("insertProperties", insertProperties);
		param.put("params", params);
		param.put("propertiesCount", propertiesCount);
		return param;
	}

	/**
	 * 生成类文件
	 * 
	 * @param dir
	 * @param className
	 * @param param
	 * @throws IOException
	 */
	private void generateClassFile(File dir, String className, Map<String, Object> param) throws IOException {
		String controllerContent = getController(param);
		String serviceContent = getService(param);
		String serviceImplContent = getServiceImpl(param);
		String daoContent = getDao(param);
		String daoImplContent = getDaoImpl(param);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/controller/" + className + "Controller.java"), controllerContent, ConstantData.DEFAULT_CHARSET);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/service/" + className + "Service.java"), serviceContent, ConstantData.DEFAULT_CHARSET);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/service/impl/" + className + "ServiceImpl.java"), serviceImplContent, ConstantData.DEFAULT_CHARSET);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/dao/" + className + "Dao.java"), daoContent, ConstantData.DEFAULT_CHARSET);
		FileUtils.writeStringToFile(new File(dir.getAbsoluteFile() + "/dao/impl/" + className + "DaoImpl.java"), daoImplContent, ConstantData.DEFAULT_CHARSET);
	}

	private String getController(Map<String, Object> param) {
		return velocityUtil.toString("codegenerate/controller.vm", param);
	}

	private String getService(Map<String, Object> param) {
		return velocityUtil.toString("codegenerate/service.vm", param);
	}

	private String getServiceImpl(Map<String, Object> param) {
		return velocityUtil.toString("codegenerate/serviceImpl.vm", param);
	}

	private String getDao(Map<String, Object> param) {
		return velocityUtil.toString("codegenerate/dao.vm", param);
	}

	private String getDaoImpl(Map<String, Object> param) {
		return velocityUtil.toString("codegenerate/daoImpl4Mysql.vm", param);
	}

	/**
	 * 创建好所有需要的路径
	 */
	private void initPath(File dir) {
		String root = dir.getAbsolutePath();
		String dao = root + "/dao";
		String daoImpl = dao + "/impl";
		String service = root + "/service";
		String serviceImpl = service + "/impl";
		String controller = root + "/controller";
		new File(daoImpl).mkdirs();
		new File(serviceImpl).mkdirs();
		new File(controller).mkdirs();
	}

	/**
	 * 按照class对象数组生成代码
	 * 
	 * @param cs
	 *            class对象数组
	 * @param dir
	 *            代码输入目录
	 * @throws IOException
	 */
	public void codeGenerate(Class<?>[] cs, File dir) throws IOException {
		for (Class<?> c : cs) {
			codeGenerate(c, dir);
		}
	}

	/**
	 * 按照class对象生成代码
	 * 
	 * @param c
	 *            class对象
	 * @throws IOException
	 */
	public void codeGenerate(Class<?> c) throws IOException {
		codeGenerate(c, new File(ServerUtil.getSrcDir().getAbsolutePath() + "/" + getPackagePath()));
	}

	/**
	 * 按照class对象数组生成代码
	 * 
	 * @param cs
	 *            class对象数组
	 * @throws IOException
	 */
	public void codeGenerate(Class<?>[] cs) throws IOException {
		codeGenerate(cs, new File(ServerUtil.getSrcDir().getAbsolutePath() + "/" + getPackagePath()));
	}

	private String getPackagePath() {
		return packageName.replaceAll("\\.", "/");
	}
}
