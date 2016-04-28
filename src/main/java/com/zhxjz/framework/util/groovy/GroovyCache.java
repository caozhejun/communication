package com.zhxjz.framework.util.groovy;

import groovy.lang.Script;

import java.util.HashMap;
import java.util.Map;

/**
 * Groovy 脚本 类 缓存
 * 
 * @author caozj
 * 
 */
public class GroovyCache {
	private static final Map<String, Class<?>> GROOVYCLASSCACHE = new HashMap<String, Class<?>>();
	private static final Map<String, Script> GROOVYSCRIPTCACHE = new HashMap<String, Script>();

	private GroovyCache() {
	}

	private static class GroovyCacheHolder {
		private static final GroovyCache instance = new GroovyCache();
	}

	public static GroovyCache getInstance() {
		return GroovyCacheHolder.instance;
	}

	public Class<?> getClassByKey(String key) {
		return GROOVYCLASSCACHE.get(key);
	}

	public void putClass(String key, Class<?> clazz) {
		GROOVYCLASSCACHE.put(key, clazz);
	}

	public boolean containsClassKey(String key) {
		return GROOVYCLASSCACHE.containsKey(key);
	}

	public Script getScriptByKey(String key) {
		return GROOVYSCRIPTCACHE.get(key);
	}

	public void putScript(String key, Script script) {
		GROOVYSCRIPTCACHE.put(key, script);
	}

	public boolean containsScriptKey(String key) {
		return GROOVYSCRIPTCACHE.containsKey(key);
	}
}
