package com.David.javaProject.models.paypal;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.shopping.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="shipping_addresses")
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String street;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
    @OneToOne(mappedBy="shippingAddress", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private Order order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="city_id")
	private City city;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="state_id")
	private State state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="zipcode_id")
	private Zipcode zipcode;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
    
	public ShippingAddress() {
	}

	public ShippingAddress(String street, City city, State state, Zipcode zipcode, User user) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.user = user;
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
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Zipcode getZipcode() {
		return zipcode;
	}
	public void setZipcode(Zipcode zipcode) {
		this.zipcode = zipcode;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
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
	
    
}
