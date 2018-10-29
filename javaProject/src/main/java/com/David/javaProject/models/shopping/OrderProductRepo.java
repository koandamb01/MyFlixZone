package com.David.javaProject.models.shopping;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductRepo extends CrudRepository<OrderProduct, Long> {
    List<OrderProduct> findByOrder_Id(Long id);
}
