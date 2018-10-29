package com.David.javaProject.controllers;


import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.shopping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.ProductRepo;
import com.David.javaProject.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paypal")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class ShoppingController {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderProductRepo orderProductRepo;

	@GetMapping("/checking")
	public Response checking() {
		long is = 1;
		Optional<Order> option = orderRepo.findById(is);
		Order order = option.get();


		double total1 = 0;
		List<OrderProduct> list1 = orderProductRepo.findByOrder_Id(is);
		for (OrderProduct item : list1){
			double price = item.getProduct().getPrice();
			int quantity = item.getQuantity();
			total1 += price * quantity;
		}
		order.setTotal(total1); orderRepo.save(order);

		Optional<Order> option1 = orderRepo.findById(is);
		Order order1 = option1.get();
		List list = new ArrayList();
		list.add(order1);

		Response response = new Response();
		response.setStatus(true);
		response.setMessage("Testing");
		response.setData(list);
		return response;
	}
}
