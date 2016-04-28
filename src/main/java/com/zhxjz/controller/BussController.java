package com.zhxjz.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.framework.model.ext.ExtPageGrid;
import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.util.common.JsonUtil;
import com.zhxjz.model.Buss;
import com.zhxjz.model.constant.ConstantData;
import com.zhxjz.service.BussService;

/**
 * 调用业务系统Controller
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/buss")
public class BussController {

	@Autowired
	private BussService bussService;

	@RequestMapping("/execute.do")
	public String execute(HttpServletRequest request, ModelMap model, String scriptName) {
		Map<String, String[]> params = request.getParameterMap();
		Object object = bussService.execute(params, scriptName);
		model.put("message", new JsonResult(object).toJson());
		return "message";
	}

	@RequestMapping
	public String toAdd() {
		return "buss/add";
	}

	@RequestMapping("/add.do")
	public String add(Buss buss, ModelMap model) {
		bussService.add(buss);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(String name, ModelMap model) throws UnsupportedEncodingException {
		name = URLDecoder.decode(name, ConstantData.DEFAULT_CHARSET);
		Buss buss = bussService.get(name);
		model.put("buss", buss);
		return "buss/update";
	}

	@RequestMapping("/update.do")
	public String update(Buss buss, ModelMap model) {
		bussService.update(buss);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/delete.do")
	public String delete(String name, ModelMap model) {
		bussService.delete(name);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(String[] names, ModelMap model) {
		bussService.batchDelete(Arrays.asList(names));
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "buss/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model) {
		List<Buss> list = bussService.listAll();
		String message = new JsonResult(new ExtPageGrid(list, list.size())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/get.do")
	public String get(String name, ModelMap model) {
		Buss buss = bussService.get(name);
		model.put("message", new JsonResult(buss).toJson());
		return "message";
	}

}
