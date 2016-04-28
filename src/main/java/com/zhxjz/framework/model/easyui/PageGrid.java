package com.zhxjz.framework.model.easyui;

import java.util.List;

/**
 * easyui的分页对象
 * 
 * @author caozj
 * 
 */
public class PageGrid {

	public PageGrid() {
	}

	public PageGrid(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	private int total;

	private List<?> rows;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
