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

import com.foodexpress.exception.RestaurantException;
import com.foodexpress.model.Restaurant;
import com.foodexpress.service.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService rService;

	@PostMapping("/restaurants")
	public ResponseEntity<Restaurant> add(@RequestParam String key,@RequestBody Restaurant restaurant) throws RestaurantException, LoginException {

		Restaurant saved = rService.addRestaurant(key,restaurant);

		return new ResponseEntity<Restaurant>(saved, HttpStatus.ACCEPTED);
	}

	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getAll(@RequestParam String key) throws RestaurantException, LoginException {
		List<Restaurant> restaurants = rService.viewAllRestaurants(key);

		return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
	}

	@PutMapping("/restaurants")
	public ResponseEntity<Restaurant> update(@RequestParam String key,@RequestBody Restaurant restaurant) throws RestaurantException, LoginException {
		Restaurant saved = rService.updateRestaurant(key,restaurant);

		return new ResponseEntity<Restaurant>(saved, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/restaurants")
	public ResponseEntity<Restaurant> deleteById(@RequestParam String key,@RequestParam Integer id) throws RestaurantException, LoginException {
		Restaurant delete = rService.removeRestaurant(key,id);

		return new ResponseEntity<Restaurant>(delete, HttpStatus.OK);
	}

	@GetMapping("/restaurants/{id}")
	public ResponseEntity<Restaurant> getById(@RequestParam String key,@PathVariable Integer id) throws RestaurantException, LoginException {
		Restaurant restaurant = rService.viewRestaurantById(key,id);

		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);

	}

}
