package com.store.sale.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.store.client.model.Client;
import com.store.formpayment.model.FormPayment;
import com.store.productsale.ProductSale;
import com.store.user.model.User;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true )
@Data
public class Sale {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String codeSale; //código venda?
	private BigDecimal subtotal;
	private BigDecimal totalValue;
	private Long discountPercentage;
	private BigDecimal discountValue;
	private BigDecimal amountPaid;
	//private BigDecimal change;  // tem que colocar outro nome (change of money)
	
	private OffsetDateTime dateSale;
	
	private StatusSale statusSale;
	
	private TypeSale typeSale; 
	
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "form_payment_id", nullable = false)
	private FormPayment formPayment;


	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<ProductSale> productsSale = new ArrayList<>();
	
	
	
	public void calculateTotalValue () {
		getProductsSale().forEach(ProductSale::calculateTotalPrice);
		this.subtotal = getProductsSale().stream()
				.map(item -> item.getTotalValue())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		if (this.discountPercentage >= 1) {
			this.discountValue = subtotal.multiply(new BigDecimal(this.discountPercentage).divide(BigDecimal.valueOf(100)));
			this.totalValue = subtotal.subtract(discountValue) ;
		} else {
			this.totalValue = subtotal;
		}
	
	 
		
		
	}
	
	
	public void teste () {
		productsSale.forEach(item -> {
			var stock = item.getProduct().getStock();
			stock = stock - item.getQuantity();
			item.getProduct().setStock(stock);
		});
	}


}
