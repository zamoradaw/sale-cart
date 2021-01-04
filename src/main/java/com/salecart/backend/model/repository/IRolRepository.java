package com.salecart.backend.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salecart.backend.model.entity.Rol;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{
	
	public Rol findByName(String name);

}
