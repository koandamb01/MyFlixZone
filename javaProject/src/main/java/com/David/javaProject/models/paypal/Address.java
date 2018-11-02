package com.David.javaProject.models.paypal;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.shopping.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="addresses")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Street is required.")
	private String street;
	
	@NotBlank(message="City is required.")
	private String city;
	
	@NotBlank(message="State is required.")
	private String state;
	
	@NotBlank(message="Zip code is required.")
	private String zipcode;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	private boolean defaultShippingAddress = true;

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	private List<Order> orders =new ArrayList<>();

	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private List<PaymentInfo> paymentInfos = new ArrayList<>();



	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
    
	public Address() {
	}

	public Address(String street, String city, String state, String zipcode, User user, boolean defaultShippingAddress) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.user = user;
		this.defaultShippingAddress = defaultShippingAddress;
	}

	public Address(String street, String city, String state, String zipcode, User user) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.user = user;
	}
	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<PaymentInfo> getPaymentInfos() {
		return paymentInfos;
	}

	public void setPaymentInfos(List<PaymentInfo> paymentInfos) {
		this.paymentInfos = paymentInfos;
	}

	public boolean isDefaultShippingAddress() {
		return defaultShippingAddress;
	}

	public void setDefaultShippingAddress(boolean defaultShippingAddress) {
		this.defaultShippingAddress = defaultShippingAddress;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
