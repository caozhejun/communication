package com.zhxjz.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.controller.form.CommunicationSearchForm;
import com.zhxjz.controller.form.EasyUIPageForm;
import com.zhxjz.framework.model.easyui.PageGrid;
import com.zhxjz.framework.model.ext.ExtPageGrid;
import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.util.common.JsonUtil;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Comment;
import com.zhxjz.model.Communication;
import com.zhxjz.model.enums.CommentType;
import com.zhxjz.service.CommentService;
import com.zhxjz.service.CommunicationService;

@Controller
@RequestMapping("/communication")
public class CommunicationController {

	@Autowired
	private CommunicationService communicationService;

	@Autowired
	private CommentService commentService;

	@RequestMapping
	public String listMine(String sessAccount) {
		return "redirect:/communication/list.do?userAccount=" + sessAccount;
	}

	@RequestMapping("/list.do")
	public String list(ModelMap model, CommunicationSearchForm searchForm) {
		model.put("searchForm", searchForm);
		return "communication/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit, CommunicationSearchForm searchForm, int sessOrgId) {
		searchForm.setOrgId(sessOrgId);
		Pager page = new Pager(start, limit);
		List<Communication> list = communicationService.page(page, searchForm);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, CommunicationSearchForm searchForm, int sessOrgId) {
		searchForm.setOrgId(sessOrgId);
		Pager page = new Pager((form.getPage() - 1) * form.getRows(), form.getRows());
		List<Communication> list = communicationService.page(page, searchForm);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(ModelMap model, CommunicationSearchForm searchForm) {
		model.put("searchForm", searchForm);
		return "communication/add";
	}

	@RequestMapping("/add.do")
	public String add(Communication communication, ModelMap model, int sessOrgId, String sessAccount, String sessName) {
		communication.setTime(new Date());
		communication.setOrgId(sessOrgId);
		communication.setUserName(sessName);
		communication.setUserAccount(sessAccount);
		communicationService.add(communication);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model, CommunicationSearchForm searchForm) {
		Communication communication = communicationService.get(id);
		model.put("communication", communication);
		model.put("searchForm", searchForm);
		return "communication/update";
	}

	@RequestMapping("/update.do")
	public String update(Communication communication, ModelMap model, int sessOrgId, String sessAccount, String sessName) {
		communication.setTime(new Date());
		communication.setOrgId(sessOrgId);
		communication.setUserName(sessName);
		communication.setUserAccount(sessAccount);
		communicationService.update(communication);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		communicationService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		communicationService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model, CommunicationSearchForm searchForm) {
		Communication communication = communicationService.get(id);
		List<Comment> commentList = commentService.list(communication.getId(), CommentType.Communication.getId());
		model.put("commentList", commentList);
		model.put("commentCount", commentList.size());
		model.put("communication", communication);
		model.put("type", CommentType.Communication.getId());
		model.put("searchForm", searchForm);
		return "communication/show";
	}

	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Communication communication = communicationService.get(id);
		model.put("message", new JsonResult(communication).toJson());
		return "message";
	}

}
