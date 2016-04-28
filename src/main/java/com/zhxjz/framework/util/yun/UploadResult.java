package com.zhxjz.framework.util.yun;

/**
 * 文件上传结果信息
 * 
 * @author caozj
 *
 */
public class UploadResult {

	/**
	 * 该文件的绝对路径
	 */
	private String path;

	/**
	 * 文件字节大小
	 */
	private long size;

	/**
	 * 文件创建时间
	 */
	private long ctime;

	/**
	 * 文件修改时间
	 */
	private long mtime;

	/**
	 * 文件的md5签名
	 */
	private String md5;

	/**
	 * 文件在PCS的临时唯一标识ID
	 */
	private long fs_id;

	private long request_id;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public long getMtime() {
		return mtime;
	}

	public void setMtime(long mtime) {
		this.mtime = mtime;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getFs_id() {
		return fs_id;
	}

	public void setFs_id(long fs_id) {
		this.fs_id = fs_id;
	}

	public long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(long request_id) {
		this.request_id = request_id;
	}

}
