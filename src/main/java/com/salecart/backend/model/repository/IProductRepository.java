package com.salecart.backend.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salecart.backend.model.entity.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long>{

	public List<Product> findByStockBetween(int startQuantity, int endQuantity);
	
	public boolean existsByPhoto(String name);
	
	public boolean existsById(Long id);
	
}
