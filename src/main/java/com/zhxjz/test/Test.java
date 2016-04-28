package com.zhxjz.test;

import java.io.IOException;

import com.zhxjz.framework.util.ftp.FtpClientUtil;
import com.zhxjz.framework.util.ftp.FtpServerUtil;

public class Test {

	public static void main(String[] args) throws IOException {
		FtpServerUtil.start();

		System.out.println("启动完成ftp,开始连接");
		FtpClientUtil client = new FtpClientUtil("127.0.0.1", 21, "admin", "admin");
		client.uploadFile("E:/DTS-ESB-V103-005企业信息服务总线标准版本一期性能测试报告.doc", "/测试.doc");
		client.uploadFile("E:/曹哲军.doc", "/c.doc");
		client.uploadFile("E:/测试.txt", "/测试.txt");
		client.uploadFile("e:/rtxcsetup.exe", "/r.exe");
		client.uploadFile("E:\\珠海局\\接口交互说明.docx", "/j.docx");
		client.uploadFile("E:\\组合测试方案和成绩书.rar", "/j.rar");
		client.uploadFile("E:\\珠海局\\Fw_soa申请模板（湛江模板）.zip", "/f.zip");
		client.uploadFile("E:\\苏丽燕个人文件\\照片\\齐奥岛(义工朋友)\\照片 085.jpg", "/8.jpg");
		client.uploadFile("E:\\zookeeper-3.4.6\\ivy.xml", "/i.xml");
		client.uploadFile("E:\\珠海局\\Fw_soa申请模板（湛江模板）\\广东电网公司省级集中计量自动化系统与中调系统接口需求设计文档.xlsx", "/档.xlsx");
		
		
		client.download("/测试.doc", "d:/DTS-ESB-V103-005企业信息服务总线标准版本一期性能测试报告.doc");
		client.download("/c.doc", "d:/曹哲军.doc");
		client.download( "/测试.txt","d:/测试.txt");
		client.download( "/r.exe","d:/rtxcsetup.exe");
		client.download("/j.docx", "d:\\接口交互说明.docx");
		client.download("/j.rar", "d:\\组合测试方案和成绩书.rar");
		client.download("/f.zip", "d:\\Fw_soa申请模板（湛江模板）.zip");
		client.download("/8.jpg", "d:\\照片 085.jpg");
		client.download( "/i.xml","d:\\ivy.xml");
		client.download("/档.xlsx", "d:\\广东电网公司省级集中计量自动化系统与中调系统接口需求设计文档.xlsx");

		client.closeServer();
	}
}
