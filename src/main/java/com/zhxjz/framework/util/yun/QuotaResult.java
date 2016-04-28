package com.zhxjz.framework.util.yun;

/**
 * 获取当前用户空间配额信息 返回信息
 * 
 * @author caozj
 *
 */
public class QuotaResult {

	/**
	 * 空间配额，单位为字节
	 */
	private long quota;

	/**
	 * 已使用空间大小，单位为字节
	 */
	private long used;

	private long request_id;

	public long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(long request_id) {
		this.request_id = request_id;
	}

	public long getQuota() {
		return quota;
	}

	public void setQuota(long quota) {
		this.quota = quota;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

}
