package com.salecart.backend.model.service;

import java.util.List;

import com.salecart.backend.model.entity.DetailPurchase;
import com.salecart.backend.model.entity.Purchase;

public interface IPurchaseService {
	
	//Purchase
	public List<Purchase> getAllPurchase();	
	public Purchase getByIdPurchase(Long id);
	public List<Purchase> getByUserId(Long id);
	public Purchase savePurchase(Purchase purchase);
	
	//DetailPurchase
	public void saveDetailPurchase(Purchase newPurchase, List<DetailPurchase> allPurchase);
	
}
