package com.zhxjz.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import com.zhxjz.framework.util.office.ExcelUtilFor2007;
import com.zhxjz.model.Participation;
import com.zhxjz.service.ParticipationService;

@Controller
@RequestMapping("/participation")
public class ParticipationController {

	@Autowired
	private ParticipationService participationService;

	@RequestMapping("/list.do")
	public String list(ModelMap model, int activeId) {
		model.put("activeId", activeId);
		return "participation/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit, int activeId) {
		Pager page = new Pager(start, limit);
		List<Participation> list = participationService.page(page, activeId);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form, int activeId) {
		Pager page = new Pager((form.getPage() - 1) * form.getRows(), form.getRows());
		List<Participation> list = participationService.page(page, activeId);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "participation/add";
	}

	@RequestMapping("/add.do")
	public String add(Participation participation, ModelMap model, String sessAccount) {
		participation.setTime(new Date());
		participation.setUserAccount(sessAccount);
		participationService.add(participation);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		Participation participation = participationService.get(id);
		model.put("participation", participation);
		return "participation/update";
	}

	@RequestMapping("/update.do")
	public String update(Participation participation, ModelMap model) {
		participationService.update(participation);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		participationService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		participationService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model) {
		Participation participation = participationService.get(id);
		model.put("participation", participation);
		return "participation/show";
	}

	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Participation participation = participationService.get(id);
		model.put("message", new JsonResult(participation).toJson());
		return "message";
	}

	@RequestMapping("/exportParticipation.do")
	public void exportParticipation(int activeId, HttpServletResponse response) throws FileNotFoundException, IOException {
		List<Participation> list = participationService.list(activeId);
		List<List<String>> rows = new ArrayList<List<String>>(list.size() + 1);
		List<String> header = buildHeader();
		rows.add(header);
		for (Participation p : list) {
			List<String> content = buildBody(p);
			rows.add(content);
		}
		OutputStream out = response.getOutputStream();
		response.setContentType("application/msexcel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + new String("活动报名详情.xlsx".getBytes(), "ISO8859-1") + "\"");
		ExcelUtilFor2007.write(out, "活动报名详情", rows);
		IOUtils.closeQuietly(out);
	}

	private List<String> buildBody(Participation p) {
		List<String> content = new ArrayList<String>(10);
		content.add(p.getUserAccount());
		content.add(p.getName());
		content.add(p.getEmail());
		content.add(p.getTelNo());
		content.add(p.getFloorNo());
		content.add(p.getUnitNo());
		content.add(p.getRoomNo());
		content.add(p.getIdNo());
		content.add(p.getNum() + "");
		content.add(p.getRemark());
		return content;
	}

	private List<String> buildHeader() {
		List<String> header = new ArrayList<String>(10);
		header.add("账号");
		header.add("名称");
		header.add("电子 邮箱");
		header.add("电话号码");
		header.add("楼号");
		header.add("单元号");
		header.add("房号");
		header.add("身份证号");
		header.add("人数");
		header.add("备注");
		return header;
	}

}
