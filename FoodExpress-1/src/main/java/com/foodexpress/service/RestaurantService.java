package com.foodexpress.service;

import java.util.List;

import com.foodexpress.exception.RestaurantException;
import com.foodexpress.model.Restaurant;

public interface RestaurantService {

	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException;

	public List<Restaurant> viewAllRestaurants() throws RestaurantException;

	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantException;

	public Restaurant removeRestaurant(Integer restaurantId) throws RestaurantException;

	public Restaurant viewRestaurantById(Integer restaurantId) throws RestaurantException;

}
