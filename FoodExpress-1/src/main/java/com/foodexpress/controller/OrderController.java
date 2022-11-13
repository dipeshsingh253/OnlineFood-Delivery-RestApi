package com.foodexpress.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodexpress.exception.OrderDetailsException;
import com.foodexpress.model.OrderDetails;
import com.foodexpress.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService oService;

	@PostMapping("/orders")
	public ResponseEntity<OrderDetails> add(@RequestParam String key,@RequestBody OrderDetails oDetails) throws OrderDetailsException, LoginException {

		OrderDetails orderDetails = oService.addOrder(key,oDetails);

		return new ResponseEntity<OrderDetails>(orderDetails, HttpStatus.ACCEPTED);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<OrderDetails>> getAll(@RequestParam String key) throws OrderDetailsException, LoginException {
		List<OrderDetails> orders = oService.viewAllOrders(key);

		return new ResponseEntity<List<OrderDetails>>(orders, HttpStatus.OK);
	}

	@PutMapping("/orders")
	public ResponseEntity<OrderDetails> update(@RequestParam String key,@RequestBody OrderDetails orderDetails) throws OrderDetailsException, LoginException {
		OrderDetails updated = oService.updateOrder(key,orderDetails);
		return new ResponseEntity<OrderDetails>(updated, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/orders")
	public ResponseEntity<OrderDetails> deleteById(@RequestParam String key,@RequestParam Integer id) throws OrderDetailsException, LoginException {

		OrderDetails deleted = oService.removeOrder(key,id);

		return new ResponseEntity<OrderDetails>(deleted, HttpStatus.OK);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDetails> getOrder(@RequestParam String key,@PathVariable("id") Integer id) throws OrderDetailsException, LoginException {
		OrderDetails order = oService.viewOrder(key,id);

		return new ResponseEntity<OrderDetails>(order, HttpStatus.OK);
	}

}
