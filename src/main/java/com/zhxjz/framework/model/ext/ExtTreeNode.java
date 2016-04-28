package com.zhxjz.framework.model.ext;

/**
 * ExtJs对应树节点对象
 * 
 * @author caozj
 * 
 */
public class ExtTreeNode {

	/**
	 * 节点唯一 id
	 */
	private int id;

	/**
	 * 显示节点名称
	 */
	private String text;

	/**
	 * 是否叶子节点
	 */
	private boolean leaf;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

}
