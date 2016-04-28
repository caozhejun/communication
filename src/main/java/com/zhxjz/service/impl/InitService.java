package com.zhxjz.service.impl;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.zhxjz.annotation.TimeAnnotation;
import com.zhxjz.framework.util.upload.UploadUtil;

/**
 * 初始化类
 * 
 * @author caozj
 * 
 */
@Service
public class InitService {

	@PostConstruct
	@TimeAnnotation
	private void init() {
		UploadUtil.init();
	}
}
