package com.foodexpress.service;

import java.util.List;

import com.foodexpress.exception.CustomerException;
import com.foodexpress.model.Customer;

public interface CustomerService {

	public Customer adddCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(Customer customer) throws CustomerException;

	public Customer removeCustomer(String customerEmail) throws  CustomerException;
	
	public List<Customer> viewAllCustomers() throws CustomerException;

}
