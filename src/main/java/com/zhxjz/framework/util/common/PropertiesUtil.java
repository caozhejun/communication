package com.zhxjz.framework.util.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * properties文件操作工具类
 * 
 * @author caozj
 *
 */
public class PropertiesUtil {

	private static final Log logger = LogFactory.getLog(PropertiesUtil.class);

	/**
	 * 读取properties文件内容
	 * 
	 * @param resourcePath
	 *            文件地址，相对于classpath,例如/config.properties
	 * @return
	 * @throws IOException
	 */
	public static Map<String, String> read(String resourcePath) {
		InputStream in = PropertiesUtil.class.getResourceAsStream(resourcePath);
		return readProperties(in);
	}

	/**
	 * 从输入流读取properties内容
	 * 
	 * @param in
	 * @return
	 * @throws IOException
	 */
	private static Map<String, String> readProperties(InputStream in) {
		Map<String, String> m = new HashMap<String, String>();
		try {
			Properties p = new Properties();
			p.load(in);
			for (Object key : p.keySet()) {
				m.put(key.toString(), p.getProperty(key.toString()));
			}
		} catch (Exception e) {
			logger.error("读取properties文件异常", e);
		} finally {
			IOUtils.closeQuietly(in);
		}
		return m;
	}

	/**
	 * 读取properties文件内容
	 * 
	 * @param filePath
	 *            文件地址
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, String> readFile(String filePath) throws FileNotFoundException {
		FileInputStream in = new FileInputStream(filePath);
		return readProperties(in);
	}

	/**
	 * 保存properties文件
	 * 
	 * @param resourcePath
	 * @param m
	 * @throws FileNotFoundException
	 */
	public static void save(String resourcePath, Map<String, String> m) throws FileNotFoundException {
		InputStream in = PropertiesUtil.class.getResourceAsStream(resourcePath);
		OutputStream out = new FileOutputStream(PropertiesUtil.class.getResource(resourcePath).getPath());
		writeProperties(m, in, out);
	}

	/**
	 * 保存properties文件
	 * 
	 * @param filePath
	 * @param m
	 * @throws FileNotFoundException
	 */
	public static void saveFile(String filePath, Map<String, String> m) throws FileNotFoundException {
		writeProperties(m, new FileInputStream(filePath), new FileOutputStream(filePath));
	}

	/**
	 * 将数据写到properties输出流中
	 * 
	 * @param m
	 * @param in
	 * @param out
	 */
	private static void writeProperties(Map<String, String> m, InputStream in, OutputStream out) {
		try {
			Properties p = new Properties();
			p.load(in);
			for (Map.Entry<String, String> entry : m.entrySet()) {
				p.setProperty(entry.getKey(), entry.getValue());
			}
			p.store(out, "");
		} catch (Exception e) {
			logger.error("保存properties文件异常", e);
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}

}
