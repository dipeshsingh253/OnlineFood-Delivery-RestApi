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

import com.foodexpress.exception.ItemException;
import com.foodexpress.model.Item;
import com.foodexpress.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping("/items")
	public ResponseEntity<Item> add(@RequestBody Item item) throws ItemException {
		Item save = itemService.addItem(item);

		return new ResponseEntity<Item>(save, HttpStatus.ACCEPTED);

	}

	@GetMapping("/items")
	public ResponseEntity<List<Item>> getAll() throws ItemException {
		List<Item> items = itemService.viewAllItems();

		return new ResponseEntity<List<Item>>(items, HttpStatus.OK);
	}

	@PutMapping("/items")
	public ResponseEntity<Item> update(@RequestBody Item item) throws ItemException {
		Item save = itemService.updateItem(item);

		return new ResponseEntity<Item>(save, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/items/{id}")
	public ResponseEntity<Item> delete(@PathVariable("id") Integer id) throws ItemException {
		Item delete = itemService.removeItemById(id);

		return new ResponseEntity<Item>(delete, HttpStatus.OK);
	}
}
