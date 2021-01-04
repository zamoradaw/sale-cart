package com.salecart.backend.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "productos")
public class Product implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto")
	private Long id;

	@NotEmpty
	@Column(name = "nombre", length = 50, nullable = false)
	private String name;

	@NotEmpty
	@Column(name = "foto", length = 200, unique = true, nullable = false)
	private String photo;

	@NotEmpty
	@Column(name = "descripcion", length = 200, nullable = false)
	private String description;

	@NotNull
	@Column(name = "precio", nullable = false)
	private double price;

	@NotNull
	@Column(name = "cantidad", nullable = false)
	private int stock;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	private Brand brand;

	public Product() {
	}

	public Product(@NotEmpty String name, @NotEmpty String photo, @NotEmpty String description, double price,
			int stock) {
		this.name = name;
		this.photo = photo;
		this.description = description;
		this.price = price;
		this.stock = stock;
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

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	private static final long serialVersionUID = 1L;
}
