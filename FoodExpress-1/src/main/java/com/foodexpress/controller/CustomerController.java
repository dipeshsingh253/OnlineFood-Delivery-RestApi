package com.foodexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodexpress.exception.CustomerException;
import com.foodexpress.model.Customer;
import com.foodexpress.service.CustomerService;
import com.foodexpress.exception.LoginException;

//localhost:8088/swagger-ui/
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/customers")
	public ResponseEntity<Customer> add(@RequestBody Customer customer) throws CustomerException {
		Customer save = customerService.adddCustomer(customer);
		return new ResponseEntity<Customer>(save, HttpStatus.ACCEPTED);
	}

	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAll() throws CustomerException {

		List<Customer> customers = customerService.viewAllCustomers();

		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
			throws CustomerException, LoginException {
		Customer returnCustomer = customerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(returnCustomer, HttpStatus.OK);
	}

	@DeleteMapping("/updates")
	public ResponseEntity<Customer> delete(@RequestParam String email) throws CustomerException {
		Customer deleted = customerService.removeCustomer(email);
		return new ResponseEntity<Customer>(deleted, HttpStatus.OK);

	}

}
