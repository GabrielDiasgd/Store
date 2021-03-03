package com.store.sale.service;

import java.time.OffsetDateTime;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.Product.model.Product;
import com.store.Product.service.ProductService;
import com.store.cashier.repository.CashierRepository;
import com.store.client.model.Client;
import com.store.client.service.ClientService;
import com.store.exception.AddressNotFoundException;
import com.store.formpayment.model.FormPayment;
import com.store.formpayment.service.FormPaymentService;
import com.store.installmentsale.model.InstallmentSale;
import com.store.installmentsale.repositiry.InstallmentSaleRepository;
import com.store.sale.model.Sale;
import com.store.sale.model.StatusSale;
import com.store.sale.model.TypeSale;
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
	
	@Autowired
	private FormPaymentService formPaymentService;
	
	@Autowired
	private InstallmentSaleRepository installmentSaleRepository;
	
	@Autowired
	private CashierRepository cashierRepository;
	
	
    @Transactional
	public Sale save (Sale sale) {
		Client client = clientService.find(sale.getClient().getId());
		User user = userService.find(sale.getUser().getId());
		FormPayment formPayment = formPaymentService.find(sale.getFormPayment().getId());
		
		sale.setDateSale(OffsetDateTime.now());
		sale.setClient(client);
		sale.setUser(user);
		sale.setFormPayment(formPayment);
		
		InstallmentSale installmentSale = new InstallmentSale();
	
		var cashier = cashierRepository.findByStatusOpen();
	

			sale.setCashier(cashier.get());
			sale.setStatusSale(StatusSale.FINISHED);
			validateProducts(sale);
			sale.calculateTotalValue();
			saleRepository.save(sale);
			
			try {
				cashier.get().getSalesCashier().add(sale);
			} catch (NoSuchElementException e) {
				throw new AddressNotFoundException("test");
			}
	
			if (sale.getTypeSale().equals(TypeSale.TERM)) {
				installmentSale = setInstallmentSale(sale);
				installmentSaleRepository.save(installmentSale);
			}

		return sale;	
	}
    
    public InstallmentSale setInstallmentSale (Sale sale) {
    	InstallmentSale installmentSale = new InstallmentSale();
    	installmentSale.setClient(sale.getClient());
    	installmentSale.setSale(sale);
    	installmentSale.setDueDate(sale.getDateSale().plusDays(30));
    	installmentSale.setStatus(1L);
    	
    	return installmentSale;
    }
	
	
	public void validateProducts (Sale sale) {
		sale.getProductsSale().forEach(productSale -> {
		Product product = productService.find(productSale.getProduct().getId());
		if (productSale.getProduct().getPrice() != null) {
			productSale.setSale(sale);
			productSale.setUnitaryValue(productSale.getProduct().getPrice());
			productSale.setProduct(product);
			product.setStock(product.getStock() - productSale.getQuantity());
		} else {
			productSale.setSale(sale);
			productSale.setProduct(product);
			productSale.setUnitaryValue(product.getPrice());
			product.setStock(product.getStock() - productSale.getQuantity());
		}
		});
	}

}
