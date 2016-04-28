package com.zhxjz.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.controller.form.EasyUIPageForm;
import com.zhxjz.controller.form.NoticeSearchForm;
import com.zhxjz.framework.model.easyui.PageGrid;
import com.zhxjz.framework.model.ext.ExtPageGrid;
import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.util.common.JsonUtil;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Notice;
import com.zhxjz.model.enums.NoticeType;
import com.zhxjz.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@RequestMapping("/list.do")
	public String list(ModelMap model, NoticeSearchForm searchForm) {
		model.put("searchForm", searchForm);
		return "notice/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit, NoticeSearchForm searchForm, int sessOrgId) {
		searchForm.setOrgId(sessOrgId);
		Pager page = new Pager(start, limit);
		List<Notice> list = noticeService.page(page, searchForm);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, NoticeSearchForm searchForm, int sessOrgId) {
		searchForm.setOrgId(sessOrgId);
		Pager page = new Pager((form.getPage() - 1) * form.getRows(), form.getRows());
		List<Notice> list = noticeService.page(page, searchForm);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(ModelMap model, NoticeSearchForm searchForm) {
		model.put("searchForm", searchForm);
		return "notice/add";
	}

	@RequestMapping("/add.do")
	public String add(Notice notice, ModelMap model, int sessOrgId, String sessAccount) {
		notice.setTime(new Date());
		notice.setUserAccount(sessAccount);
		notice.setOrgId(sessOrgId);
		noticeService.add(notice);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		Notice notice = noticeService.get(id);
		model.put("notice", notice);
		return "notice/update";
	}

	@RequestMapping("/update.do")
	public String update(Notice notice, ModelMap model, String sessAccount) {
		notice.setTime(new Date());
		notice.setUserAccount(sessAccount);
		noticeService.update(notice);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		noticeService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		noticeService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model) {
		Notice notice = noticeService.get(id);
		model.put("notice", notice);
		return "notice/show";
	}

	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Notice notice = noticeService.get(id);
		model.put("message", new JsonResult(notice).toJson());
		return "message";
	}

	@RequestMapping("/listCommittee.do")
	public String listCommittee() {
		return "redirect:/notice/list.do?type=" + NoticeType.COMMITTEE.getId();
	}

	@RequestMapping("/listEstate.do")
	public String listEstate() {
		return "redirect:/notice/list.do?type=" + NoticeType.ESTATE.getId();
	}
}
