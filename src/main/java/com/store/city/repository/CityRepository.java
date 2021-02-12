package com.store.city.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.city.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long>{

}
