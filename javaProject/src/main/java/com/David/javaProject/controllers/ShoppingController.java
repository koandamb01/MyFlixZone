package com.David.javaProject.controllers;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.David.javaProject.models.OrderDetail;
import com.David.javaProject.models.ProductInfo;
import com.David.javaProject.models.Response;
import com.David.javaProject.models.general.User;
import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.paypal.AddressRepo;
import com.David.javaProject.models.paypal.PaymentInfo;
import com.David.javaProject.models.paypal.PaymentInfoRepo;
import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.Order;
import com.David.javaProject.models.shopping.OrderProduct;
import com.David.javaProject.models.shopping.OrderProductRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.Product;
import com.David.javaProject.models.shopping.ProductRepo;
import com.David.javaProject.repositories.UserRepository;
import com.David.javaProject.services.ShoppingService;

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
	@Autowired
	private AddressRepo addressRepo;
	@Autowired
	private PaymentInfoRepo paymentInfoRepo;
	@Autowired
	private SessionService sessionService;

//	public ShoppingController(UserRepository userRepo, CategoryRepo categoryRepo, OrderRepo orderRepo, ProductRepo productRepo, OrderProductRepo orderProductRepo, ShoppingService shoppingService, AddressRepo addressRepo, PaymentInfoRepo paymentInfoRepo, SessionService sessionService){
//		this.userRepo =userRepo;
//		this.categoryRepo = categoryRepo;
//		this.orderRepo = orderRepo;
//		this.productRepo = productRepo;
//		this.orderProductRepo = orderProductRepo;
//		this.shoppingService = shoppingService;
//		this.addressRepo = addressRepo;
//		this.paymentInfoRepo = paymentInfoRepo;
//		this.sessionService = sessionService;
//	}

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

	//add address
	@PostMapping("/addAddress")
	public Response addAddress(@RequestBody Address address, Errors errors, HttpSession session) {
		if (errors.hasErrors()) {
			Response res = new Response(false, "Validation errors", errors.getAllErrors());
			return res;
		} else {
			//getting the user from session
			Long userId = this.sessionService.getUserId();
			User user = shoppingService.findUserById(userId);
			//setting user as the address owner
			address.setUser(user);
			//checking if user has a default address
			if (user.getAddresses() == null) {
				Address newAddress = addressRepo.save(address);
				List<Address> list = new ArrayList<>();
				list.add(newAddress);
				Response res = new Response(true, "Added a new default address", list);
				return res;
			} else {
				address.setDefaultShippingAddress(false);
				Address newAddress = addressRepo.save(address);
				List<Address> list = new ArrayList<>();
				list.add(newAddress);
				Response res = new Response(true, "Added an address, it is not default", list);
				return res;
			}
		}
	}

	@GetMapping("/changeAddress/{addressId}")
	public Response changeDefaultAddress(@PathVariable("addressId") Long addressId, HttpSession session) {
		Long userId = this.sessionService.getUserId();
		User user = shoppingService.findUserById(userId);
		//find user
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			//find the address by id from parameter
			Optional<Address> optional = addressRepo.findById(addressId);
			if(optional.isPresent()){
				Address address = optional.get();
				//find default address and set it to false
				shoppingService.findDefaultAddress(user).setDefaultShippingAddress(false);
				//set the new address to true
				address.setDefaultShippingAddress(true);
				addressRepo.save(address);

				//return JSON
				List list = new ArrayList();
				list.add(address);
				Response res = new Response(true, "Could not find user", list);
				return res;
			}
			else{
				Response res = new Response(false, "Could not find address");
				return res;
			}
		}
	}

	//add paymentinfo
	@PostMapping("/addPaymentInfo")
	public Response addPaymentInfo(@RequestBody PaymentInfo paymentInfo, Errors errors, @RequestBody Address address, Errors addressErrors, HttpSession session) {
		//checking errors for payment Info
		if (errors.hasErrors()) {
			Response res = new Response(false, "Validation errors", errors.getAllErrors());
			return res;
		}
		//checking errors for address info
		else if (addressErrors.hasErrors()) {
			Response res = new Response(false, "Validation errors", errors.getAllErrors());
			return res;
		}
		//passed validation
		else {
			//getting the user from session
			Long userId = this.sessionService.getUserId();
			User user = shoppingService.findUserById(userId);
			//setting user as the address owner
			paymentInfo.setUser(user);
			Address checkAddress = shoppingService.checkAddressExist(address.getStreet(),address.getCity(),address.getState(),address.getZipcode(), user);
			//address does not exist
			if (checkAddress == null){
				//add the address to DB and set it as the address for the payment info
				paymentInfo.setAddress(addressRepo.save(address));

				//serve JSON
				List<PaymentInfo> list = new ArrayList<>();
				//save the paymentinfp while at it
				list.add(paymentInfoRepo.save(paymentInfo));
				Response res = new Response(true, "Added a paymentInfo using an existing address", list);
				return res;
			}
			//address does exist already
			else{
				//add the existing address as billing address
				paymentInfo.setAddress(checkAddress);

				//serve JSON
				List<PaymentInfo> list = new ArrayList<>();
				//save the paymentinfp while at it
				list.add(paymentInfoRepo.save(paymentInfo));
				Response res = new Response(true, "Added a paymentInfo using an existing address", list);
				return res;
			}

		}
	}

	//add a product to order
	@GetMapping("/addToOrder/{pathProductId}/{quantity}")
	public Response addToOrder(@PathVariable("pathProductId") Long productId, @PathVariable("quantity") int quantity, HttpSession session) {
		//finding user
		Long userId = this.sessionService.getUserId();
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

	//submit order
	@GetMapping("/submitOrder")
	public Response submitOrder(HttpSession session) {
//		//demo setting session userId as 1
//		Long one = new Long(1);
//		session.setAttribute("userId", one);
		//finding user
		Long userId = this.sessionService.getUserId();
		User user = shoppingService.findUserById(userId);
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			//does a cart exist?
			if (shoppingService.hasCart(user) == null) {
				Response res = new Response(false, "Cart is empty");
				return res;
			} else {
				//If cart exists, proceed
				Order order = shoppingService.hasCart(user);
				shoppingService.finalizeOrder(order);
				orderRepo.save(order);
				Response res = new Response(true, "Submitted Order");
				return res;
			}
		}
	}

	//get cart info
	@GetMapping("/getCart")
	public Response getCart(){
		Long userId = this.sessionService.getUserId();
		User user = shoppingService.findUserById(userId);
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			Order order = shoppingService.hasCart(user);
			//checking if cart is empty
			if (order == null){
				Response res = new Response (true, "Cart is empty");
				return res;
			}
			else{
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(order);
				//make a list of array for productInfo
				List<ProductInfo> productInfoList = new ArrayList();
//				find all the products in the order
				List<OrderProduct> orderProductList = shoppingService.findAllOrderProductByOrderId(order.getId());
				for(OrderProduct orderProduct: orderProductList){
					Product product = orderProduct.getProduct();
					ProductInfo productInfo = new ProductInfo(product.getName(), product.getPrice(), orderProduct.getQuantity(), product.getImg());
					productInfoList.add(productInfo);
				}
				orderDetail.setDetails(productInfoList);
				Response res = new Response(true, "Cart information", orderDetail);
//				Response res = new Response(true, "hey");
				return res;
			}
		}
	}

	//get checkOut information
	@GetMapping("/getCheckOut")
	public Response checkOutInformation(HttpSession session){
		//finding user
		Long userId = this.sessionService.getUserId();
		User user = shoppingService.findUserById(userId);
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			Order order = shoppingService.hasCart(user);
			//find the order by Id
				if(order == null){
					Response res = new Response(false, "Could not find order");
					return res;
				}
				else{
					OrderDetail orderDetail = new OrderDetail();

					orderDetail.setOrder(order);
					orderDetail.setPaymentInfo(order.getPaymentInfo());

					System.out.println(order.getAddress().getCity());
					Address address = new Address(order.getAddress().getName(), order.getAddress().getStreet(), order.getAddress().getCity(), order.getAddress().getState(), order.getAddress().getZipcode(), order.getAddress().getUser());

					orderDetail.setShippingAddress(address);
					//make a list of array for productInfo
					List<ProductInfo> productInfoList = new ArrayList();

					//find all the products in the order
					List<OrderProduct> orderProductList = shoppingService.findAllOrderProductByOrderId(order.getId());

					for(OrderProduct orderProduct: orderProductList){
						Product product = orderProduct.getProduct();
						ProductInfo productInfo = new ProductInfo(product.getName(), product.getPrice(), orderProduct.getQuantity(), product.getImg());
						productInfoList.add(productInfo);
					}
					orderDetail.setDetails(productInfoList);
					Response res = new Response(true, "Order of the user in session", orderDetail);
					return res;
				}
			}
		}

		//get one order information for detail
	@GetMapping("/getOneOrder/{orderId}")
	public Response getOneOrder(@PathVariable("orderId") Long orderId, HttpSession session){
		//finding user
		Long userId = this.sessionService.getUserId();
		User user = shoppingService.findUserById(userId);
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			//find the order by Id
			Optional<Order> optional = orderRepo.findById(orderId);{
				if(!optional.isPresent()){
					Response res = new Response(false, "Could not find order");
					return res;
				}
				else{
					Order order = optional.get();
					if(order.getStatus()=="cart"){
						Response res = new Response(false, "The order is in cart");
						return res;
					}
					OrderDetail orderDetail = new OrderDetail();

					orderDetail.setOrder(order);
					orderDetail.setPaymentInfo(order.getPaymentInfo());

					System.out.println(order.getAddress().getCity());
					Address address = new Address(order.getAddress().getName(), order.getAddress().getStreet(), order.getAddress().getCity(), order.getAddress().getState(), order.getAddress().getZipcode(), order.getAddress().getUser());

					orderDetail.setShippingAddress(address);
					//make a list of array for productInfo
					List<ProductInfo> productInfoList = new ArrayList();

					//find all the products in the order
					List<OrderProduct> orderProductList = shoppingService.findAllOrderProductByOrderId(order.getId());

					for(OrderProduct orderProduct: orderProductList){
						Product product = orderProduct.getProduct();
						ProductInfo productInfo = new ProductInfo(product.getName(), product.getPrice(), orderProduct.getQuantity(), product.getImg());
						productInfoList.add(productInfo);
					}
					orderDetail.setDetails(productInfoList);
					Response res = new Response(true, "Order of the user in session", orderDetail);
					return res;
				}
			}
		}
	}

	//get All orders
	@GetMapping("/getAllOrders")
	public Response getAllOrders(HttpSession session){
//		//demo setting session userId as 1
//		Long one = new Long(1);
//		session.setAttribute("userId", one);

		//finding user
		Long userId = this.sessionService.getUserId();
		User user = shoppingService.findUserById(userId);
		if (user == null) {
			Response res = new Response(false, "Could not find user");
			return res;
		} else {
			//create a list to return as response
			List list = new ArrayList();
			//find all orders of the user
			List<Order> orderList = shoppingService.findAllOrderOfUser(user);
			//loop the the list
			for(Order order: orderList){
				OrderDetail orderDetail = new OrderDetail();
				//set the order for the orderDetail
				orderDetail.setOrder(order);

				//for each order, find all the orderProducts
				List<OrderProduct> orderProductList = shoppingService.findAllOrderProductByOrderId(order.getId());

				//make a list of array for productInfo
				List<ProductInfo> productInfoList = new ArrayList();

				for(OrderProduct orderProduct: orderProductList){
					Product product = orderProduct.getProduct();
					ProductInfo productInfo = new ProductInfo(product.getName(), product.getPrice(), orderProduct.getQuantity(), product.getImg());
					productInfoList.add(productInfo);
				}
				orderDetail.setDetails(productInfoList);
				list.add(orderDetail);
			}


			Response res = new Response(true, "All the orders of the user in session", list);
			return res;
		}
	}

}
