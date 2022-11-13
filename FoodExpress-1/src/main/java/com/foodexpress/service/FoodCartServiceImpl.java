package com.foodexpress.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.FoodCartException;
import com.foodexpress.exception.ItemException;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.FoodCart;
import com.foodexpress.model.Item;
import com.foodexpress.repo.FoodCartRepo;
import com.foodexpress.repo.ItemRepo;
import com.foodexpress.repo.*;
@Service
public class FoodCartServiceImpl implements FoodCartService {

	@Autowired
	private FoodCartRepo foodCartRepo;

	@Autowired
	private ItemRepo itemRepo;
	
	
	@Autowired 
	private CurrentUserSessionRepo sessionRepo;

//	@Override
//	public FoodCart addFoodCart(FoodCart foodCart) throws FoodCartException {
//
//		Optional<FoodCart> optional = foodCartRepo.findById(foodCart.getCartId());
//
//		if (optional.isPresent()) {
//			throw new FoodCartException("Cart alredy exists");
//		}
//
//		return foodCartRepo.save(foodCart);
//	}

	@Override
	public List<FoodCart> viewAllFoodCarts(String key) throws FoodCartException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		
		List<FoodCart> foodCarts = foodCartRepo.findAll();

		if (foodCarts.size() == 0) {
			throw new FoodCartException("No carts Available");
		}

		return foodCarts;
	}

	@Override
	public FoodCart increaseQuantity(String key, Integer itemId, Integer quantity)
			throws FoodCartException, ItemException, LoginException {
		
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}

		
		Optional<FoodCart> optionalCart = foodCartRepo.findById(0);

		if (optionalCart.isEmpty()) {
			throw new FoodCartException("No carts Available");
		}

		FoodCart existedCart = optionalCart.get();

		if (existedCart.getItemList().size() == 0) {
			throw new FoodCartException("No items available in cart...Cart is empty");
		}

		boolean found = false;

		List<Item> items = existedCart.getItemList();
		
		Item updetedItem = new Item();
		updetedItem.setItemId(itemId);
		
		
		for (int i = 0; i < items.size(); i++) {

			Item itm = items.get(i);

			if (itm.getItemId() == itemId) {
				found = true;

				itm.setQuantity(Math.abs(itm.getQuantity() - quantity));

				updetedItem = itemRepo.save(updetedItem);

			}
		}

		if (!found) {
			throw new ItemException("Item not found for given id ");
		}

		return foodCartRepo.save(existedCart);
	}

	@Override
	public FoodCart reduceQuantity( String key,Integer itemId, Integer quantity) throws FoodCartException, ItemException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		

		Optional<FoodCart> optionalCart = foodCartRepo.findById(0);

		if (optionalCart.isEmpty()) {
			throw new FoodCartException("No carts Available");
		}

		FoodCart existedCart = optionalCart.get();

		if (existedCart.getItemList().size() == 0) {
			throw new FoodCartException("No items available in cart...Cart is empty");
		}

		boolean found = false;

		List<Item> items = existedCart.getItemList();

		for (int i = 0; i < items.size(); i++) {

			Item itm = items.get(i);

			if (itm.getItemId() == itemId) {
				found = true;

				itm.setQuantity(Math.abs(itm.getQuantity() - quantity));

//				itemRepo.save(item);

			}
		}

		if (!found) {
			throw new ItemException("Item not found for given id ");
		}

		return foodCartRepo.save(existedCart);
	}

	@Override
	public FoodCart removeItem(String key,Integer itemId) throws FoodCartException, ItemException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		
		Optional<FoodCart> optionalCart = foodCartRepo.findById(0);

		if (optionalCart.isEmpty()) {
			throw new FoodCartException("No carts Available");
		}
		
		FoodCart existedCart = optionalCart.get();

		if (existedCart.getItemList().size() == 0) {
			throw new FoodCartException("No items available in cart...Cart is empty");
		}


		boolean found = false;

		List<Item> items = existedCart.getItemList();

		for (int i = 0; i < items.size(); i++) {

			Item itm = items.get(i);

			if (itm.getItemId() == itemId) {
				found = true;

				items.remove(i);
//				itemRepo.save(item);

			}
		}

		if (!found) {
			throw new ItemException("Item not found for given id ");
		}
		
		return foodCartRepo.save(existedCart);
	}

	@Override
	public FoodCart clearCart(String key) throws FoodCartException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		
		Optional<FoodCart> optionalCart = foodCartRepo.findById(1);

		if (optionalCart.isEmpty()) {
			throw new FoodCartException("No carts Available");
		}
		
		FoodCart existedCart = optionalCart.get();

		if (existedCart.getItemList().size() == 0) {
			throw new FoodCartException("No items available in cart...Cart is empty");
		}


		List<Item> items = existedCart.getItemList();
		
		items.clear();
	
		return foodCartRepo.save(existedCart);
	}

	@Override
	public FoodCart addItemToCart(String key, Integer id, String itemName) throws FoodCartException, ItemException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		Optional<FoodCart> optionalCart = foodCartRepo.findById(id);

		if (optionalCart.isEmpty()) {
			throw new FoodCartException("No carts Available");
		}
		
		FoodCart existedCart = optionalCart.get();
			
		Item existedItem = itemRepo.findByItemName(itemName);
		
		if(existedItem == null) {
			throw new ItemException("Item does not exist");
		}
		
		Item newItem = new Item();
		newItem.setItemName(itemName);
		
		Item saveItem = itemRepo.save(newItem);
		
		existedCart.getItemList().add(saveItem);
		
		
		return foodCartRepo.save(existedCart);
	}

}
