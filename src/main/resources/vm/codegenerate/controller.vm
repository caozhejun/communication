package ${packageName}.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ${packageName}.controller.form.EasyUIPageForm;
import ${packageName}.framework.model.easyui.PageGrid;
import ${packageName}.framework.model.ext.ExtPageGrid;
import ${packageName}.framework.model.json.JsonResult;
import ${packageName}.framework.util.common.JsonUtil;
import ${packageName}.framework.util.jdbc.Pager;
import ${packageName}.model.${className};
import ${packageName}.service.${className}Service;

@Controller
@RequestMapping("/${firstLower}")
public class ${className}Controller {

	@Autowired
	private ${className}Service ${firstLower}Service;

	@RequestMapping("/list.do")
	public String list(ModelMap model) {
		return "${firstLower}/list";
	}

	@RequestMapping("/listDataOfExt.do")
	public String listDataOfExt(ModelMap model, int start, int limit) {
		Pager page = new Pager(start, limit);
		List<${className}> list = ${firstLower}Service.page(page);
		String message = new JsonResult(new ExtPageGrid(list, page.getTotalCount())).toJson();
		model.put("message", message);
		return "message";
	}
	
	@RequestMapping("/listDataOfEasyUI.do")
	public String listDataOfEasyUI(ModelMap model, EasyUIPageForm form) {
		Pager page = new Pager((form.getPage()-1) * form.getRows(), form.getRows());
		List<${className}> list = ${firstLower}Service.page(page);
		String message = JsonUtil.toJson(new PageGrid(page.getTotalCount(), list));
		model.put("message", message);
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "${firstLower}/add";
	}

	@RequestMapping("/add.do")
	public String add(${className} ${firstLower}, ModelMap model) {
		${firstLower}Service.add(${firstLower});
		model.put("message", new JsonResult().toJson());
		return "message";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(int id, ModelMap model) {
		${className} ${firstLower} = ${firstLower}Service.get(id);
		model.put("${firstLower}", ${firstLower});
		return "${firstLower}/update";
	}

	@RequestMapping("/update.do")
	public String update(${className} ${firstLower}, ModelMap model) {
		${firstLower}Service.update(${firstLower});
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(Integer[] ids, ModelMap model) {
		List<Integer> idList = Arrays.asList(ids);
		${firstLower}Service.batchDelete(idList);
		model.put("message", new JsonResult().toJson());
		return "message";
	}
	
	@RequestMapping("/delete.do")
	public String delete(int id, ModelMap model) {
		${firstLower}Service.delete(id);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/show.do")
	public String show(int id, ModelMap model) {
		${className} ${firstLower} = ${firstLower}Service.get(id);
		model.put("${firstLower}", ${firstLower});
		return "${firstLower}/show";
	}
	
	@RequestMapping("/get.do")
	public String get(int id, ModelMap model) {
		${className} ${firstLower} = ${firstLower}Service.get(id);
		model.put("message", new JsonResult(${firstLower}).toJson());
		return "message";
	}

}
