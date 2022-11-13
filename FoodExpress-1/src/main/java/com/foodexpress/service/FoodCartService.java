package com.foodexpress.service;

import java.util.List;

import com.foodexpress.exception.FoodCartException;
import com.foodexpress.model.FoodCart;
import com.foodexpress.model.Item;
import com.foodexpress.exception.*;

public interface FoodCartService {

	public FoodCart addFoodCart(FoodCart foodCart) throws FoodCartException;

	public List<FoodCart> viewAllFoodCarts() throws FoodCartException;

	public FoodCart addItemToCart(Integer id ,Item item) throws FoodCartException, ItemException;

	public FoodCart increaseQuantity(FoodCart cart, Item item, Integer quantity)
			throws FoodCartException, ItemException;

	public FoodCart reduceQuantity(FoodCart cart, Item item, Integer quantity) throws FoodCartException, ItemException;

	public FoodCart removeItem(FoodCart cart, Item item) throws FoodCartException, ItemException;

	public FoodCart clearCart(FoodCart foodCart) throws FoodCartException;

}
