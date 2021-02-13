package com.store.sale.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.sale.input.SaleInput;
import com.store.sale.model.Sale;

@Component
public class SaleDisassemblerInput {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public Sale toDomainObject (SaleInput saleInput) {
		return modelMapper.map(saleInput, Sale.class);
	}

}
