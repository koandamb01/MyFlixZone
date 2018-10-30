package com.David.javaProject.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.Response;
import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.Order;
import com.David.javaProject.models.shopping.OrderProductRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.ProductRepo;
import com.David.javaProject.repositories.UserRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class SellerController {
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

	// get all orders
	@GetMapping("/orders")
	public Response getAllOrders() {
		Response res = new Response();
		List<Order> orders = orderRepo.findAll();
		if (orders.size() == 0) {
			res.setMessage("Orders not found!");
			res.setStatus(false);
		} else {
			for (Order o : orders) {
				System.out.println(o.getTotal());
			}
			res.setData(orders);
			res.setMessage("Orders found");
			res.setStatus(true);
		}
		return res;
	}
	
	// get order details
	@GetMapping("/orders/{id}")
	public Response getOrderDetails(@PathVariable("id") Long order_id) {
		Order order;
		Response res = new Response();
		Optional<Order> opt = orderRepo.findById(order_id);

		// if order by id exists
		if (opt.isPresent()) {
			order = opt.get();
			// create list object
			List<Order> data = new ArrayList<Order>();
			data.add(order);
			// set Response data
			res.setData(data);
			res.setMessage("Order id: "+ order_id +" found");
			res.setStatus(true);
		} else {
			res.setMessage("Order not found");
			res.setStatus(false);
		}
		return res;
	}

	// update order status
	@GetMapping("/orders/{id}/status/{status}")
	public Response setOrderStatus(@PathVariable("id") Long order_id, @PathVariable("status") String status) {
		Order order;
		Response res = new Response();
		Optional<Order> opt = orderRepo.findById(order_id);
		// if order by id exists
		if (opt.isPresent()) {
			order = opt.get();
			// set order status
			order.setStatus(status);
			// save order to database
			order = orderRepo.save(order);
			// create list object
			List<Order> orders = new ArrayList<Order>();
			orders.add(order);
			// set Response data
			res.setData(orders);
			res.setMessage("Order id: "+ order_id +", status set to " + status);
			res.setStatus(true);
		} else {
			res.setMessage("Order not found");
			res.setStatus(false);
		}
		return res;
	}
	
	// filter order list by date
//	@GetMapping("/orders/{start}/{end}")
//	public Response filterOrdersByDate(@PathVariable("start") String start, @PathVariable("end") String end) throws ParseException {
//		Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(start);
//		Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(end);
//		
//		List<Order> orders = orderRepo.findDatesBetween(startDate, endDate);
//		Response res = new Response();
//		
//		if (orders.size() == 0) {
//			res.setMessage("Order not found");
//			res.setStatus(false);
//		} else {
//			res.setData(orders);
//			res.setMessage("Orders set");
//			res.setStatus(true);
//		}
//		return res;
//	}
	
}
