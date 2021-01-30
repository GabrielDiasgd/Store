package com.store.sale.model;

public enum StatusSale {
	
	ABERTA(0), FINALIZADA(1);
	
	private Integer code;
	
	public int getCode() {
		return code;
	}
	
	 private StatusSale(Integer code) {
		 this.code = code;
	}



}
