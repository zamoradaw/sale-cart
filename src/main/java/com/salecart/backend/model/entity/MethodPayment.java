package com.salecart.backend.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="metodos_pago")
public class MethodPayment implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idMetodoPago")
	private Long id;
	
	@NotEmpty
	@Column(name = "nombre", unique = true, length = 25, nullable = false)
	private String name;
	
	public MethodPayment() {
	}

	public MethodPayment(@NotEmpty String name) {
		this.name = name;
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
	
	private static final long serialVersionUID = 1L;
}
