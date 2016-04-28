package com.zhxjz.test;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.annotation.TimeAnnotation;

@Controller
@RequestMapping("/test")
public class TestController {

	@TimeAnnotation(overtime = 30)
	@RequestMapping("testChinese.do")
	public String testChinese(String info, ModelMap model) {
		System.out.println("收到信息:" + info);
		model.put("message", info);
		return "message";
	}

	@TimeAnnotation
	@RequestMapping
	public String testTaglib(String info, ModelMap model) {
		model.put("message", info);
		return "test/taglib";
	}

	@TimeAnnotation(overtime = 70)
	@PostConstruct
	public String init() {
		return "1";
	}
}
