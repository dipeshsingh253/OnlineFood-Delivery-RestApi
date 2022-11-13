package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.RestaurantException;
import com.foodexpress.model.Restaurant;
import com.foodexpress.repo.RestaurantRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {

		Optional<Restaurant> optional = restaurantRepo.findById(restaurant.getRestaurantId());

		if (optional.isPresent()) {
			throw new RestaurantException("Resaturant Already Exists");
		}

		return restaurantRepo.save(restaurant);
	}

	@Override
	public List<Restaurant> viewAllRestaurants() throws RestaurantException {

		List<Restaurant> restaurants = restaurantRepo.findAll();

		if (restaurants.size() == 0) {
			throw new RestaurantException("No Restaurants Available ");
		}

		return restaurants;
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantException {

		Optional<Restaurant> optional = restaurantRepo.findById(restaurant.getRestaurantId());

		if (optional.isEmpty()) {
			throw new RestaurantException("No Restaurants Available ");
		}

		return restaurantRepo.save(restaurant);
	}

	@Override
	public Restaurant removeRestaurant(Integer restaurantId) throws RestaurantException {

		Optional<Restaurant> optional = restaurantRepo.findById(restaurantId);
		if (optional.isEmpty()) {
			throw new RestaurantException("No Restaurants Available ");
		}

		Restaurant delete = optional.get();

		restaurantRepo.delete(delete);

		return delete;
	}

	@Override
	public Restaurant viewRestaurantById(Integer restaurantId) throws RestaurantException {

		Optional<Restaurant> optional = restaurantRepo.findById(restaurantId);
		if (optional.isEmpty()) {
			throw new RestaurantException("No Restaurants Available ");
		}

		return optional.get();
	}

}
