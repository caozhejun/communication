package com.zhxjz.model;

/**
 * 安装包
 * 
 * @author caozj
 * 
 */
public class InstallPackage {

	/**
	 * 版本号
	 */
	private String installVersion;

	/**
	 * 版本升级细节
	 */
	private String detail;

	/**
	 * 安装包文件名
	 */
	private String fileName;

	/**
	 * 是否最新版本 1:是,0:否
	 */
	private int newest;

	/**
	 * 发布时间
	 */
	private String publishDate;

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getInstallVersion() {
		return installVersion;
	}

	public void setInstallVersion(String installVersion) {
		this.installVersion = installVersion;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getNewest() {
		return newest;
	}

	public void setNewest(int newest) {
		this.newest = newest;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InstallPackage [installVersion=");
		builder.append(installVersion);
		builder.append(", detail=");
		builder.append(detail);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", newest=");
		builder.append(newest);
		builder.append(", publishDate=");
		builder.append(publishDate);
		builder.append("]");
		return builder.toString();
	}

}
