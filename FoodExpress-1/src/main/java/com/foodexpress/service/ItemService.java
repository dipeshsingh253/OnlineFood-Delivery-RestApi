package com.foodexpress.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.foodexpress.exception.ItemException;
import com.foodexpress.model.Item;
import com.foodexpress.exception.CategoryException;
import com.foodexpress.model.Category;

public interface ItemService {

	public Item addItem(String key, Item item) throws ItemException, LoginException;

	public List<Item> viewAllItems(String key) throws ItemException, LoginException;

	public Item updateItem(String key, Item item) throws ItemException, LoginException;

//	public Item removeItem(Item item) throws ItemException;

	public Item removeItemById(String key, Integer itemId) throws ItemException, LoginException;

//	public List<Item> getAllItemByCategory(Category category) throws ItemException, CategoryException;
//
//	public List<Item> getAllItemByCategoryName(String categoryName) throws ItemException, CategoryException;

}
