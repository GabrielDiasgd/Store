package com.store.Product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.store.Product.ProductDTO;
import com.store.Product.converters.ProductAssemblerDTO;
import com.store.Product.converters.ProductDisassemblerInput;
import com.store.Product.input.ProductInput;
import com.store.Product.model.Product;
import com.store.Product.repository.ProductRepository;
import com.store.Product.service.ProductService;
import com.store.exception.BusinessException;
import com.store.exception.CategoryNotFoundException;
import com.store.exception.ProviderNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepostory;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductAssemblerDTO productAssembler;
	
	@Autowired
	private ProductDisassemblerInput productDisassembler;

	@GetMapping
	public List<ProductDTO> listProduct() {
		return productAssembler.toCollectionDTO(productRepostory.findAll());
	}

	@GetMapping("/{productId}")
	public ProductDTO find(@PathVariable Long productId) {
		return productAssembler.toDTO(productService.find(productId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDTO add(@RequestBody @Valid ProductInput productInput) {
		try {
			Product product = productDisassembler.toDomainObject(productInput);
			return productAssembler.toDTO(productService.save(product));
		} catch (CategoryNotFoundException | ProviderNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}

	}

	@PutMapping("/{productId}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDTO update(@PathVariable Long productId, @RequestBody @Valid ProductInput productInput) {
		try {
			Product product = productDisassembler.toDomainObject(productInput);
			Product currentProduct = productService.find(productId);
			
			BeanUtils.copyProperties(product, currentProduct, "id", "dateCreation");
			return productAssembler.toDTO(productService.save(currentProduct));
			
		} catch (CategoryNotFoundException | ProviderNotFoundException e) {
			throw new BusinessException(e.getMessage());
		}
	}

	@DeleteMapping("/{productId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long productId) {
		productService.delete(productId);
	}
	
	
	
	
	
	
	
	
	

}
