package com.zhxjz.framework.util.yun;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.zhxjz.framework.util.common.JsonUtil;
import com.zhxjz.framework.util.http.HttpClientUtil;
import com.zhxjz.model.constant.ConstantData;

/**
 * 百度云盘工具类
 * 
 * @author caozj
 *
 */
public class YunUtil {

	// 获取当前用户空间配额信息
	private static final String quotaUrl = "https://pcs.baidu.com/rest/2.0/pcs/quota";

	// 上传单个文件
	private static final String fileUploadUrl = "https://c.pcs.baidu.com/rest/2.0/pcs/file";

	// 下载单个文件
	private static final String downloadUrl = "https://d.pcs.baidu.com/rest/2.0/pcs/file";

	// 创建目录
	private static final String mkdirUrl = "https://pcs.baidu.com/rest/2.0/pcs/file";

	// 创建目录或者文件
	private static final String deleteUrl = "https://pcs.baidu.com/rest/2.0/pcs/file";

	/**
	 * 获取当前用户空间配额信息
	 * 
	 * @param token
	 * @return
	 */
	public static QuotaResult quota(String token) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("method", "info");
		param.put("access_token", token);
		String result = HttpClientUtil.get(quotaUrl, param);
		return JsonUtil.toObject(result, QuotaResult.class);
	}

	/**
	 * 上传文件
	 * 
	 * @param token
	 * @param path
	 *            上传文件路径（含上传的文件名称)
	 * @param file
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static UploadResult upload(String token, String path, String file) throws UnsupportedEncodingException {
		String queryString = "?method=upload&access_token=" + token + "&path=" + path + "&ondup=overwrite";
		queryString = URLEncoder.encode(queryString, ConstantData.DEFAULT_CHARSET);
		String url = fileUploadUrl + queryString;
		String result = HttpClientUtil.fileUpload(url, file);
		return JsonUtil.toObject(result, UploadResult.class);
	}

	/**
	 * 下载文件
	 * 
	 * @param token
	 * @param path
	 *            下载文件路径，以/开头的绝对路径
	 * @return
	 */
	public static String download(String token, String path) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("method", "download");
		param.put("access_token", token);
		param.put("path", path);
		return HttpClientUtil.post(downloadUrl, param);
	}

	/**
	 * 创建目录
	 * 
	 * @param token
	 * @param path
	 *            需要创建的目录，以/开头的绝对路径
	 */
	public static void createDir(String token, String path) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("method", "mkdir");
		param.put("access_token", token);
		param.put("path", path);
		HttpClientUtil.post(mkdirUrl, param);
	}

	/**
	 * 删除目录或者文件
	 * 
	 * @param token
	 * @param path
	 */
	public static void delete(String token, String path) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("method", "delete");
		param.put("access_token", token);
		param.put("path", path);
		HttpClientUtil.post(mkdirUrl, param);
	}
}
