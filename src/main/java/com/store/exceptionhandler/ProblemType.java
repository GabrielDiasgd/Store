package com.store.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	RESOURCE_NOT_FOUND("/resource-not-found","Resource not Found"),
	BUSINESS_ERROR("/business_error", "Business rule violation"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	UNREADABLE_REQUEST_BODY("/unreadable-request-body", "Unreadable request body"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter in URL"),
	SYSTEM_ERROR("/system-error", "Internal Sever Error"),
	INVALID_DATA("/invalid-data", "Invalid data");
	
	
	private String uri;
	private String title;
	
	 ProblemType (String path, String title) {
		this.uri = "Http//localhost:8080" + path;
		this.title = title;
	}

}
