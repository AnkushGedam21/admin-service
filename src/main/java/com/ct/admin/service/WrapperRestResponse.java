package com.ct.admin.service;

public class WrapperRestResponse <T> {
private PaginatedResponse<T> page;
public WrapperRestResponse() {
}
public WrapperRestResponse(PaginatedResponse<T> page) {
	this.page = page;
}
public PaginatedResponse<T> getPage() {
	return page;
}
public void setPage(PaginatedResponse<T> page) {
	this.page = page;
}



}
