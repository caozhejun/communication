package com.zhxjz.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zhxjz.framework.util.zip.ZipUtil;

import net.lingala.zip4j.exception.ZipException;

public class TestZip {

	public static void main(String[] args) throws ZipException {
		List<File> srcList = new ArrayList<File>();
		srcList.add(new File("e://曹哲军.doc"));
		srcList.add(new File("e://快乐虎.txt"));
		srcList.add(new File("e://照片"));
		srcList.add(new File("e://普通文件"));
		ZipUtil.zip(srcList, "e://asd.zip");
	}
}
