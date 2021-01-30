package com.store.productsale;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.store.Product.model.Product;
import com.store.sale.model.Sale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductSale {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long id;
	
	private BigDecimal unitaryValue; // change name for price
	
	private BigDecimal totalValue; //idem
	
	private Long quantity;
	
	@ManyToOne
	@JoinColumn(nullable = false, name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn( nullable = false, name = "sale_id")
	private Sale sale;
	

	public void calculateTotalPrice() {
		BigDecimal unitaryValue = this.unitaryValue;
		Long quantity = this.quantity;
		
		if (unitaryValue == null) {
			unitaryValue = BigDecimal.ZERO;
		}
		
		if (quantity == null) {
			quantity = 0L;
		}
		
		this.setTotalValue(unitaryValue.multiply(new BigDecimal(quantity)));
	}
}
