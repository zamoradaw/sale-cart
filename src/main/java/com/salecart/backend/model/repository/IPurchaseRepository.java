package com.salecart.backend.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salecart.backend.model.entity.Purchase;

@Repository
public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {

	public List<Purchase> findByUserId(Long id);
}
