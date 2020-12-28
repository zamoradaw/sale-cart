package com.salecart.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salecart.backend.model.entity.DetailPurchase;

@Repository
public interface IDetailPurchaseRepository extends JpaRepository<DetailPurchase, Long>{
	
}
