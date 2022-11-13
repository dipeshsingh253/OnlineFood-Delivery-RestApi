package com.foodexpress.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.foodexpress.exception.FoodCartException;
import com.foodexpress.model.FoodCart;
import com.foodexpress.model.Item;
import com.foodexpress.exception.*;

public interface FoodCartService {

	//public FoodCart addFoodCart(FoodCart foodCart) throws FoodCartException;

	public List<FoodCart> viewAllFoodCarts(String key) throws FoodCartException, LoginException;

	public FoodCart addItemToCart(String key,Integer id ,String itemName) throws FoodCartException, ItemException, LoginException;

	public FoodCart increaseQuantity(String key,Integer itemId, Integer quantity)
			throws FoodCartException, ItemException, LoginException;

	public FoodCart reduceQuantity(String key,Integer itemId, Integer quantity) throws FoodCartException, ItemException, LoginException;

	public FoodCart removeItem(String key,Integer itemId) throws FoodCartException, ItemException, LoginException;

	public FoodCart clearCart(String key) throws FoodCartException, LoginException;

}
