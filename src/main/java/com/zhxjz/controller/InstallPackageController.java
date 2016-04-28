package com.zhxjz.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.zhxjz.framework.model.json.JsonResult;
import com.zhxjz.framework.util.common.JsonUtil;
import com.zhxjz.framework.util.common.ServerUtil;
import com.zhxjz.model.InstallPackage;
import com.zhxjz.service.InstallPackageService;

/**
 * 安装包管理Controller
 * 
 * @author caozj
 * 
 */
@Controller
@RequestMapping("/installPackage")
public class InstallPackageController {

	@Autowired
	private InstallPackageService installPackageService;

	@Value("${install.package.upload.dir}")
	private String installDir;

	@RequestMapping("/list.do")
	public String list() {
		return "installPackage/list";
	}

	@RequestMapping("/listData.do")
	public String listData(ModelMap model) {
		List<InstallPackage> allList = installPackageService.listAll();
		model.put("message", JsonUtil.toJson(allList));
		return "message";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd() {
		return "installPackage/add";
	}

	@RequestMapping("/add.do")
	public String add(InstallPackage installPackage, MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		installPackage.setFileName(fileName);
		String fileFullPath = getFullPath(installPackage);
		FileUtils.writeByteArrayToFile(new File(fileFullPath), file.getBytes());
		installPackageService.add(installPackage);
		return "redirect:/installPackage/list.do";
	}

	@RequestMapping("/toUpdate.do")
	public String toUpdate(String installVersion, ModelMap model) {
		InstallPackage installPackage = installPackageService.get(installVersion);
		model.put("installPackage", installPackage);
		return "installPackage/update";
	}

	@RequestMapping("/update.do")
	public String update(InstallPackage installPackage, ModelMap model) {
		InstallPackage oldInstallPackage = installPackageService.get(installPackage.getInstallVersion());
		oldInstallPackage.setDetail(installPackage.getDetail());
		installPackageService.update(oldInstallPackage);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/batchDelete.do")
	public String batchDelete(String[] versions, ModelMap model) {
		installPackageService.batchDelete(Arrays.asList(versions));
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/updateNewest.do")
	public String updateNewest(String version, ModelMap model) {
		installPackageService.updateNewest(version);
		model.put("message", JsonUtil.successJson());
		return "message";
	}

	@RequestMapping("/getNewest.do")
	public String getNewest(String version, ModelMap model) {
		InstallPackage newestInstallPackage = installPackageService.getNewest();
		String message = null;
		if (newestInstallPackage == null || newestInstallPackage.getInstallVersion().equals(version)) {
			message = JsonUtil.successJson();
		} else {
			newestInstallPackage.setFileName("/" + installDir + "/" + newestInstallPackage.getInstallVersion() + "/" + newestInstallPackage.getFileName());
			message = JsonUtil.toJson(new JsonResult(newestInstallPackage));
		}
		model.put("message", message);
		return "message";
	}

	private String getFullPath(InstallPackage installPackage) {
		String path = ServerUtil.getWebRoot() + File.separator + installDir + File.separator + installPackage.getInstallVersion();
		new File(path).mkdirs();
		return path + File.separator + installPackage.getFileName();
	}
}
