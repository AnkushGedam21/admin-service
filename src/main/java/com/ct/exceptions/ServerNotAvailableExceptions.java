package com.ct.exceptions;

public class ServerNotAvailableExceptions extends IllegalStateException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static String server_message="SERVER NOT AVALABLE";
	
	public ServerNotAvailableExceptions() {
		super(server_message);
	}

	public ServerNotAvailableExceptions(String message) {
		super(message);
	}

	
}
