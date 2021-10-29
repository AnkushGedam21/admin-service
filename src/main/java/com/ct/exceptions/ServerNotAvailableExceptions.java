package com.ct.exceptions;

import org.springframework.web.client.RestClientException;

/**
 * @Ankush Gedam
 */
public class ServerNotAvailableExceptions extends RestClientException {
	
	private static final long serialVersionUID = 1L;
	private final static String server_message="SERVER NOT AVALABLE";
	
	public ServerNotAvailableExceptions() {
		super(server_message);
	}

	public ServerNotAvailableExceptions(String message) {
		super(message);
	}

	
}
