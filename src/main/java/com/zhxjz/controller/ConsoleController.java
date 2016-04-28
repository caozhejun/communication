package com.zhxjz.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.controller.form.GroovyExecuteForm;
import com.zhxjz.exception.ForbidException;
import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.util.common.EncryptUtil;
import com.zhxjz.framework.util.groovy.GroovyUtil;

/**
 * Groovy控制台Controller
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/console")
public class ConsoleController {

	@Value("${groovy.console.enable}")
	private boolean consoleEnable = false;

	@Value("${groovy.console.username}")
	private String userName;

	@Value("${groovy.console.password}")
	private String password;

	/**
	 * 进入控制台页面
	 * 
	 * @return
	 */
	@RequestMapping
	public String show() {
		if (!consoleEnable) {
			throw new ForbidException("控制台不能使用");
		}
		return "console/show";
	}

	/**
	 * 执行Groovy脚本返回结果
	 * 
	 * @param executeForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/execute.do")
	public String execute(GroovyExecuteForm executeForm, ModelMap model) {
		validatorExecute(executeForm);
		Object result = GroovyUtil.executeScript(executeForm.getScript());
		model.put("message", new JsonResult(result.toString()).toJson());
		return "message";
	}

	/**
	 * 验证账号密码是否正确，以及脚本不能为空
	 * 
	 * @param executeForm
	 */
	private void validatorExecute(GroovyExecuteForm executeForm) {
		if (StringUtils.isBlank(executeForm.getUserName())) {
			throw new ForbidException("用户名不能为空");
		}
		if (StringUtils.isBlank(executeForm.getPassword())) {
			throw new ForbidException("密码不能为空");
		}
		if (StringUtils.isBlank(executeForm.getScript())) {
			throw new ForbidException("脚本不能为空");
		}
		if (!userName.equals(EncryptUtil.md5(executeForm.getUserName()))) {
			throw new ForbidException("用户名错误");
		}
		if (!password.equals(EncryptUtil.md5(executeForm.getPassword()))) {
			throw new ForbidException("密码错误");
		}
	}

}
