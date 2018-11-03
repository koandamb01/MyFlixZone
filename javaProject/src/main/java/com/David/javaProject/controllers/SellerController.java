package com.David.javaProject.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.Response;
import com.David.javaProject.models.shopping.Category;
import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.Order;
import com.David.javaProject.models.shopping.OrderProductRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.Product;
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
	
	// delete order
	@DeleteMapping("/orders/{id}/delete")
	public Response deleteOrder(@PathVariable("id") Long order_id) {
		Response res = new Response();
		Optional<Order> opt = orderRepo.findById(order_id);
		
		if (opt.isPresent()) {
			orderRepo.delete(opt.get());
			res.setMessage("Order id: "+ order_id +", deleted");
			res.setStatus(true);
		} else {
			res.setMessage("Order not found");
			res.setStatus(false);
		}
		return res;
	}
	
	// delete item from an order
//	@DeleteMapping("/orders/{order_id}/item/{item_id}/delete")
//	public Response deleteItem(@PathVariable("order_id") Long order_id, @PathVariable("item_id") Long item_id) {
//		Response res = new Response();
//		Optional<Order> opt = orderRepo.findById(order_id);
//		
//		if (opt.isPresent()) {
//			Order order = opt.get();
//			List<Product> itemList = order.getProducts();
//			for (Product product : itemList) {
//				if (product.getId() == item_id) {
//					itemList.remove(product);
//					order.setProducts(itemList);
//					orderRepo.save(order);
//					res.setMessage("Item removed");
//					res.setStatus(true);
////					List<Order> temp = new ArrayList<Order>();
////					temp.add(order);
//					res.setData(order.getProducts());
//					return res;
//				}
//			}
//			res.setMessage("Item not found");
//			res.setStatus(false);
//		} else {
//			res.setMessage("Order not found");
//			res.setStatus(false);
//		}
//		return res;
//	}
	
	// edit order
	@PutMapping("/orders")
	public Response editOrder(@RequestBody Order order) {
		Response res = new Response();
		Optional<Order> opt = orderRepo.findById(order.getId());
		
		if (opt.isPresent()) {
			Order orderFromDb = opt.get();
			if (order.getStatus() != null) { orderFromDb.setStatus(order.getStatus()); }
			if (order.getTotal() != null) { orderFromDb.setTotal(order.getTotal()); }
			if (order.getDatePaid() != null) { orderFromDb.setDatePaid(order.getDatePaid()); }
			if (order.getExpectedDate() != null) { orderFromDb.setExpectedDate(order.getExpectedDate()); }
			if (order.getCarrier() != null) { orderFromDb.setCarrier(order.getCarrier()); }
			if (order.getTracking_id() != null) { orderFromDb.setTracking_id(order.getTracking_id()); }
			orderRepo.save(orderFromDb);
			res.setStatus(true);
			res.setMessage("Order successfully edited");
			List<Order> data = new ArrayList<Order>();
			data.add(orderFromDb);
			res.setData(data);
		} else {
			res.setStatus(false);
			res.setMessage("Order not found");
		}
		return  res;
	}

	// get all products
	@GetMapping("/product")
	public Response getAllProducts() {
		Response res = new Response();
		List<Product> products = productRepo.findAll();
		if (products.size() == 0) {
			res.setMessage("No products were found!");
			res.setStatus(false);
		} else {
			res.setData(products);
			res.setMessage("Products found");
			res.setStatus(true);
		}
		return res;
	}

	// add Product
	@PostMapping("/product")
	public Response addProduct(@RequestBody Product product, Errors errors) {
		Response res = new Response();
		if (errors.hasErrors()) {
			res.setStatus(false);
			res.setMessage("Validation error");
			res.setData(errors.getAllErrors());
		} else {
			res.setStatus(true);
			res.setMessage("Product successfully added");
			List<Product> data = new ArrayList<Product>();
			data.add(productRepo.save(product));
			res.setData(data);
		}
		return  res;
	}
	
	// edit Product
	@PutMapping("/product")
	public Response editProduct(@RequestBody Product product) {
		Response res = new Response();
		Optional<Product> opt = productRepo.findById(product.getId());
		
		if (opt.isPresent()) {
			Product productFromDb = opt.get();
			if (product.getId() != null) { productFromDb.setId(product.getId()); }
			if (product.getName() != null) { productFromDb.setName(product.getName()); }
			if (!Double.isNaN(product.getPrice())) { productFromDb.setPrice(product.getPrice()); }
			if (product.getStock()!=0) { productFromDb.setStock(product.getStock()); }
			if (product.getDescription() != null) { productFromDb.setDescription(product.getDescription()); }
			if (product.getImg() != null) { productFromDb.setImg(product.getImg()); }
			
			productRepo.save(productFromDb);
			res.setStatus(true);
			res.setMessage("Product successfully edited");
			List<Product> data = new ArrayList<Product>();
			data.add(productFromDb);
			res.setData(data);
		} else {
			res.setStatus(false);
			res.setMessage("Product not found");
		}
		return  res;
	}
	
	// delete Product
	@DeleteMapping("/product/{id}/delete")
	public Response deleteProduct(@PathVariable("id") Long id) {
		Response res = new Response();
		Optional<Product> opt = productRepo.findById(id);
		if (opt.isPresent()) {
			productRepo.delete(opt.get());
			res.setMessage("Product id: "+ id +", deleted");
			res.setStatus(true);
		} else {
			res.setMessage("Product not found");
			res.setStatus(false);
		}
		return res;
	}
	
	// add category
	@PostMapping("/category")
	public Response addCategory(@RequestBody Category category, Errors errors) {
		Response res = new Response();
		if (errors.hasErrors()) {
			res.setStatus(false);
			res.setMessage("Validation error");
			res.setData(errors.getAllErrors());
		} else {
			res.setStatus(true);
			res.setMessage("Category successfully added");
			List<Category> data = new ArrayList<Category>();
			data.add(categoryRepo.save(category));
			res.setData(data);
		}
		return res;
	}
	
	// delete category
	@DeleteMapping("/category/{id}/delete")
	public Response deleteCategory(@PathVariable("id") Long id) {
		Response res = new Response();
		Optional<Category> opt = categoryRepo.findById(id);
		if (opt.isPresent()) {
			categoryRepo.delete(opt.get());
			res.setMessage("Category id: "+ id +", deleted");
			res.setStatus(true);
		} else {
			res.setMessage("Category not found");
			res.setStatus(false);
		}
		return res;
	}
	
	// edit category
	@PutMapping("/category")
	public Response editCategory(@RequestBody Category category) {
		Response res = new Response();
		Optional<Category> opt = categoryRepo.findById(category.getId());
		
		if (opt.isPresent()) {
			Category categoryFromDb = opt.get();
			if (category.getName() != null) { categoryFromDb.setName(category.getName()); }
			
			categoryRepo.save(categoryFromDb);
			res.setStatus(true);
			res.setMessage("Category successfully edited");
			List<Category> data = new ArrayList<Category>();
			data.add(categoryFromDb);
			res.setData(data);
		} else {
			res.setStatus(false);
			res.setMessage("Category not found");
		}
		return  res;
	}
}
