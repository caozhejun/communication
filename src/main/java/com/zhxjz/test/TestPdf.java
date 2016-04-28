package com.zhxjz.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zhxjz.framework.util.pdf.PdfUtil;

public class TestPdf {

	public static void main(String[] args) throws Exception {
		PdfUtil.writPdf(new File("D:/测试内容.pdf"), "Hello World ! 大帅哥 ！！！");
		List<List<String>> table = new ArrayList<List<String>>();
		List<String> row = new ArrayList<String>();
		row.add("标题1");
		row.add("任务2");
		table.add(row);
		List<String> row2 = new ArrayList<String>();
		row2.add("内容1");
		row2.add("内容2");
		table.add(row2);
		PdfUtil.exportTable(new File("D:/测试表格.pdf"), table);
	}
}
