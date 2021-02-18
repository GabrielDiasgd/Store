package com.store.sale.model;

public enum TypeSale {
	
	INCASH(0), TERM(1);
	
	private Integer code;
	
	public Integer getTypeSale() {
		return code;
	}
	
	private TypeSale (Integer code) {
		this.code = code;
	}

}
