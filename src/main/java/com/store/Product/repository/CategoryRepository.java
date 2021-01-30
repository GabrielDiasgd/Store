package com.store.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.Product.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
