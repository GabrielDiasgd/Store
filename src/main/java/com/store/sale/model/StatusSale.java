package com.store.sale.model;

public enum StatusSale {
	
	OPEN(0), FINISHED(1);
	
	private Integer code;
	
	public int getCode() {
		return code;
	}
	
	 private StatusSale(Integer code) {
		 this.code = code;
	}



}
