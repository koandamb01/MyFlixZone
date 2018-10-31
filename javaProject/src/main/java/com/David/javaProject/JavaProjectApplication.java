package com.David.javaProject;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.paypal.*;
import com.David.javaProject.models.shopping.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.music.FavoriteRepo;
import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.paypal.AddressRepo;
import com.David.javaProject.models.paypal.PaymentInfo;
import com.David.javaProject.models.paypal.PaymentInfoRepo;
import com.David.javaProject.models.shopping.Category;
import com.David.javaProject.models.shopping.CategoryRepo;
import com.David.javaProject.models.shopping.OrderProductRepo;
import com.David.javaProject.models.shopping.OrderRepo;
import com.David.javaProject.models.shopping.Product;
import com.David.javaProject.models.shopping.ProductRepo;
import com.David.javaProject.repositories.UserRepository;

@SpringBootApplication
// CommandLineRunner runs whenever server starts
public class JavaProjectApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepo categoryRepository;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AddressRepo shippingRepo;
	@Autowired
	private PaymentInfoRepo paymentRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private FavoriteRepo favoriteRepo;
	@Autowired
	private OrderProductRepo orderProductRepo;

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}

	// This runs whenever server starts
	@Override
	public void run(String... args) throws Exception {
//		 User user1 = userRepo.save(new User("Bob", "Bobbers", "bob@bob.com",
//		 "saltyhomie", "user"));
//		 User user2 = userRepo.save(new User("Adam", "Liang", "adam@adam.com",
//		 "saltyhomie", "user"));
//		 User user3 = userRepo.save(new User("Tony", "Peterson", "tony@tony.com",
//		 "saltyhomie", "user"));
//		 User user4 = userRepo.save(new User("Erica", "Jones", "erica@erica.com",
//		 "saltyhomie", "user"));
//		 User user5 = userRepo.save(new User("Admin1", "Admin1", "admin1@admin.com",
//		 "saltyhomie", "admin"));
//		 User user6 = userRepo.save(new User("Admin2", "Admin2", "admin2@admin.com",
//		 "saltyhomie", "admin"));
//
//		 Address shipping1 = shippingRepo.save(new Address("1234 bullet street", "San Francisco", "California", "11111", user1));
//		 Address shipping2 = shippingRepo.save(new Address("4314 boom street", "New York" ,"New York", "22222", user4));
//		 Address shipping3 = shippingRepo.save(new Address("3251 choo street",
//		 "Seattle" ,"Washington", "33333", user3));
//		 Address shipping4 = shippingRepo.save(new Address("7777 bang street",
//		 "Austin" ,"Texas", "44444", user2));
//		 Address shipping5 = shippingRepo.save(new Address("8834 b street", "Miami"
//		 ,"Florida", "55555", user1, false));
//		 Address shipping6 = shippingRepo.save(new Address("5151 c street", "San Jose"
//		 ,"Califronia", "11111", user1, false));
//
//		 PaymentInfo pay1 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424",
//		 user1, shipping1));
//		 PaymentInfo pay2 = paymentRepo.save(new PaymentInfo("1111-4214-2424-2424",
//		 user2, shipping2));
//		 PaymentInfo pay3 = paymentRepo.save(new PaymentInfo("2222-4214-2424-2424",
//		 user3, shipping3));
//		 PaymentInfo pay4 = paymentRepo.save(new PaymentInfo("3333-4214-2424-2424",
//		 user4, shipping4));
//
//		 Category cat1 = categoryRepository.save(new Category("A"));
//		 Category cat2 = categoryRepository.save(new Category("B"));
//		 Category cat3 = categoryRepository.save(new Category("C"));
//		 Category cat4 = categoryRepository.save(new Category("D"));
//
//		 Product product1 = productRepo.save(new Product("Chocolate", 5.55, 13, "Great tasting chocolate. It is one of the best.", user6, "https://images-na.ssl-images-amazon.com/images/I/91QL47x2RoL._SY550_.jpg"));
//		 Product product2 = productRepo.save(new Product("Stick", 111.55, 122, "Great tasting Stick. It is one of the best.", user4, "http://www.toyhalloffame.org/sites/www.toyhalloffame.org/files/toys/square/stick_0.png"));
//		 Product product3 = productRepo.save(new Product("Pencil", 222.42, 22, "Great tasting Pencil. It is one of the best.", user3, "https://images-na.ssl-images-amazon.com/images/I/71TBauyMtgL._SL1500_.jpg"));
//		 Product product4 = productRepo.save(new Product("Phone", 33.11, 5, "Great tasting Phone. It is one of the best.", user5, "https://ss7.vzw.com/is/image/VerizonWireless/SAMSUNG_Galaxy_S9_Blue?$device-lg$"));
//
//		 product1.getCategories().add(cat1);product1.getCategories().add(cat2);
//		 product2.getCategories().add(cat2);product2.getCategories().add(cat3);
//		 product3.getCategories().add(cat3);product3.getCategories().add(cat1);
//		 product4.getCategories().add(cat3);product4.getCategories().add(cat4);
//		 productRepo.save(product1);productRepo.save(product2);productRepo.save(product3);productRepo.save(product4);
//
//		 Order order1 = orderRepo.save(new Order("cart", shipping1, pay1, user1));
//		 Order order2 = orderRepo.save(new Order("cart", shipping2, pay2, user2));
//		 Order order3 = orderRepo.save(new Order("cart", shipping3, pay3, user3));
//		 Order order4 = orderRepo.save(new Order("cart", shipping4, pay4, user4));
//		
//		 OrderProduct op1 = orderProductRepo.save(new OrderProduct(order1,
//		 product1,2));OrderProduct op2 = orderProductRepo.save(new
//		 OrderProduct(order1, product2,15));
//		 OrderProduct op3 = orderProductRepo.save(new OrderProduct(order1,
//		 product3,5));
//		
//		 OrderProduct op4 = orderProductRepo.save(new OrderProduct(order2,
//		 product1,1));OrderProduct op5 = orderProductRepo.save(new
//		 OrderProduct(order2, product2,12));
//		 OrderProduct op6 = orderProductRepo.save(new OrderProduct(order2,
//		 product4,1));
//		
//		 OrderProduct op7 = orderProductRepo.save(new OrderProduct(order3,
//		 product4,1));OrderProduct op8 = orderProductRepo.save(new
//		 OrderProduct(order3, product2,2)); 
//		 OrderProduct op9 = orderProductRepo.save(new OrderProduct(order3,
//		 product3,2));
//		
//		 OrderProduct op10 = orderProductRepo.save(new OrderProduct(order4,
//		 product1,3));OrderProduct op11 = orderProductRepo.save(new
//		 OrderProduct(order4, product4,1));
//		 OrderProduct op12 = orderProductRepo.save(new OrderProduct(order4,
//		 product3,1));

	}
}
