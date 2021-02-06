package com.store.sale.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.Product.model.Product;
import com.store.Product.service.ProductService;
import com.store.client.Client;
import com.store.client.service.ClientService;
import com.store.sale.model.Sale;
import com.store.sale.model.StatusSale;
import com.store.sale.repository.SaleRepository;
import com.store.user.User;
import com.store.user.service.UserService;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository saleRepository;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private ProductService productService;
	
    @Transactional
	public Sale save (Sale sale) {
		Client client = clientService.find(sale.getClient().getId());
		User user = userService.find(sale.getUser().getId());
		sale.setDateSale(LocalDateTime.now());
		sale.setStatusSale(StatusSale.FINISHED);
		
		sale.setClient(client);
		sale.setUser(user);
	
		
		validateProducts(sale);
		sale.calculateTotalValue();
		saleRepository.save(sale);
		
		return sale;	
	}
	
	
	public void validateProducts (Sale sale) {
		sale.getProductsSale().forEach(productSale -> {
		Product product = productService.find(productSale.getProduct().getId());
		System.out.println(product.getName());
		productSale.setSale(sale);
		productSale.setProduct(product);
		productSale.setUnitaryValue(product.getPrice());
		System.out.println(product.getPrice());
		});
	}

}
