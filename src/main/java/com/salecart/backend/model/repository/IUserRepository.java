package com.salecart.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salecart.backend.model.entity.AuthUser;

@Repository
public interface IUserRepository extends JpaRepository<AuthUser, Long>{

	public AuthUser findByUsername(String username);
	
	public boolean existsByUsername(String username);
	
	public boolean existsByEmail(String email);
	
}
