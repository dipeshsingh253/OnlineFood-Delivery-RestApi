package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.CustomerException;
import com.foodexpress.model.Customer;
import com.foodexpress.repo.*;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public Customer adddCustomer(Customer customer) throws CustomerException {

		Customer c = customerRepo.findByEmail(customer.getEmail());

		if (c != null) {
			throw new CustomerException("Customer Alredy Exists");
		}

		return customerRepo.save(customer);

	}
	
	
	
	
	
	
	
	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {

		List<Customer> customers = customerRepo.findAll();

		if (customers.size() == 0) {
			throw new CustomerException("No Customers available");
		}

		return customers;
	}







	@Override
	public Customer updateCustomer(Customer customer) throws CustomerException {
		
		
		Customer updated = customerRepo.findByEmail(customer.getEmail());
		
			if(updated==null) {
				throw new CustomerException("No Customers available");
			}
		
		
		return customerRepo.save(customer);
	}







	@Override
	public Customer removeCustomer(String customerEmail) throws CustomerException {
		
		Customer delete = customerRepo.findByEmail(customerEmail);
		
		if(delete==null) {
			throw new CustomerException("No Customers available");
		}
		
		customerRepo.delete(delete);
		
		return delete;
	}

}
