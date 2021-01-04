package com.salecart.backend.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.salecart.backend.model.entity.AuthUser;
import com.salecart.backend.model.repository.IUserRepository;
import com.salecart.backend.model.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	@Transactional(readOnly = true)
	public List<AuthUser> listAllUser() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public AuthUser showUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	@Transactional(readOnly = true)
	public boolean fieldRepeat(String username, String email) {
		if(userRepository.existsByEmail(email)) {
			return true;
		}else if(userRepository.existsByUsername(username)) {
			return true;
		}
		
		return false;
	}

	@Override
	@Transactional
	public void saveUser(AuthUser user) {
		String passwordCo = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordCo);

		userRepository.save(user);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean ifUsernameExists(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean ifEmailExists(String email) {
		return userRepository.existsByEmail(email);
	}

}
