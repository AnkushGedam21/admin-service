package com.ct.exceptions;

public class ContentNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ContentNotFound() {
		 super();
	 }
	public ContentNotFound(String message) {
		 super(message);
	 }

}
