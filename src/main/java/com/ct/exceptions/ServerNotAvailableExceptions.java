package com.ct.exceptions;

import org.springframework.web.client.RestClientException;

/**
 * @Ankush Gedam
 */
public class ServerNotAvailableExceptions extends RestClientException {
	
	private static final long serialVersionUID = 1L;
	private final static String SERVER_NOT_AVAILABLE="SERVER NOT AVALABLE";
	
	public ServerNotAvailableExceptions() {
		super(SERVER_NOT_AVAILABLE);
	}

	public ServerNotAvailableExceptions(String message) {
		super(message);
	}

	
}
