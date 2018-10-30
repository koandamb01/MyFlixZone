package com.David.javaProject.controllers;


import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.User;
import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.shopping.*;
import com.David.javaProject.services.ShoppingService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.ProductRepo;
import com.David.javaProject.repositories.UserRepository;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.Option;
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
	@Autowired
	private ShoppingService shoppingService;

	@GetMapping("/testing")
	public Response testing(HttpSession session) {
		//demo setting session userId as 1
		Long one = new Long(1);
		session.setAttribute("userId", one);
		//finding user
		Long userId = (Long) session.getAttribute("userId");
		User user = shoppingService.findUserById(userId);
		Order order = shoppingService.hasCart(user);
		List list = new ArrayList();
		list.add(order);
		Response res = new Response(true, "Could not find user", list);
		return res;
	}

	@GetMapping("/addToOrder/{pathProductId}/{quantity}")
	public Response addToOrder(@PathVariable("pathProductId") Long productId, @PathVariable("quantity") int quantity, HttpSession session) {
		//demo setting session userId as 1
		Long one = new Long(1);
		session.setAttribute("userId", one);
		//finding user
		Long userId = (Long) session.getAttribute("userId");
		User user = shoppingService.findUserById(userId);
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			Order order;
			//does a cart exist?
			if (shoppingService.hasCart(user) == null) {
				//finding default shipping
				Address shipping = shoppingService.findDefaultAddress(user);
				//making an order model through constructor
				order = orderRepo.save(new Order("cart", shipping, user.getPaymentInfo(), user));
				orderRepo.save(order);
			} else {
				//If cart exists, keep using the previous cart
				order = shoppingService.hasCart(user);
			}
			//finding the product
			Product product = shoppingService.findProductById(productId);
			if (product == null) {
				Response res = new Response(false, "Could not find product");
				return res;
			} else {
				//checking if order and product is already linked
				OrderProduct orderAndProduct = shoppingService.orderProductLinked(order, product);
				if (orderAndProduct == null) {
					//linking the product with the order by quanity, order and product
					orderProductRepo.save(new OrderProduct(order, product, quantity));
					//calculating total cost of the order
					shoppingService.setTotal(order);
				} else {
					shoppingService.setNewQuantity(orderAndProduct, quantity);
					shoppingService.setTotal(order);
				}

				Response res = new Response(true, "Successfully added to cart");
				return res;
			}
		}
	}

	@GetMapping("/submitOrder")
	public Response submitOrder(HttpSession session) {
		//demo setting session userId as 1
		Long one = new Long(1);
		session.setAttribute("userId", one);
		//finding user
		Long userId = (Long) session.getAttribute("userId");
		User user = shoppingService.findUserById(userId);
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			Order order;
			//does a cart exist?
			if (shoppingService.hasCart(user) == null) {
				Response res = new Response(false, "Cart is empty");
				return res;
			} else {
				//If cart exists, proceed
				Response res = new Response(false, "Cart is empty");
				return res;
			}
		}
	}
}
