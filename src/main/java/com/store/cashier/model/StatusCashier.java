package com.store.cashier.model;

public enum StatusCashier {
	
	 CLOSE(0), OPEN(1);
	
	private Integer code;
	
	
	private StatusCashier (Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}
	
	

}
