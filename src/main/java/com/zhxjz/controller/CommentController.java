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
import com.zhxjz.model.Comment;
import com.zhxjz.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "comment/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<Comment> list = commentService.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form) {
		Pager page = new Pager((form.getPage() - 1) * form.getRows(), form.getRows());
		List<Comment> list = commentService.page(page);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "comment/add";
	}

	@RequestMapping("/add.do")
	public String add(Comment comment, ModelMap model, String sessAccount, String sessName) {
		comment.setUserAccount(sessAccount);
		comment.setName(sessName);
		comment.setTime(new Date());
		commentService.add(comment);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		Comment comment = commentService.get(id);
		model.put("comment", comment);
		return "comment/update";
	}

	@RequestMapping("/update.do")
	public String update(Comment comment, ModelMap model) {
		commentService.update(comment);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		commentService.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		commentService.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model) {
		Comment comment = commentService.get(id);
		model.put("comment", comment);
		return "comment/show";
	}

	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		Comment comment = commentService.get(id);
		model.put("message", new JsonResult(comment).toJson());
		return "message";
	}

}
