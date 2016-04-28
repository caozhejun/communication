package com.zhxjz.framework.util.jdbc;

/**
 * 分页对象
 * 
 * @author caozj
 * 
 */
public class Pager {

	/** 默认每页显示对象个数. */
	private final int DEFAULT_PAGE_SIZE = 10;

	/** 默认翻页起点. */
	private final int DEFAULT_PAGE_START = 0;

	/** 开始条数. */
	protected int pageStart = DEFAULT_PAGE_START;

	/** 每页显示对象个数. */
	protected int pageSize = DEFAULT_PAGE_SIZE;

	/** 对象总数量. */
	private int totalCount = 0;

	/**
	 * 构造函数.
	 * 
	 * @param pageStart
	 *            ---------------设置开始条数.
	 * @param pageSize
	 *            ---------------每页显示对象个数.
	 */
	public Pager(int pageStart, int pageSize) {
		this.setPageStart(pageStart);
		this.setPageSize(pageSize);
	}

	/**
	 * 构造函数.
	 * 
	 * @param pageStart
	 *            ---------------设置开始条数.
	 */
	public Pager(int pageStart) {
		this.setPageStart(pageStart);
	}

	/**
	 * 构造函数.
	 */
	public Pager() {
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize
	 *            the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		if (pageStart > 0) {
			this.pageStart = pageStart;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
		}
	}
}
