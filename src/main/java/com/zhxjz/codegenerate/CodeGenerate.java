package com.zhxjz.codegenerate;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zhxjz.model.Communication;

/**
 * 代码生成器入口
 * 
 * @author caozj
 *
 */
public class CodeGenerate {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		// 只需要将需要生成代码的class对象放入下面数组中，就可以自动生成代码
		Class<?>[] classes = new Class<?>[] { Communication.class };
		ApplicationContext context = new ClassPathXmlApplicationContext("/SpringContext.xml");
		CodeGenerateUtil util = (CodeGenerateUtil) context.getBean("codeGenerateUtil");
		util.codeGenerate(classes);
		System.exit(0);
	}

}
