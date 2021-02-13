package com.store.sale.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.store.sale.dto.SaleDTO;
import com.store.sale.model.Sale;

@Component
public class SaleAssemblerDTO {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public SaleDTO toDTO (Sale sale) {
		return modelMapper.map(sale, SaleDTO.class);
	}
	
	public List<SaleDTO> toCollectionDTO (List<Sale> sales) {
		return sales.stream()
				.map(sale -> toDTO(sale))
				.collect(Collectors.toList());
	}

}
