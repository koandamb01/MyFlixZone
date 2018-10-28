package com.David.javaProject.models.paypal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentInfoRepo extends CrudRepository<PaymentInfo, Long>{
	List<PaymentInfo> findAll();
}

