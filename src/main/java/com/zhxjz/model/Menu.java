package com.zhxjz.model;

import com.zhxjz.framework.model.ext.ExtTreeNode;

/**
 * 菜单
 * 
 * @author caozj
 * 
 */
public class Menu extends ExtTreeNode {

	/**
	 * 父菜单id
	 */
	private int parentID;

	/**
	 * 菜单url地址
	 */
	private String url;

	/**
	 * 排序
	 */
	private int orderNo;

	// ///扩展属性////
	/**
	 * 树节点状态,easyUI使用
	 */
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
