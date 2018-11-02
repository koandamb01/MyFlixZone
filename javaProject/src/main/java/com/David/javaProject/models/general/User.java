package com.David.javaProject.models.general;

import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.David.javaProject.models.music.Favorite;
import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.paypal.PaymentInfo;
import com.David.javaProject.models.shopping.Order;
import com.David.javaProject.models.shopping.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message="First Name is required.")
	@Size(min=2, message="Must be at least 2 characters.")
	private String firstName;
	
	@NotBlank(message="First Name is required.")
	@Size(min=2, message="Must be at least 2 characters.")
	private String lastName;
	
	@NotBlank(message="Email is required.")
	@Email(message="Email is Invalid.")
	private String email;
	
	@NotBlank(message="Password is required.")
	@Size(min=6, message="Must be at least 6 characters.")
	private String password;

	
	private String role = "user";

	@Transient
    private String passwordConfirmation;
	
	@Transient
    private String oldPassword;
	
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

	@PrePersist
	public void onCreate() {
		this.createdAt = new Date();
	}
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@JsonIgnore
    @OneToOne(mappedBy="user", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private PaymentInfo paymentInfo;

	@JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="users_favorites",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name = "favorite_id")
	)
    private List<Favorite> favorites = new ArrayList<>();;

    
    
	@JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Address> shippingAddresses = new ArrayList<>();;

	@JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();;

	@JsonIgnore
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Product> product = new ArrayList<>();;
	
	public User() {}

	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public User(String firstName, String lastName, String email, String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getFirstName() { return firstName; }

	public void setFirstName(String firstName) { this.firstName = firstName; }

	public String getLastName() { return lastName; }

	public void setLastName(String lastName) { this.lastName = lastName; }

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
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public List<Favorite> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}
	public List<Address> getAddresses() {
		return shippingAddresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.shippingAddresses = addresses;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public List<Address> getShippingAddresses() {
		return shippingAddresses;
	}
	public void setShippingAddresses(List<Address> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}
	
	
}
