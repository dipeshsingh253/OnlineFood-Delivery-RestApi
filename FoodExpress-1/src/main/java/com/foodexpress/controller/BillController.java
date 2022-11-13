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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodexpress.exception.BillException;
import com.foodexpress.exception.CustomerException;
import com.foodexpress.exception.LoginException;
import com.foodexpress.exception.OrderDetailsException;
import com.foodexpress.model.Bill;
import com.foodexpress.service.BillService;

@RestController
public class BillController {

	@Autowired
	private BillService billService;

	@PostMapping("/bills")
	public ResponseEntity<Bill> add(@RequestParam String key, @RequestBody Bill bill)
			throws BillException, CustomerException, OrderDetailsException, LoginException {
		Bill saveBill = billService.addBill(key,bill);

		return new ResponseEntity<Bill>(saveBill, HttpStatus.ACCEPTED);
	}

	@GetMapping("/bills")
	public ResponseEntity<List<Bill>> getAll(@RequestParam String key) throws BillException {
		List<Bill> bills = billService.viewAllBills(key);

		return new ResponseEntity<List<Bill>>(bills, HttpStatus.OK);
	}

	@PutMapping("/bills")
	public ResponseEntity<Bill> update(@RequestParam String key,@RequestBody Bill bill)
			throws BillException, OrderDetailsException, LoginException {
		Bill updated = billService.updateBill(key, bill);

		return new ResponseEntity<Bill>(updated, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/bills")
	public ResponseEntity<Bill> delete(@RequestParam String key,@RequestParam Integer id) throws BillException, LoginException {

		Bill deleted = billService.removeBill(key,id);

		return new ResponseEntity<Bill>(deleted, HttpStatus.ACCEPTED);

	}

	@GetMapping("/bills/{id}")
	public ResponseEntity<Bill> add(@RequestParam String key ,@PathVariable("id") Integer id) throws BillException, LoginException {
		Bill saveBill = billService.viewBill(key,id);

		return new ResponseEntity<Bill>(saveBill, HttpStatus.ACCEPTED);
	}
}
