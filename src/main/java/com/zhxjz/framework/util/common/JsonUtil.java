package com.zhxjz.framework.util.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.zhxjz.framework.model.json.JsonResult;

/**
 * json处理工具类
 * 
 * @author caozj
 */
public class JsonUtil {

	private JsonUtil() {

	}

	private static final ObjectMapper mapper = new ObjectMapper();

	private static final Log logger = LogFactory.getLog(JsonUtil.class);

	/**
	 * Object -> json
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			logger.error("Object转化成Json失败", e);
		}
		return null;
	}

	/**
	 * json -> Object
	 * 
	 * @param <T>
	 * @param content
	 * @param valueType
	 * @return
	 */
	public static <T> T toObject(String content, Class<T> valueType) {
		if (StringUtils.isEmpty(content)) {
			return null;
		}
		try {
			return mapper.readValue(content, valueType);
		} catch (Exception e) {
			logger.error("Json转化成Object失败", e);
		}
		return null;
	}

	/**
	 * json -> List
	 * 
	 * @param <T>
	 * @param content
	 * @param valueType
	 * @return
	 */
	public static <T> List<T> toList(String content, Class<T> valueType) {
		JsonNode node = null;
		try {
			node = mapper.readTree(content);
		} catch (Exception e) {
			logger.error("Json转化成List失败", e);
		}
		if (node == null) {
			return new ArrayList<T>(0);
		}
		Iterator<JsonNode> iterator = node.getElements();
		List<T> list = new ArrayList<T>();
		while (iterator.hasNext()) {
			String json = iterator.next().toString();
			T e = toObject(json, valueType);
			list.add(e);
		}
		return list;
	}

	/**
	 * success json
	 * 
	 * @return - {success:true}
	 */
	public static String successJson() {
		return toJson(new JsonResult());
	}

	/**
	 * result json
	 * 
	 * @param success
	 *            - 操作成功与否
	 * @param message
	 *            - 结果信息
	 * @return - {success:true,message:'test'}
	 */
	public static String resultJson(boolean success, String message) {
		return toJson(new JsonResult(message, success ? JsonResult.successCode : JsonResult.failCode));
	}

	/**
	 * result json
	 * 
	 * @param code
	 *            - 状态码
	 * @param message
	 *            - 结果信息
	 * @param data
	 *            - 结果数据
	 * @return
	 */
	public static String resultJson(int code, String message, Object data) {
		return toJson(new JsonResult(data, message, code));
	}
}
