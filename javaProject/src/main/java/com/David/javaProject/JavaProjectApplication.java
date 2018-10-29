package com.David.javaProject;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.general.*;
import com.David.javaProject.models.music.FavoriteRepo;
import com.David.javaProject.models.paypal.*;
import com.David.javaProject.models.shopping.*;
import com.David.javaProject.models.shopping.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//CommandLineRunner runs whenever server starts
public class JavaProjectApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepo categoryRepository;
	@Autowired
	private UserRepo userRepo;
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

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}
	
	//This runs whenever server starts
	@Override
	public void run(String... args) throws Exception {
//		User user1 = userRepo.save(new User("Bob", "Bobbers", "bob@bob.com", "saltyhomie"));
//		User user2 = userRepo.save(new User("Adam", "Liang", "adam@adam.com", "saltyhomie"));
//		User user3 = userRepo.save(new User("Tony", "Peterson", "tony@tony.com", "saltyhomie"));
//		User user4 = userRepo.save(new User("Erica", "Jones", "erica@erica.com", "saltyhomie"));
//		User user5 = userRepo.save(new User("Admin1", "Admin1", "admin1@admin.com", "saltyhomie", "admin"));
//		User user6 = userRepo.save(new User("Admin2", "Admin2", "admin2@admin.com", "saltyhomie", "admin"));
////
//		Address shipping1 = shippingRepo.save(new Address("1234 bullet street", "San Francisco" ,"California", "11111", user1));
//		Address shipping2 = shippingRepo.save(new Address("4314 boom street", "New York" ,"New York", "22222", user4));
//		Address shipping3 = shippingRepo.save(new Address("3251 choo street", "Seattle" ,"Washington", "33333", user3));
//		Address shipping4 = shippingRepo.save(new Address("7777 bang street", "Austin" ,"Texas", "44444", user2));
//		Address shipping5 = shippingRepo.save(new Address("8834 b street", "Miami" ,"Florida", "55555", user1));
// 		Address shipping6 = shippingRepo.save(new Address("5151 c street", "San Jose" ,"Califronia", "11111", user1));
//
//
//
//		PaymentInfo pay1 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user1, "San Francisco" ,"California", "11111"));
//		PaymentInfo pay2 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user2, "New York" ,"New York", "22222"));
//		PaymentInfo pay3 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user3, "Seattle" ,"Washington", "33333"));
//		PaymentInfo pay4 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user4, "Miami" ,"Florida", "55555"));
//
//
//		Category cat1 = categoryRepository.save(new Category("A"));
//		Category cat2 = categoryRepository.save(new Category("B"));
//		Category cat3 = categoryRepository.save(new Category("C"));
//		Category cat4 = categoryRepository.save(new Category("D"));
//
//		Product product1 =






	}
}
