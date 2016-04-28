package com.zhxjz.framework.util.upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.zhxjz.common.CustomizedPropertyPlaceholderConfigurer;
import com.zhxjz.framework.util.common.ServerUtil;

/**
 * 文件上传工具类
 * 
 * @author caozj
 *
 */
public class UploadUtil {

	private static String storage;

	private static String localDir;

	private static final SimpleDateFormat format = new SimpleDateFormat("/yyyy/MM/dd");

	public static void init() {
		storage = CustomizedPropertyPlaceholderConfigurer.getContextProperty("files.storage");
		localDir = CustomizedPropertyPlaceholderConfigurer.getContextProperty("files.dir");
	}

	/**
	 * 获取唯一的文件名
	 * 
	 * @param fileName
	 *            原文件名
	 * @return
	 */
	public static String getUniqueFileName(String fileName) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		int beginIndex = fileName.lastIndexOf(".");
		if (beginIndex == -1) {
			return uuid;
		}
		String ext = fileName.substring(beginIndex);
		return uuid + ext;
	}

	/**
	 * 上传文件，返回文件的路径，可以直接使用http访问
	 * 
	 * @param content
	 *            文件内容
	 * @param fileName
	 *            文件名称
	 * @return
	 * @throws IOException
	 */
	public static String upload(byte[] content, String fileName) throws IOException {
		String absPath = getPath() + getUniqueFileName(fileName);
		String fullPath = ServerUtil.getWebRoot() + absPath;
		FileUtils.writeByteArrayToFile(new File(fullPath), content);
		return absPath;
	}

	/**
	 * 获取上传文件的路径
	 * 
	 * @return
	 */
	public static String getPath() {
		return "/" + localDir + format.format(new Date()) + "/";
	}

}
