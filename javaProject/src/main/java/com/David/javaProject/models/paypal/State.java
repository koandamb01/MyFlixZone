package com.David.javaProject.models.paypal;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="states")
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<ShippingAddress> shippingAddress;

    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<PaymentInfo> paymentInfo;

	public State() {
	}

	public State(String name) {
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<ShippingAddress> getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(List<ShippingAddress> shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public List<PaymentInfo> getPaymentInfo() {
		return paymentInfo;
	}
	public void setPaymentInfo(List<PaymentInfo> paymentInfo) {
		this.paymentInfo = paymentInfo;
	}
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
}
