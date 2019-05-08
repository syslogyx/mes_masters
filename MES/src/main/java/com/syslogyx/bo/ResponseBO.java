package com.syslogyx.bo;

import java.util.List;

/**
 * ResponseBO class is defined to prepare the Response for all lists with the
 * pagination object and all required data
 * 
 * @author namrata
 *
 */
public class ResponseBO {

	private Pagination pagination;
	private List list;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
