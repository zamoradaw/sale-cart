package com.salecart.backend.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salecart.backend.model.entity.AuthUser;
import com.salecart.backend.model.entity.MainUser;
import com.salecart.backend.model.service.IUserService;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AuthUser user = userService.showUserByUsername(username);
		
		return MainUser.build(user);
	}

}
