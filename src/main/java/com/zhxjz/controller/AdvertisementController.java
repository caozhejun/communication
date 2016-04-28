package com.zhxjz.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.controller.form.AdvertisementSearchForm;
import com.zhxjz.controller.form.EasyUIPageForm;
import com.zhxjz.framework.model.easyui.PageGrid;
import com.zhxjz.framework.model.ext.ExtPageGrid;
import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.util.common.JsonUtil;
import com.zhxjz.framework.util.jdbc.Pager;
import com.zhxjz.model.Advertisement;
import com.zhxjz.model.enums.Status;
import com.zhxjz.service.AdvertisementService;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;

	@RequestMapping
	public String view(int sessOrgId) {
		return "redirect:/advertisement/list.do?status=" + Status.APPROVE.getId() + "&orgId=" + sessOrgId + "&avoid=1";
	}

	@RequestMapping
	public String manage() {
		return "redirect:/advertisement/list.do";
	}

	@RequestMapping
	public String listMine(String sessAccount) {
		return "redirect:/advertisement/list.do?userAccount=" + sessAccount;
	}

	@RequestMapping("/list.do")
	public String list(ModelMap model, AdvertisementSearchForm searchForm, int sessOrgId) {
		searchForm.setOrgId(sessOrgId);
		model.put("searchForm", searchForm);
		return "advertisement/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit, AdvertisementSearchForm searchForm) {
		Pager page = new Pager(start, limit);
		List<Advertisement> list = advertisementService.page(page, searchForm);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, AdvertisementSearchForm searchForm) {
		Pager page = new Pager((form.getPage() - 1) * form.getRows(), form.getRows());
		List<Advertisement> list = advertisementService.page(page, searchForm);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "advertisement/add";
	}

	@RequestMapping("/add.do")
	public String add(Advertisement advertisement, ModelMap model, String sessAccount, int sessOrgId) {
		advertisement.setTime(new Date());
		advertisement.setOrgId(sessOrgId);
		advertisement.setUserAccount(sessAccount);
		advertisement.setStatus(Status.DOING.getId());
		advertisementService.add(advertisement);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		Advertisement advertisement = advertisementService.get(id);
		model.put("advertisement", advertisement);
		return "advertisement/update";
	}

	@RequestMapping("/update.do")
	public String update(Advertisement advertisement, ModelMap model) {
		advertisement.setTime(new Date());
		advertisementService.update(advertisement);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping
	public String toApproval(int id, ModelMap model) {
		Advertisement advertisement = advertisementService.get(id);
		model.put("advertisement", advertisement);
		model.put("statuses", Status.values());
		return "advertisement/approval";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		advertisementService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		advertisementService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model, AdvertisementSearchForm searchForm) {
		Advertisement advertisement = advertisementService.get(id);
		model.put("searchForm", searchForm);
		model.put("advertisement", advertisement);
		return "advertisement/show";
	}

	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Advertisement advertisement = advertisementService.get(id);
		model.put("message", new JsonResult(advertisement).toJson());
		return "message";
	}

}
