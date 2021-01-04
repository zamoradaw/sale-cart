package com.salecart.backend.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salecart.backend.model.entity.DetailPurchase;
import com.salecart.backend.model.entity.Product;
import com.salecart.backend.model.entity.Purchase;
import com.salecart.backend.model.repository.IDetailPurchaseRepository;
import com.salecart.backend.model.repository.IPurchaseRepository;
import com.salecart.backend.model.service.IPurchaseService;
import com.salecart.backend.model.service.IProductService;

@Service
public class PurchaseServiceImpl implements IPurchaseService{
	
	@Autowired
	private IDetailPurchaseRepository detailPurchaseRepository;
	
	@Autowired
	private IPurchaseRepository purchaseRepository;
	
	@Autowired
	private IProductService productService;
	
	@Override
	@Transactional(readOnly = true)
	public List<Purchase> getAllPurchase() {
		return purchaseRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Purchase getByIdPurchase(Long id) {
		return purchaseRepository.findById(id).get();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Purchase> getByUserId(Long id) {
		return purchaseRepository.findByUserId(id);
	}
	
	@Override
	@Transactional
	public Purchase savePurchase(Purchase purchase) {
		Double total = purchase.getDetailPurchase()
				  			   .stream()
				  			   .map(DetailPurchase::getPricePurchase)
				  			   .reduce((after, before) -> after + before)
				  			   .get();
		
		purchase.setAmount(total);
		return purchaseRepository.save(purchase);
	}

	@Override
	@Transactional
	public void saveDetailPurchase(Purchase newPurchase, List<DetailPurchase> allPurchase) {
		allPurchase.forEach(item -> {
			Product product = productService.getProductById(item.getProduct().getId());
			if(product.getStock() >= item.getQuantity()) {
				int difference = product.getStock() - item.getQuantity();
				product.setStock(difference);
				item.setPurchase(newPurchase);
				productService.saveProduct(product);
				detailPurchaseRepository.save(item);
			}
		});
	}

}
