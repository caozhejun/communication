package com.zhxjz.controller.uEditor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadBase.InvalidContentTypeException;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zhxjz.framework.util.upload.UploadUtil;

/**
 * UEditor文件上传辅助工具类
 * 
 */
public class Uploader {

	private static final Log logger = LogFactory.getLog(Uploader.class);

	// 保存的文件目录
	public static final String FILEDIR = "uEditor";

	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 状态
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private String size = "";

	private HttpServletRequest request = null;
	private String title = "";

	// 文件允许格式
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv", ".gif", ".png",
			".jpg", ".jpeg", ".bmp" };
	// 文件大小限制，单位KB
	private int maxSize = 10000;

	private HashMap<String, String> errorInfo = new HashMap<String, String>();

	public Uploader(HttpServletRequest request) {
		this.request = request;
		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); // 默认成功
		tmp.put("NOFILE", "未包含文件上传域");
		tmp.put("TYPE", "不允许的文件格式");
		tmp.put("SIZE", "文件大小超出限制");
		tmp.put("ENTYPE", "请求类型ENTYPE错误");
		tmp.put("REQUEST", "上传请求异常");
		tmp.put("IO", "IO异常");
		tmp.put("DIR", "目录创建失败");
		tmp.put("UNKNOWN", "未知错误");
	}

	public void uploadFile() throws Exception {
		if (this.request instanceof MultipartHttpServletRequest) {
			uploadMultiFile();
		} else if (this.request instanceof HttpServletRequest) {
			uploadNormalFile();
		}
	}

	private void uploadMultiFile() throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
		if (!isMultipart) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		CommonsMultipartFile file = (CommonsMultipartFile) ((MultipartHttpServletRequest) request).getFile("upfile");
		if (file == null) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		this.originalName = file.getOriginalFilename();
		if (StringUtils.isEmpty(this.originalName)) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		if (!this.checkFileType(this.originalName)) {
			this.state = this.errorInfo.get("TYPE");
			return;
		}
		if (file.getSize() > maxSize * 1024) {
			this.state = this.errorInfo.get("SIZE");
			return;
		}
		this.fileName = this.getName(this.originalName);
		this.type = this.getFileExt(this.fileName);
		this.url = request.getContextPath() + UploadUtil.upload(file.getBytes(), file.getOriginalFilename());
		this.title = request.getParameter("pictitle");
		this.state = this.errorInfo.get("SUCCESS");
	}

	private void uploadNormalFile() throws Exception {
		boolean isMultipart = ServletFileUpload.isMultipartContent(this.request);
		if (!isMultipart) {
			this.state = this.errorInfo.get("NOFILE");
			return;
		}
		DiskFileItemFactory dff = new DiskFileItemFactory();
		BufferedReader reader = null;
		try {
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setSizeMax(this.maxSize * 1024);
			sfu.setHeaderEncoding("utf-8");
			FileItemIterator fii = sfu.getItemIterator(this.request);
			while (fii.hasNext()) {
				FileItemStream fis = fii.next();
				if (!fis.isFormField()) {
					this.originalName = fis.getName().substring(
							fis.getName().lastIndexOf(System.getProperty("file.separator")) + 1);
					if (!this.checkFileType(this.originalName)) {
						this.state = this.errorInfo.get("TYPE");
						continue;
					}
					this.fileName = this.getName(this.originalName);
					this.type = this.getFileExt(this.fileName);
					this.url = request.getContextPath()
							+ UploadUtil.upload(StreamUtils.copyToByteArray(fis.openStream()), this.originalName);
					this.state = this.errorInfo.get("SUCCESS");
					break;
				} else {
					String fname = fis.getFieldName();
					// 只处理title，其余表单请自行处理
					if (!fname.equals("pictitle")) {
						continue;
					}
					reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(fis.openStream())));
					StringBuffer result = new StringBuffer();
					while (reader.ready()) {
						result.append((char) reader.read());
					}
					this.title = new String(result.toString().getBytes(), "utf-8");
				}
			}
		} catch (SizeLimitExceededException e) {
			logger.error(e);
			this.state = this.errorInfo.get("SIZE");
		} catch (InvalidContentTypeException e) {
			logger.error(e);
			this.state = this.errorInfo.get("ENTYPE");
		} catch (FileUploadException e) {
			logger.error(e);
			this.state = this.errorInfo.get("REQUEST");
		} catch (Exception e) {
			logger.error(e);
			this.state = this.errorInfo.get("UNKNOWN");
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		for (String allowFile : allowFiles) {
			if (fileName.toLowerCase().endsWith(allowFile)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展�?
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 依据原始文件名生成新文件名
	 * 
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = "" + random.nextInt(10000) + System.currentTimeMillis() + this.getFileExt(fileName);
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(int size) {
		this.maxSize = size;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getState() {
		return this.state;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}

}
