package com.store.sale.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.Product.model.Product;
import com.store.Product.service.ProductService;
import com.store.client.model.Client;
import com.store.client.service.ClientService;
import com.store.sale.model.Sale;
import com.store.sale.model.StatusSale;
import com.store.sale.repository.SaleRepository;
import com.store.user.model.User;
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
		sale.setDateSale(OffsetDateTime.now());
		sale.setStatusSale(StatusSale.FINISHED);
		
		sale.setClient(client);
		sale.setUser(user);

		
		validateProducts(sale);
		sale.calculateTotalValue();
		
		sale.teste();
		saleRepository.save(sale);
		
		return sale;	
	}
	
	
	public void validateProducts (Sale sale) {
		sale.getProductsSale().forEach(productSale -> {
		Product product = productService.find(productSale.getProduct().getId());
		if (productSale.getProduct().getPrice() != null) {
			productSale.setSale(sale);
			productSale.setUnitaryValue(productSale.getProduct().getPrice());
			productSale.setProduct(product);
			//product.stockSale(productSale.getQuantity());
		} else {
			productSale.setSale(sale);
			productSale.setProduct(product);
			productSale.setUnitaryValue(product.getPrice());
			//product.stockSale(productSale.getQuantity());
		}
		});
	}

}
