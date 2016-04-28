package com.zhxjz.service;

import java.util.List;

public interface CacheService {

	/**
	 * 获取缓存对象
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key);

	/**
	 * 设置缓存对象
	 * 
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value);

	/**
	 * 设置缓存对象，有过期时间
	 * 
	 * @param key
	 * @param value
	 * @param second
	 *            单位秒
	 */
	public void set(String key, Object value, int second);

	/**
	 * 设置缓存对象，使用hash结构
	 * 
	 * @param key
	 * @param field
	 * @param value
	 */
	public void hset(String key, String field, Object value);

	/**
	 * 获取缓存对象，使用hash结构
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public Object hget(String key, String field);

	/**
	 * 删除缓存对象
	 * 
	 * @param key
	 */
	public void remove(String key);

	/**
	 * 获取自增id，每次获取自动+1，key区分不同队列
	 * 
	 * @param key
	 * @return
	 */
	public long getAutoId(String key);

	/**
	 * list头部增加一个元素，使用list结构
	 * 
	 * @param key
	 * @param value
	 */
	public void lpush(String key, Object value);

	/**
	 * list尾部增加一个元素，使用list结构
	 * 
	 * @param key
	 * @param value
	 */
	public void rpush(String key, Object value);

	/**
	 * 获取list中全部数据
	 * 
	 * @param key
	 * @return
	 */
	public List<Object> lrange(String key);

	/**
	 * 从list头部获取数据并删除
	 * 
	 * @param key
	 * @return
	 */
	public Object lpop(String key);

	/**
	 * 从list尾部获取数据并删除
	 * 
	 * @param key
	 * @return
	 */
	public Object rpop(String key);

	/**
	 * 返回list中的index位置的元素
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public Object lindex(String key, int index);

	/**
	 * 获取list中元素个数
	 * 
	 * @param key
	 * @return
	 */
	public long llen(String key);

	/**
	 * 返回缓存中所有的key的列表
	 * 
	 * @return
	 */
	public List<String> keys();

}
