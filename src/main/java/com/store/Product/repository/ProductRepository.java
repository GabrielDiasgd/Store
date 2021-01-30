package com.store.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.Product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
