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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "detalle_compra")
public class DetailPurchase implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idDetalleCompra")
	private Long id;

	@NotNull
	@Min(1)
	@Max(25)
	@Column(name = "cantidad")
	private int quantity;

	@NotNull
	@Column(name = "precioCompra")
	private double pricePurchase;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProducto")
	private Product product;

	@JsonIgnoreProperties({"detailPurchase", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idCompra")
	private Purchase purchase;

	public DetailPurchase() {
	}

	public DetailPurchase(int quantity, double pricePurchase, Product product, Purchase purchase) {
		this.quantity = quantity;
		this.pricePurchase = pricePurchase;
		this.product = product;
		this.purchase = purchase;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPricePurchase() {
		return pricePurchase;
	}

	public void setPricePurchase(double pricePurchase) {
		this.pricePurchase = pricePurchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	private static final long serialVersionUID = 1L;
}
