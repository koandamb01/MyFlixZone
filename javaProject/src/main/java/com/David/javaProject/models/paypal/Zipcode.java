//package com.David.javaProject.models.paypal;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//
//@Entity
//@Table(name="zipcodes")
//public class Zipcode {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	private int code;
//	@Column(updatable=false)
//	private Date createdAt;
//	private Date updatedAt;
//
//    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
//    private List<Address> shippingAddress;
//
//    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
//    private List<PaymentInfo> paymentInfo;
//
//	public Zipcode() {
//	}
//
//	public Zipcode(int code) {
//		this.code = code;
//	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public int getCode() {
//		return code;
//	}
//	public void setCode(int code) {
//		this.code = code;
//	}
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//	public List<Address> getAddress() {
//		return shippingAddress;
//	}
//	public void setAddress(List<Address> shippingAddress) {
//		this.shippingAddress = shippingAddress;
//	}
//	public List<PaymentInfo> getPaymentInfo() {
//		return paymentInfo;
//	}
//	public void setPaymentInfo(List<PaymentInfo> paymentInfo) {
//		this.paymentInfo = paymentInfo;
//	}
//	@PrePersist
//	public void onCreate() {
//		this.createdAt = new Date();
//	}
//	@PreUpdate
//	public void onUpdate() {
//		this.updatedAt = new Date();
//	}
//
//}
