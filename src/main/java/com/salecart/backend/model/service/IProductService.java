package com.salecart.backend.model.service;

import java.util.List;

import com.salecart.backend.model.entity.Product;

public interface IProductService {

	public List<Product> listAllProduct();
	
	public Product getProductById(Long id);
	
	public boolean saveProduct(Product product);
	
	public boolean ifProductExists(Long id);
}
