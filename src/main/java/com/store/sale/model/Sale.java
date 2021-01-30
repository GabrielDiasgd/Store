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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.client.Client;
import com.store.productsale.ProductSale;
import com.store.user.User;

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
	private BigDecimal subtotol;
	private BigDecimal totalValue;
	private OffsetDateTime dateSale;
	
	private StatusSale statusSale; // Verificar nome depois
	
	//private String type; //enum Adicionar no banco de dados
	
	
	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
	private List<ProductSale> productsSale = new ArrayList<>();
	
	
	
	public void calculateTotalValue () {
		getProductsSale().forEach(ProductSale::calculateTotalPrice);
		
		this.totalValue = getProductsSale().stream()
				.map(item -> item.getTotalValue())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	


}
