package com.zhxjz.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.controller.form.EasyUIPageForm;
import com.zhxjz.framework.model.easyui.PageGrid;
import com.zhxjz.framework.model.ext.ExtPageGrid;
import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.util.common.JsonUtil;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Active;
import com.zhxjz.model.Participation;
import com.zhxjz.service.ActiveService;
import com.zhxjz.service.ParticipationService;

@Controller
@RequestMapping("/active")
public class ActiveController {

	@Autowired
	private ActiveService activeService;

	@Autowired
	private ParticipationService participationService;

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "active/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<Active> list = activeService.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form) {
		Pager page = new Pager((form.getPage() - 1) * form.getRows(), form.getRows());
		List<Active> list = activeService.page(page);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "active/add";
	}

	@RequestMapping("/add.do")
	public String add(Active active, ModelMap model, int sessOrgId, String sessAccount) {
		active.setOrgId(sessOrgId);
		active.setUserAccount(sessAccount);
		active.setStartTime(new Date());
		activeService.add(active);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		Active active = activeService.get(id);
		model.put("active", active);
		return "active/update";
	}

	@RequestMapping("/update.do")
	public String update(Active active, ModelMap model, String sessAccount) {
		active.setUserAccount(sessAccount);
		active.setStartTime(new Date());
		activeService.update(active);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		activeService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		activeService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model, String sessAccount) {
		Active active = activeService.get(id);
		Participation participation = participationService.get(id, sessAccount);
		model.put("active", active);
		model.put("participation", participation);
		model.put("depart", participation != null);
		return "active/show";
	}

	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Active active = activeService.get(id);
		model.put("message", new JsonResult(active).toJson());
		return "message";
	}

}
