package com.salecart.backend.model.service;

import java.util.List;

import com.salecart.backend.model.entity.AuthUser;

public interface IUserService {

	public List<AuthUser> listAllUser();

	public AuthUser showUserByUsername(String username);
	
	public boolean fieldRepeat(String username, String email);

	public void saveUser(AuthUser user);
	
	public boolean ifUsernameExists(String username);
	
	public boolean ifEmailExists(String email);
}
