package com.salecart.backend.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salecart.backend.model.entity.Product;
import com.salecart.backend.model.repository.IProductRepository;
import com.salecart.backend.model.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Product> listAllProduct() {
		return productRepository.findByStockBetween(1, 35);
	}

	@Override
	@Transactional(readOnly = true)
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	@Transactional
	public boolean saveProduct(Product product) {
		String name = product.getPhoto();
		if (productRepository.existsByPhoto(name)) {
			return true;
		} else {
			productRepository.save(product);
			return false;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean ifProductExists(Long id) {
		return productRepository.existsById(id);
	}

}
