package com.zhxjz.framework.util.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.groovy.control.CompilationFailedException;

/**
 * Groovy工具类
 * 
 * @author caozj
 * 
 */
public class GroovyUtil {

	/**
	 * 执行Groovy脚本(使用GroovyShell方式执行)
	 * 
	 * @param script
	 *            脚本内容
	 * @param param
	 *            参数
	 * @return
	 */
	public static Object executeScript(String script, Map<String, Object> param) {
		String key = String.valueOf(script.hashCode());
		Script scriptObject = getScriptFromCache(key);
		Binding binding = convertParam(param);
		if (scriptObject == null) {
			GroovyShell shell = new GroovyShell(binding);
			scriptObject = shell.parse(script);
			GroovyCache.getInstance().putScript(key, scriptObject);
		}
		scriptObject.setBinding(binding);
		return scriptObject.run();
	}

	/**
	 * 执行Groovy脚本(使用GroovyShell方式执行)
	 * 
	 * @param script
	 *            脚本内容
	 * @return
	 */
	public static Object executeScript(String script) {
		return GroovyUtil.executeScript(script, null);
	}

	/**
	 * 执行Groovy脚本文件(使用GroovyShell方式执行)
	 * 
	 * @param scriptFile
	 * @param param
	 * @return
	 * @throws CompilationFailedException
	 * @throws IOException
	 */
	public static Object executeFile(File scriptFile, Map<String, Object> param) throws CompilationFailedException,
			IOException {
		String key = String.valueOf(scriptFile.hashCode());
		Script scriptObject = getScriptFromCache(key);
		Binding binding = convertParam(param);
		if (scriptObject == null) {
			GroovyShell shell = new GroovyShell(binding);
			scriptObject = shell.parse(scriptFile);
			GroovyCache.getInstance().putScript(key, scriptObject);
		}
		scriptObject.setBinding(binding);
		return scriptObject.run();
	}

	/**
	 * 执行Groovy脚本文件(使用GroovyShell方式执行)
	 * 
	 * @param scriptFile
	 * @return
	 * @throws CompilationFailedException
	 * @throws IOException
	 */
	public static Object executeFile(File scriptFile) throws CompilationFailedException, IOException {
		return GroovyUtil.executeFile(scriptFile, null);
	}

	private static GroovyScriptEngine instance;

	private static GroovyScriptEngine getGroovyScriptEngine() throws IOException {
		if (instance == null) {
			instance = new GroovyScriptEngine("");
		}
		return instance;
	}

	/**
	 * 执行Groovy脚本文件(使用GroovyScriptEngine方式执行)
	 * 
	 * @param scriptFile
	 *            脚本文件地址(classpath的相对路径)
	 * @param param
	 *            参数
	 * @return
	 * @throws ResourceException
	 * @throws ScriptException
	 * @throws IOException
	 */
	public static Object executeFile(String scriptFile, Map<String, Object> param) throws ResourceException,
			ScriptException, IOException {
		return getGroovyScriptEngine().run(scriptFile, convertParam(param));
	}

	/**
	 * 执行Groovy脚本文件(使用GroovyScriptEngine方式执行)
	 * 
	 * @param scriptFile
	 *            脚本文件地址(classpath的相对路径)
	 * @return
	 * @throws IOException
	 * @throw ScriptException
	 * @throws ResourceExceptio
	 */
	public static Object executeFile(String scriptFile) throws ResourceException, ScriptException, IOException {
		return GroovyUtil.executeFile(scriptFile, null);
	}

	/**
	 * 执行Groovy类的方法
	 * 
	 * @param groovyFile
	 *            Groovy类
	 * @param method
	 *            方法名
	 * @param args
	 *            参数列表
	 * @return
	 * @throws CompilationFailedException
	 * @throws IOException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object executeMethod(File groovyFile, String method, Object... args)
			throws CompilationFailedException, IOException, InstantiationException, IllegalAccessException {
		GroovyClassLoader groovyClassLoader = new GroovyClassLoader(GroovyUtil.class.getClassLoader());
		String key = String.valueOf(groovyFile.getPath().hashCode());
		// 先从缓存里面取Class文件
		GroovyObject go = getGroovyObjectFromCache(key);
		if (go == null) {
			Class<?> groovyClass = groovyClassLoader.parseClass(groovyFile);
			GroovyCache.getInstance().putClass(key, groovyClass);
			go = (GroovyObject) groovyClass.newInstance();
		}
		return go.invokeMethod(method, args);
	}

	/**
	 * 执行Groovy类的方法
	 * 
	 * @param groovyClassText
	 *            Groovy类文件内容
	 * @param method
	 *            方法名
	 * @param args
	 *            参数列表
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static Object executeMethod(String groovyClassText, String method, Object... args)
			throws InstantiationException, IllegalAccessException {
		GroovyClassLoader groovyClassLoader = new GroovyClassLoader(GroovyUtil.class.getClassLoader());
		String key = String.valueOf(groovyClassText.hashCode());
		// 先从缓存里面取Class文件
		GroovyObject go = getGroovyObjectFromCache(key);
		if (go == null) {
			Class<?> groovyClass = groovyClassLoader.parseClass(groovyClassText);
			GroovyCache.getInstance().putClass(key, groovyClass);
			go = (GroovyObject) groovyClass.newInstance();
		}
		return go.invokeMethod(method, args);
	}

	/**
	 * 将Map转换成groovy执行时的参数对象
	 * 
	 * @param param
	 * @return
	 */
	private static Binding convertParam(Map<String, Object> param) {
		Binding bind = new Binding();
		if (param != null && param.size() > 0) {
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				bind.setVariable(entry.getKey(), entry.getValue());
			}
		}
		return bind;
	}

	/**
	 * 从缓存里取出脚本对象
	 * 
	 * @param key
	 * @return
	 */
	private static Script getScriptFromCache(String key) {
		Script script = null;
		if (GroovyCache.getInstance().containsScriptKey(key)) {
			script = GroovyCache.getInstance().getScriptByKey(key);
		}
		return script;
	}

	/**
	 * 从缓存里取出Groovy对象
	 * 
	 * @param key
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static GroovyObject getGroovyObjectFromCache(String key) throws InstantiationException,
			IllegalAccessException {
		GroovyObject go = null;
		if (GroovyCache.getInstance().containsClassKey(key)) {
			go = (GroovyObject) (GroovyCache.getInstance().getClassByKey(key).newInstance());
		}
		return go;
	}
}
