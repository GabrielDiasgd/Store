package com.store.client.model;

public enum ClientStatus {
	
	RELEASED(0), BLOCKED(1);
	
	private Integer code;
	
	private ClientStatus(Integer code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

}
