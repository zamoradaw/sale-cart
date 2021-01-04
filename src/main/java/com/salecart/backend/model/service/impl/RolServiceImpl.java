package com.salecart.backend.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salecart.backend.model.entity.Rol;
import com.salecart.backend.model.repository.IRolRepository;
import com.salecart.backend.model.service.IRolService;

@Service
public class RolServiceImpl implements IRolService{
	
	@Autowired
	private IRolRepository rolRepository;

	@Override
	@Transactional(readOnly = true)
	public Rol showRolByName(String name) {
		return rolRepository.findByName(name);
	}
}
