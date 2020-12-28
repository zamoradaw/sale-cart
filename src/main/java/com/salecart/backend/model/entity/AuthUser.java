package com.salecart.backend.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios")
public class AuthUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idUsuario")
	private Long id;

	@NotEmpty
	@Column(name = "nombre", length = 25, nullable = false)
	private String name;

	@NotEmpty
	@Column(name = "apellido", length = 25, nullable = false)
	private String lastName;

	@NotEmpty
	@Column(name = "nombreUsuario", length = 50, unique = true, nullable = false)
	private String username;

	@NotEmpty
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z.-]+.(com|net)$")
	@Column(name = "correoElectronico", length = 100, unique = true, nullable = false)
	private String email;

	@NotEmpty
	@Column(name = "direccion", length = 150, nullable = false)
	private String address;

	@NotEmpty
	@Column(name = "clave", length = 100, nullable = false)
	private String password;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRol")
	private Rol rol;

	public AuthUser() {
	}

	public AuthUser(@NotEmpty String name, @NotEmpty String lastName, @NotEmpty String username,
			@NotEmpty @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z.-]+.(com|net)$") String email, @NotEmpty String address,
			@NotEmpty String password, Rol rol) {
		this.name = name;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.address = address;
		this.password = password;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	private static final long serialVersionUID = 1L;
}
