package com.store.sale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.sale.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}
