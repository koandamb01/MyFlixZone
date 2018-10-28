package com.David.javaProject;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.general.UserRepo;
import com.David.javaProject.models.paypal.*;
import com.David.javaProject.models.shopping.Category;
import com.David.javaProject.models.shopping.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//CommandLineRunner runs whenever server starts
public class JavaProjectApplication implements CommandLineRunner{
	@Autowired
	private CityRepo cityRepository;
	@Autowired
	private CategoryRepo categoryRepository;
	@Autowired
	private StateRepo stateRepository;
	@Autowired
	private ZipcodeRepo zipcodeRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ShippingAddressRepo shippingRepo;
	@Autowired
	private PaymentInfoRepo paymentRepo;

	public static void main(String[] args) {
		SpringApplication.run(JavaProjectApplication.class, args);
	}
	
	//This runs whenever server starts
	@Override
	public void run(String... args) throws Exception {
//		City city1= cityRepository.save(new City("Seattle"));
//		City city2= cityRepository.save(new City("Chicago"));
//		City city3= cityRepository.save(new City("Paris"));
//		City city4= cityRepository.save(new City("London"));
//
//		State state1 =stateRepository.save(new State("California"));
//		State state2 = stateRepository.save(new State("New York"));
//		State state3 = stateRepository.save(new State("Colorado"));
//		State state4 = stateRepository.save(new State("Ohio"));
//		State state5 = stateRepository.save(new State("Oregon"));
//		State state6 = stateRepository.save(new State("Montana"));
//
//		Zipcode zip1 =  zipcodeRepo.save(new Zipcode(11111));
//		Zipcode zip2 = zipcodeRepo.save(new Zipcode(22222));
//		Zipcode zip3 = zipcodeRepo.save(new Zipcode(33333));
//		Zipcode zip4 = zipcodeRepo.save(new Zipcode(44444));
//
//		User user1 = userRepo.save(new User("Bob", "Bobbers", "bob@bob.com", "saltyhomie"));
//		User user2 = userRepo.save(new User("Adam", "Liang", "adam@adam.com", "saltyhomie"));
//		User user3 = userRepo.save(new User("Tony", "Peterson", "tony@tony.com", "saltyhomie"));
//		User user4 = userRepo.save(new User("Erica", "Jones", "erica@erica.com", "saltyhomie"));
//
//		ShippingAddress shipping1 = shippingRepo.save(new ShippingAddress("1234 bullet street", city1 ,state1, zip1, user1));
//		ShippingAddress shipping2 = shippingRepo.save(new ShippingAddress("4314 boom street", city2 ,state2, zip2, user4));
//		ShippingAddress shipping3 = shippingRepo.save(new ShippingAddress("3251 choo street", city3 ,state3, zip3, user3));
//		ShippingAddress shipping4 = shippingRepo.save(new ShippingAddress("7777 bang street", city4 ,state4, zip4, user2));
//		ShippingAddress shipping5 = shippingRepo.save(new ShippingAddress("8834 b street", city2 ,state4, zip3, user1));
// 		ShippingAddress shipping6 = shippingRepo.save(new ShippingAddress("5151 c street", city3 ,state3, zip2, user1));
//
//
//		PaymentInfo pay1 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user1, city1, state1, zip1));
//		PaymentInfo pay2 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user2, city2, state2, zip2));
//		PaymentInfo pay3 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user3, city2, state3, zip3));
//		PaymentInfo pay4 = paymentRepo.save(new PaymentInfo("4124-4214-2424-2424", "1234 bullet street", user4, city3, state4, zip4));
//
//
//		categoryRepository.save(new Category("A"));
//		categoryRepository.save(new Category("B"));
//		categoryRepository.save(new Category("C"));
//		categoryRepository.save(new Category("D"));





	}
}
