package com.salecart.backend.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salecart.backend.MessageCustom;
import com.salecart.backend.model.entity.DetailPurchase;
import com.salecart.backend.model.entity.Purchase;
import com.salecart.backend.model.service.IPurchaseService;

@RestController
@RequestMapping("compra")
@CrossOrigin
public class PurchaseController {
	
	@Autowired
	private IPurchaseService purchaseService;
	
	private MessageCustom message = new MessageCustom();
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("{id}")
	public ResponseEntity<?> show(@PathVariable("id") Long id){
		return new ResponseEntity<Purchase>(purchaseService.getByIdPurchase(id), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/nuevo")
	public ResponseEntity<?> created(@Valid @RequestBody Purchase purchase, BindingResult result){
		if(result.hasErrors())
			return message.error(result.getFieldErrors());
		
		try {
			Purchase newPurchase = purchaseService.savePurchase(purchase);
			List<DetailPurchase> allPurchase = purchase.getDetailPurchase();
			purchaseService.saveDetailPurchase(newPurchase, allPurchase);
		}catch(ConstraintViolationException e) {
			return message.errorCatch("Hay un campo que es: "+e.getCause());
		}
		
		return message.success("Good job");
	}
	
}
