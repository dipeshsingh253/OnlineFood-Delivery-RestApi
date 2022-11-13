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

import com.foodexpress.exception.FoodCartException;
import com.foodexpress.exception.ItemException;
import com.foodexpress.model.FoodCart;
import com.foodexpress.model.Item;
import com.foodexpress.repo.FoodCartRepo;
import com.foodexpress.service.FoodCartService;

@RestController
public class FoodCartController {

	@Autowired
	private FoodCartService fService;

	@PostMapping("/cart")
	public ResponseEntity<FoodCart> add(@RequestBody FoodCart foodCart) throws FoodCartException {
		FoodCart save = fService.addFoodCart(foodCart);

		return new ResponseEntity<FoodCart>(save, HttpStatus.ACCEPTED);
	}

	@GetMapping("/cart")
	public ResponseEntity<List<FoodCart>> getAll() throws FoodCartException {
		List<FoodCart> carts = fService.viewAllFoodCarts();

		return new ResponseEntity<List<FoodCart>>(carts, HttpStatus.OK);
	}

	@PutMapping("/cart/increase")
	public ResponseEntity<FoodCart> increaseQuantity(@RequestBody FoodCart foodCart, @RequestBody Item item,
			@RequestParam Integer quantity) throws FoodCartException, ItemException {

		FoodCart ansCart = fService.increaseQuantity(foodCart, item, quantity);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.ACCEPTED);
	}

	@PutMapping("/cart/reduce")
	public ResponseEntity<FoodCart> reduceQuantity(@RequestBody FoodCart foodCart, @RequestBody Item item,
			@RequestParam Integer quantity) throws FoodCartException, ItemException {

		FoodCart ansCart = fService.reduceQuantity(foodCart, item, quantity);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<FoodCart> deleteIteme(@RequestBody FoodCart foodCart, @RequestBody Item item)
			throws FoodCartException, ItemException {
		FoodCart ansCart = fService.removeItem(foodCart, item);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.OK);

	}

	@PutMapping("/cart/clear")
	public ResponseEntity<FoodCart> clearCart(@RequestBody FoodCart foodCart) throws FoodCartException {
		FoodCart ansCart = fService.clearCart(foodCart);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/cart/items/{id}")
	public ResponseEntity<FoodCart> addItemtoCart(@PathVariable("id") Integer id, @RequestBody Item item) throws FoodCartException, ItemException{

		
		FoodCart added = fService.addItemToCart(id, item);
		
		return new ResponseEntity<FoodCart>(added, HttpStatus.ACCEPTED);
	}
}
