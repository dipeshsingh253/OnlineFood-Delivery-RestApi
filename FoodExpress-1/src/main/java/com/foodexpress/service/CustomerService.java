package com.foodexpress.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.foodexpress.exception.CustomerException;
import com.foodexpress.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(String key,Customer customer) throws CustomerException, LoginException;

	public Customer removeCustomer(String key,String customerEmail) throws  CustomerException, LoginException;
	
	public Customer viewProfile(String key) throws CustomerException, LoginException;
	
	public List<Customer> viewAllCustomers(String key) throws CustomerException, LoginException;

}
