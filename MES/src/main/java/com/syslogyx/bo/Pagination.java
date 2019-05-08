package com.syslogyx.bo;

/**
 * Pagination Class to Set the Details related to pagination list
 * 
 * @author namrata
 *
 */
public class Pagination {

	private long count;
	private int page;
	private int limit;

	public Pagination(long count, int page, int limit) {
		this.count = count;
		this.page = page;
		this.limit = limit;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
