package com.David.javaProject.models.paypal;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.shopping.Order;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="payment_info")
public class PaymentInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String ccnumber;

	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id")
	private List<Order> order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="billing_address_id")
	private Address address;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="state_id")
//	private State state;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="zipcode_id")
//	private Zipcode zipcode;



	public PaymentInfo(String ccnumber, User user, Address address) {
		this.ccnumber = ccnumber;
		this.user = user;
		this.address = address;
	}

	public PaymentInfo() {
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

	public String getCcnumber() {
		return ccnumber;
	}

	public void setCcnumber(String ccnumber) {
		this.ccnumber = ccnumber;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
