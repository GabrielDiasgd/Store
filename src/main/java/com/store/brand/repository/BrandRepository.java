package com.store.brand.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.brand.model.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}
