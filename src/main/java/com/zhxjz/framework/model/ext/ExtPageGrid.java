package com.zhxjz.framework.model.ext;

import java.util.List;

/**
 * Ext分页Grid对象(保存到JsonResult的data后，toJson返回给前台就能显示分页Grid)
 * 
 * @author caozj
 * 
 */
public class ExtPageGrid {

	public ExtPageGrid() {

	}

	public ExtPageGrid(List<?> list, int count) {
		this.list = list;
		this.count = count;
	}

	/**
	 * 显示在表格中的数据
	 */
	private List<?> list;

	/**
	 * 总记录总
	 */
	private int count;

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExtPageGrid [list=");
		builder.append(list);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}
