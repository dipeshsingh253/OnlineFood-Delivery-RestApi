package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.RestaurantException;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.Restaurant;
import com.foodexpress.repo.RestaurantRepo;
import com.foodexpress.repo.*;
@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepo restaurantRepo;
	
	@Autowired 
	private CurrentUserSessionRepo sessionRepo;

	@Override
	public Restaurant addRestaurant(String key,Restaurant restaurant) throws RestaurantException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		
		Optional<Restaurant> optional = restaurantRepo.findById(restaurant.getRestaurantId());

		if (optional.isPresent()) {
			throw new RestaurantException("Resaturant Already Exists");
		}

		return restaurantRepo.save(restaurant);
	}

	@Override
	public List<Restaurant> viewAllRestaurants(String key) throws RestaurantException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		
		List<Restaurant> restaurants = restaurantRepo.findAll();

		if (restaurants.size() == 0) {
			throw new RestaurantException("No Restaurants Available ");
		}

		return restaurants;
	}

	@Override
	public Restaurant updateRestaurant(String key, Restaurant restaurant) throws RestaurantException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		
		
		
		Optional<Restaurant> optional = restaurantRepo.findById(restaurant.getRestaurantId());

		if (optional.isEmpty()) {
			throw new RestaurantException("No Restaurants Available ");
		}

		return restaurantRepo.save(restaurant);
	}

	@Override
	public Restaurant removeRestaurant(String key, Integer restaurantId) throws RestaurantException, LoginException {
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		Optional<Restaurant> optional = restaurantRepo.findById(restaurantId);
		if (optional.isEmpty()) {
			throw new RestaurantException("No Restaurants Available ");
		}

		Restaurant delete = optional.get();

		restaurantRepo.delete(delete);

		return delete;
	}

	@Override
	public Restaurant viewRestaurantById(String key, Integer restaurantId) throws RestaurantException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		Optional<Restaurant> optional = restaurantRepo.findById(restaurantId);
		if (optional.isEmpty()) {
			throw new RestaurantException("No Restaurants Available ");
		}

		return optional.get();
	}

}
