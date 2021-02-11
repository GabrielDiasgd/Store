package com.store.Product.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.Groups.categoryId;
import com.store.provider.model.Provider;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;
	
	private String description;
	
	@PositiveOrZero
	private Long stock;
	
	@PositiveOrZero
	private BigDecimal price;
	
	@PositiveOrZero
	private BigDecimal buyPrice;
	
	private Boolean active = true;
	
	@JsonIgnore
	private OffsetDateTime dateLastSale;
	
	private OffsetDateTime dateLastPurchase;
	
	@CreationTimestamp
	private OffsetDateTime dateCreation;
	
	@UpdateTimestamp
	private OffsetDateTime dateUpdate;
	
	@Valid
	@ConvertGroup(from = Default.class, to = categoryId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(nullable = false)
	private Category category;
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Provider provider;

	
	

}
