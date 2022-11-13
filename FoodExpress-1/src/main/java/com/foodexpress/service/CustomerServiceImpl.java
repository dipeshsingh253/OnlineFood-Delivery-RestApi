package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.CustomerException;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.Customer;
import com.foodexpress.model.FoodCart;
import com.foodexpress.repo.*;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentUserSessionRepo sessionRepo;
	
	@Autowired
	private FoodCartRepo foodCartRepo;
	
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

		Customer c = customerRepo.findByEmail(customer.getEmail());

		if (c != null) {
			throw new CustomerException("Customer Alredy Exists");
		}
		
		FoodCart temp = new FoodCart();
		temp.setCartId(0);
		FoodCart savedcart = foodCartRepo.save(temp);
		customer.setFoodCart(savedcart);
		
		return customerRepo.save(customer);

	}

	@Override
	public List<Customer> viewAllCustomers(String key) throws CustomerException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		
		List<Customer> customers = customerRepo.findAll();

		if (customers.size() == 0) {
			throw new CustomerException("No Customers available");
		}

		return customers;
	}

	@Override
	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		Customer updated = customerRepo.findByEmail(customer.getEmail());

		if (updated == null) {
			throw new CustomerException("No Customers available");
		}

		return customerRepo.save(customer);
	}

	@Override
	public Customer removeCustomer(String key,String customerEmail) throws CustomerException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		
		Customer delete = customerRepo.findByEmail(customerEmail);

		if (delete == null) {
			throw new CustomerException("No Customers available");
		}

		customerRepo.delete(delete);

		return delete;
	}

	@Override
	public Customer viewProfile(String key) throws CustomerException, LoginException {

		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("admin")) {
			throw new LoginException("You are logged in as admin");
		}
		
		Optional<Customer> optional = customerRepo.findById(user.getUserId());
		
		if (optional.isEmpty()) {
			throw new CustomerException("Invallid Details Check Again");
		}
		
		return optional.get();
	}

}
