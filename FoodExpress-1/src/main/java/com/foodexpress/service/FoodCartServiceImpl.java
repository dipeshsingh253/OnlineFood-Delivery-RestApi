package com.foodexpress.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.FoodCartException;
import com.foodexpress.exception.ItemException;
import com.foodexpress.model.FoodCart;
import com.foodexpress.model.Item;
import com.foodexpress.repo.FoodCartRepo;
import com.foodexpress.repo.ItemRepo;

@Service
public class FoodCartServiceImpl implements FoodCartService {

	@Autowired
	private FoodCartRepo foodCartRepo;

	@Autowired
	private ItemRepo itemRepo;

	@Override
	public FoodCart addFoodCart(FoodCart foodCart) throws FoodCartException {

		Optional<FoodCart> optional = foodCartRepo.findById(foodCart.getCartId());

		if (optional.isPresent()) {
			throw new FoodCartException("Cart alredy exists");
		}

		return foodCartRepo.save(foodCart);
	}

	@Override
	public List<FoodCart> viewAllFoodCarts() throws FoodCartException {

		List<FoodCart> foodCarts = foodCartRepo.findAll();

		if (foodCarts.size() == 0) {
			throw new FoodCartException("No carts Available");
		}

		return foodCarts;
	}

	@Override
	public FoodCart increaseQuantity(FoodCart cart, Item item, Integer quantity)
			throws FoodCartException, ItemException {

		Optional<FoodCart> optionalCart = foodCartRepo.findById(cart.getCartId());

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

			if (itm.getItemId() == item.getItemId()) {
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
	public FoodCart reduceQuantity(FoodCart cart, Item item, Integer quantity) throws FoodCartException, ItemException {

		Optional<FoodCart> optionalCart = foodCartRepo.findById(cart.getCartId());

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

			if (itm.getItemId() == item.getItemId()) {
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
	public FoodCart removeItem(FoodCart cart, Item item) throws FoodCartException, ItemException {

		Optional<FoodCart> optionalCart = foodCartRepo.findById(cart.getCartId());

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

			if (itm.getItemId() == item.getItemId()) {
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
	public FoodCart clearCart(FoodCart cart) throws FoodCartException {
		Optional<FoodCart> optionalCart = foodCartRepo.findById(cart.getCartId());

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
	public FoodCart addItemToCart(Integer id, Item item) throws FoodCartException, ItemException {
		
		
		
		Optional<FoodCart> optionalCart = foodCartRepo.findById(id);

		if (optionalCart.isEmpty()) {
			throw new FoodCartException("No carts Available");
		}
		
		FoodCart existedCart = optionalCart.get();
			
		Item existedItem = itemRepo.findByItemName(item.getItemName());
		
		if(existedItem == null) {
			throw new ItemException("Item does not exist");
		}
		
		existedCart.getItemList().add(item);
		
		
		return foodCartRepo.save(existedCart);
	}

}
