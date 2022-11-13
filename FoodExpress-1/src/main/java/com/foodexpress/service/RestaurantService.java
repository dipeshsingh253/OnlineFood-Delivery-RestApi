package com.foodexpress.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.foodexpress.exception.RestaurantException;
import com.foodexpress.model.Restaurant;

public interface RestaurantService {

	public Restaurant addRestaurant(String key, Restaurant restaurant) throws RestaurantException, LoginException;

	public List<Restaurant> viewAllRestaurants(String key) throws RestaurantException, LoginException;

	public Restaurant updateRestaurant(String key, Restaurant restaurant) throws RestaurantException, LoginException;

	public Restaurant removeRestaurant(String key, Integer restaurantId) throws RestaurantException, LoginException;

	public Restaurant viewRestaurantById(String key, Integer restaurantId) throws RestaurantException, LoginException;

}
