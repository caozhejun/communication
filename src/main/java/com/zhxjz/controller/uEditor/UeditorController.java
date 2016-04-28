package com.zhxjz.controller.uEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhxjz.framework.util.upload.UploadUtil;

/**
 * 处理html编辑器的Controller
 * 
 * @author caozj
 * @date 2013-04-11
 * 
 */
@Controller
@Scope(BeanDefinition.SCOPE_SINGLETON)
@RequestMapping("/uEditor")
public class UeditorController {

	private static final Log logger = LogFactory.getLog(UeditorController.class);

	// 允许上传的附件类型
	private static final String[] ATTACHTYPE = { ".rar", ".doc", ".docx", ".zip", ".pdf", ".txt", ".swf", ".wmv" };

	// 允许上传的图片类型
	private static final String[] IMGTYPE = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

	@RequestMapping("/show.do")
	public String show() {
		return "ueditor/show";
	}

	/**
	 * 远程抓取
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRemoteImage.do")
	public String getRemoteImage(HttpServletRequest request, ModelMap model) throws Exception {
		String url = request.getParameter("upfile");
		String state = "远程图片抓取成功";
		String[] arr = url.split("ue_separate_ue");
		String[] outSrc = new String[arr.length];
		for (int i = 0; i < arr.length; i++) {
			// 格式验证
			String type = getFileType(arr[i]);
			if (type.equals("")) {
				state = "图片类型不正确！";
				continue;
			}
			String saveName = Long.toString(new Date().getTime()) + type;
			// 大小验证
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection conn = (HttpURLConnection) new URL(arr[i]).openConnection();
			if (conn.getContentType().indexOf("image") == -1) {
				state = "请求地址头不正确";
				continue;
			}
			if (conn.getResponseCode() != 200) {
				state = "请求地址不存在！";
				continue;
			}
			InputStream is = null;
			try {
				is = conn.getInputStream();
				outSrc[i] = UploadUtil.upload(StreamUtils.copyToByteArray(is), saveName);
			} catch (Exception e) {
				logger.error(e);
			} finally {
				IOUtils.closeQuietly(is);
			}
		}
		String outstr = "";
		for (int i = 0; i < outSrc.length; i++) {
			outstr += outSrc[i] + "ue_separate_ue";
		}
		outstr = outstr.substring(0, outstr.lastIndexOf("ue_separate_ue"));
		String message = "{'url':'" + outstr + "','tip':'" + state + "','srcUrl':'" + url + "'}";
		model.addAttribute("message", message);
		return "message";
	}

	/**
	 * 获取视频数据的地址
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMovie.do")
	public String getMovie(HttpServletRequest request, ModelMap model) throws Exception {
		StringBuffer readOneLineBuff = new StringBuffer();
		String content = "";
		String searchkey = request.getParameter("searchKey");
		String videotype = request.getParameter("videoType");
		BufferedReader reader = null;
		try {
			searchkey = URLEncoder.encode(searchkey, "utf-8");
			URL url = new URL("http://api.tudou.com/v3/gw?method=item.search&appKey=myKey&format=json&kw=" + searchkey
					+ "&pageNo=1&pageSize=20&channelId=" + videotype + "&inDays=7&media=v&sort=s");
			URLConnection conn = url.openConnection();
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line = "";
			while ((line = reader.readLine()) != null) {
				readOneLineBuff.append(line);
			}
			content = readOneLineBuff.toString();
		} catch (MalformedURLException e) {
			logger.error(e);
		} catch (IOException e2) {
			logger.error(e2);
		} finally {
			IOUtils.closeQuietly(reader);
		}
		model.addAttribute("message", content);
		return "message";
	}

	/**
	 * 附件上传
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/fileUp.do")
	public String fileUp(HttpServletRequest request, ModelMap model) throws Exception {
		Uploader up = new Uploader(request);
		up.setAllowFiles(ATTACHTYPE);
		up.uploadFile();
		String message = "{'url':'" + up.getUrl() + "','fileType':'" + up.getType() + "','state':'" + up.getState()
				+ "','original':'" + up.getOriginalName() + "'}";
		model.addAttribute("message", message);
		return "message";
	}

	/**
	 * 涂鸦图片上传
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scrawlUp.do")
	public String scrawlUp(HttpServletRequest request, ModelMap model) throws Exception {
		String message = null;

		String param = request.getParameter("action");
		Uploader up = new Uploader(request);
		up.setAllowFiles(IMGTYPE);

		if (param != null && param.equals("tmpImg")) {
			up.uploadFile();
			message = "<script>parent.ue_callback('" + up.getUrl() + "','" + up.getState() + "')</script>";
		} else {
			message = "{'url':'" + up.getUrl() + "',state:'" + up.getState() + "'}";
		}
		model.addAttribute("message", message);
		return "message";
	}

	/**
	 * 图片上传 屏幕截图 word转存
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/imageUp.do")
	public String imageUp(HttpServletRequest request, ModelMap model) throws Exception {
		Uploader up = new Uploader(request);
		up.setAllowFiles(IMGTYPE);
		up.uploadFile();
		model.addAttribute("message", "{'original':'" + up.getOriginalName() + "','url':'" + up.getUrl()
				+ "','title':'" + up.getTitle() + "','state':'" + up.getState() + "'}");
		return "message";
	}

	private String getFileType(String fileName) {
		for (String type : IMGTYPE) {
			if (fileName.endsWith(type)) {
				return type;
			}
		}
		return "";
	}

}
