package com.salecart.backend.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "compras")
public class Purchase implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCompra")
	private Long id;

	@NotNull
	@Column(name = "fecha_compra", nullable = false)
	private Date datePurchase;

	@NotNull
	@Column(name = "monto", nullable = false)
	private double amount;

	@NotEmpty
	@Column(name = "estado", length = 50, nullable = false)
	private String status;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUsuario")
	private AuthUser user;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idMetodoPago")
	private MethodPayment methodPayment;
	
	@JsonIgnoreProperties({"purchase", "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "purchase")
	private List<DetailPurchase> detailPurchase;

	public Purchase() {
	}

	public Purchase(Date datePurchase, double amount, @NotEmpty String status, AuthUser user,
			MethodPayment methodPayment, List<DetailPurchase> detailPurchase) {
		this.datePurchase = datePurchase;
		this.amount = amount;
		this.status = status;
		this.user = user;
		this.methodPayment = methodPayment;
		this.detailPurchase = detailPurchase;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDatePurchase() {
		return datePurchase;
	}

	public void setDatePurchase(Date datePurchase) {
		this.datePurchase = datePurchase;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AuthUser getUser() {
		return user;
	}

	public void setUser(AuthUser user) {
		this.user = user;
	}

	public MethodPayment getMethodPayment() {
		return methodPayment;
	}

	public void setMethodPayment(MethodPayment methodPayment) {
		this.methodPayment = methodPayment;
	}

	public List<DetailPurchase> getDetailPurchase() {
		return detailPurchase;
	}

	public void setDetailPurchase(List<DetailPurchase> detailPurchase) {
		this.detailPurchase = detailPurchase;
	}

	private static final long serialVersionUID = 1L;
}
