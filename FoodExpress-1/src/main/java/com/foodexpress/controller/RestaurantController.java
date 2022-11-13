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

import com.foodexpress.exception.RestaurantException;
import com.foodexpress.model.Restaurant;
import com.foodexpress.service.RestaurantService;

@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService rService;

	@PostMapping("/restaurants")
	public ResponseEntity<Restaurant> add(@RequestBody Restaurant restaurant) throws RestaurantException {

		Restaurant saved = rService.addRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(saved, HttpStatus.ACCEPTED);
	}

	@GetMapping("/restaurants")
	public ResponseEntity<List<Restaurant>> getAll() throws RestaurantException {
		List<Restaurant> restaurants = rService.viewAllRestaurants();

		return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
	}

	@PutMapping("/restaurants")
	public ResponseEntity<Restaurant> update(@RequestBody Restaurant restaurant) throws RestaurantException {
		Restaurant saved = rService.updateRestaurant(restaurant);

		return new ResponseEntity<Restaurant>(saved, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/restaurants/{id}")
	public ResponseEntity<Restaurant> deleteById(@PathVariable Integer id) throws RestaurantException {
		Restaurant delete = rService.removeRestaurant(id);

		return new ResponseEntity<Restaurant>(delete, HttpStatus.OK);
	}

	@GetMapping("/restaurants/{id}")
	public ResponseEntity<Restaurant> getById(@PathVariable Integer id) throws RestaurantException {
		Restaurant restaurant = rService.viewRestaurantById(id);

		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);

	}

}
