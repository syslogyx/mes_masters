package com.syslogyx.bo;

/**
 * Pagination Class to Set the Details related to pagination list
 * 
 * @author namrata
 *
 */
public class Pagination {

	private int page_count;
	private int current_page;
	private boolean has_next_page;
	private boolean has_prev_page;
	private long count;
	private int limit;

	public Pagination(int page_count, int current_page, boolean has_next_page, boolean has_prev_page, long count,
			int limit) {
		super();
		this.page_count = page_count;
		this.current_page = current_page;
		this.has_next_page = has_next_page;
		this.has_prev_page = has_prev_page;
		this.count = count;
		this.limit = limit;
	}

	public int getPage_count() {
		return page_count;
	}

	public int getCurrent_page() {
		return current_page;
	}

	public boolean isHas_next_page() {
		return has_next_page;
	}

	public boolean isHas_prev_page() {
		return has_prev_page;
	}

	public long getCount() {
		return count;
	}

	public int getLimit() {
		return limit;
	}

}
