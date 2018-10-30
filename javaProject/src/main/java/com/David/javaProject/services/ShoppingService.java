package com.David.javaProject.services;

import com.David.javaProject.models.general.User;
import com.David.javaProject.models.paypal.Address;
import com.David.javaProject.models.paypal.AddressRepo;
import com.David.javaProject.models.shopping.*;
import com.David.javaProject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingService {
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
    private AddressRepo addressRepo;

    public User findUserById(Long userId){
        Optional<User> user = userRepo.findById(userId);
        if(user.isPresent()){
            return user.get();
        }
        else{
            return null;
        }
    }

    public Product findProductById(Long id){
        Optional<Product> product = productRepo.findById(id);
        if(product.isPresent()){
            return product.get();
        }
        else{
            return null;
        }
    }

    public Address findDefaultAddress(User user){
        for (Address shippingAddress : user.getAddresses()){
            if (shippingAddress.isDefaultShippingAddress()){
                return shippingAddress;
            }
        }
        return null;
    }

    public void setTotal(Order order){
        double total = 0;
        List<OrderProduct> orderList = orderProductRepo.findByOrder_Id(order.getId());
        for (OrderProduct item : orderList){
            double price = item.getProduct().getPrice();
            int orderQuantity = item.getQuantity();
            total += price * orderQuantity;
        }
        order.setTotal(total); orderRepo.save(order);
    }

    public Order hasCart(User user){
        return (orderRepo.findByStatusAndUser("cart", user));
    }

    public OrderProduct orderProductLinked(Order order, Product product){
        return (orderProductRepo.findByOrderAndProduct(order, product));
    }

    public void setNewQuantity(OrderProduct op, int quantity){
        op.setQuantity(op.getQuantity() + quantity);
    }

    public void finalizeOrder(Order order){
        order.setCarrier("USPS");
        order.setDatePaid(new Date());
        order.setTracking_id(9275391);
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 3);
        dt = c.getTime();
        order.setExpectedDate(dt);
        order.setStatus("unshipped");

    }

    public Address checkAddressExist(String street, String city, String state, String zipcode, User user){
        return addressRepo.findByStreetAndCityAndStateAndZipcodeAndUser(street, city, state, zipcode, user);
    }

    public List<Order> findAllOrderOfUser(User user){
        return orderRepo.findAllByUser(user);
    }

    public List<OrderProduct> findAllOrderProductByOrderId(Long orderId){
        return orderProductRepo.findByOrder_Id(orderId);
    }


}
