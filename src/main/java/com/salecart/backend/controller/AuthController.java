package com.salecart.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salecart.backend.MessageCustom;
import com.salecart.backend.auth.dto.JwtDto;
import com.salecart.backend.auth.dto.LoginUser;
import com.salecart.backend.model.entity.AuthUser;
import com.salecart.backend.auth.jwt.JwtProvider;
import com.salecart.backend.model.service.IUserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private JwtProvider jwtProvider;
	
	private MessageCustom message = new MessageCustom();
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> created(@Valid @RequestBody AuthUser newUser, BindingResult result){
		
		if(result.hasErrors()) 
			return message.error(result.getFieldErrors());
		
		else if(userService.fieldRepeat(newUser.getUsername(), newUser.getEmail()))
			return message.error("El nombre de usuario o correo electronico ya existe");
		
		userService.saveUser(newUser);
		return message.success("Usuario creado exitosamente");
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUser loginUser, BindingResult result){
		if(result.hasErrors()) 
			return message.error(result.getFieldErrors());
		
		String username = loginUser.getUsername();
		String password = loginUser.getPassword();
		Authentication authentication = authenticationManager
											.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext()
							 .setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication);
		JwtDto jwtDto = new JwtDto(jwt);
		return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
	}
	
}
