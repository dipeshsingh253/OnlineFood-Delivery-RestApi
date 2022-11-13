package com.foodexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodexpress.exception.OrderDetailsException;
import com.foodexpress.model.OrderDetails;
import com.foodexpress.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService oService;

	@PostMapping("/orders")
	public ResponseEntity<OrderDetails> add(@RequestBody OrderDetails oDetails) throws OrderDetailsException {

		OrderDetails orderDetails = oService.addOrder(oDetails);

		return new ResponseEntity<OrderDetails>(orderDetails, HttpStatus.ACCEPTED);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<OrderDetails>> getAll() throws OrderDetailsException {
		List<OrderDetails> orders = oService.viewAllOrders();

		return new ResponseEntity<List<OrderDetails>>(orders, HttpStatus.OK);
	}

	@PutMapping("/orders")
	public ResponseEntity<OrderDetails> update(@RequestBody OrderDetails orderDetails) throws OrderDetailsException {
		OrderDetails updated = oService.updateOrder(orderDetails);
		return new ResponseEntity<OrderDetails>(updated, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<OrderDetails> deleteById(@PathVariable("id") Integer id) throws OrderDetailsException {

		OrderDetails deleted = oService.removeOrder(id);

		return new ResponseEntity<OrderDetails>(deleted, HttpStatus.OK);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<OrderDetails> getOrder(@PathVariable("id") Integer id) throws OrderDetailsException {
		OrderDetails order = oService.viewOrder(id);

		return new ResponseEntity<OrderDetails>(order, HttpStatus.OK);
	}

}
