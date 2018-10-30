package com.David.javaProject.models;

import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.paypal.PaymentInfo;
import com.David.javaProject.models.shopping.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail {
    public Order order;
    public List<?> details = new ArrayList<>();
    public PaymentInfo paymentInfo;
    public Address shippingAddress;

    public OrderDetail() {
    }

    public OrderDetail(Order order, List<?> details) {
        this.order = order;
        this.details = details;
    }

    public OrderDetail(Order order, PaymentInfo paymentInfo, Address shippingAddress) {
        this.order = order;
        this.paymentInfo = paymentInfo;
        this.shippingAddress = shippingAddress;
    }

    public OrderDetail(Order order, List<?> details, PaymentInfo paymentInfo, Address shippingAddress) {
        this.order = order;
        this.details = details;
        this.paymentInfo = paymentInfo;
        this.shippingAddress = shippingAddress;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<?> getDetails() {
        return details;
    }

    public void setDetails(List<?> details) {
        this.details = details;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
