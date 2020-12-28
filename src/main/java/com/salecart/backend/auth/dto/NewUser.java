package com.salecart.backend.auth.dto;

import javax.validation.constraints.NotBlank;

import com.salecart.backend.model.entity.Rol;

public class NewUser {

	@NotBlank
	private String username;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	private Rol rol;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
