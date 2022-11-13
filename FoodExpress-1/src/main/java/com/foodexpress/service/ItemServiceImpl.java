package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.ItemException;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.Item;
import com.foodexpress.repo.CurrentUserSessionRepo;
import com.foodexpress.repo.ItemRepo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;
	
	
	@Autowired 
	private CurrentUserSessionRepo sessionRepo;
	
	@Override
	public Item addItem(String key, Item item) throws ItemException, LoginException {
		
		
	
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		Optional<Item> optional = itemRepo.findById(item.getItemId());

		if (optional.isPresent()) {
			throw new ItemException("Item already exists");
		}

		return itemRepo.save(item);

	}

	@Override
	public List<Item> viewAllItems(String key) throws ItemException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		
		List<Item> items = itemRepo.findAll();

		if (items.size() == 0) {
			throw new ItemException("No Items Available");
		}

		return items;
	}

	@Override
	public Item updateItem(String key, Item item) throws ItemException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		Optional<Item> optional = itemRepo.findById(item.getItemId());

		if (optional.isEmpty()) {
			throw new ItemException("No Item Available with given id");
		}

		return itemRepo.save(item);
	}

	@Override
	public Item removeItemById(String key, Integer itemId) throws ItemException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		Optional<Item> optional = itemRepo.findById(itemId);

		if (optional.isEmpty()) {
			throw new ItemException("No Item Available with given id");
		}

		Item delete = optional.get();

		itemRepo.delete(delete);

		return delete;
	}

}
