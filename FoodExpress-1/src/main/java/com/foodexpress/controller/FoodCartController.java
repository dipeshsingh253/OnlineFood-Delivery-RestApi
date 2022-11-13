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

//	@PostMapping("/cart")
//	public ResponseEntity<FoodCart> add(@RequestBody FoodCart foodCart) throws FoodCartException {
//		FoodCart save = fService.addFoodCart(foodCart);
//
//		return new ResponseEntity<FoodCart>(save, HttpStatus.ACCEPTED);
//	}

	@GetMapping("/cart")
	public ResponseEntity<List<FoodCart>> getAll(@RequestParam String key) throws FoodCartException, LoginException {
		List<FoodCart> carts = fService.viewAllFoodCarts(key);

		return new ResponseEntity<List<FoodCart>>(carts, HttpStatus.OK);
	}

	@PutMapping("/cart/increase")
	public ResponseEntity<FoodCart> increaseQuantity(@RequestParam String key,@RequestParam Integer itemid, @RequestParam Integer quantity)
			throws FoodCartException, ItemException, LoginException {

		FoodCart ansCart = fService.increaseQuantity(key,itemid, quantity);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.ACCEPTED);
	}

	@PutMapping("/cart/reduce")
	public ResponseEntity<FoodCart> reduceQuantity(@RequestParam String key,@RequestParam Integer itemid, @RequestParam Integer quantity) throws FoodCartException, ItemException, LoginException {

		FoodCart ansCart = fService.reduceQuantity(key, itemid, quantity);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<FoodCart> deleteIteme(@RequestParam String key,@RequestParam Integer itemId)
			throws FoodCartException, ItemException, LoginException {
		FoodCart ansCart = fService.removeItem(key,itemId);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.OK);

	}

	@PutMapping("/cart/clear")
	public ResponseEntity<FoodCart> clearCart(@RequestParam String key) throws FoodCartException, LoginException {
		FoodCart ansCart = fService.clearCart(key);

		return new ResponseEntity<FoodCart>(ansCart, HttpStatus.ACCEPTED);
	}

	@PutMapping("/cart/items/")
	public ResponseEntity<FoodCart> addItemtoCart(@RequestParam String key,@RequestParam Integer id, @RequestParam String itemName)
			throws FoodCartException, ItemException, LoginException {

		FoodCart added = fService.addItemToCart(key, id, itemName);

		return new ResponseEntity<FoodCart>(added, HttpStatus.ACCEPTED);
	}
}
