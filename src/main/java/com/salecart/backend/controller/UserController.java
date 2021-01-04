package com.salecart.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salecart.backend.MessageCustom;
import com.salecart.backend.model.entity.AuthUser;
import com.salecart.backend.model.service.IUserService;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UserController {

	@Autowired
	private IUserService userService;
	
	private MessageCustom message = new MessageCustom();
	
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("{username}")
	public ResponseEntity<?> show(@PathVariable Optional<String> username){
		
		if(username.isEmpty() || username.get().isBlank()) {
			return message.error("El nombre de usuario es null.");
		}else if(!userService.ifUsernameExists(username.get())){
			return message.error("El nombre de usuario no existe.");
		}
		
		AuthUser user = userService.showUserByUsername(username.get());
		
		return new ResponseEntity<AuthUser>(user, HttpStatus.OK);
	}
}
