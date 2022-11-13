package com.foodexpress.service;

import java.util.List;

import com.foodexpress.exception.ItemException;
import com.foodexpress.model.Item;
import com.foodexpress.exception.CategoryException;
import com.foodexpress.model.Category;

public interface ItemService {

	public Item addItem(Item item) throws ItemException;

	public List<Item> viewAllItems() throws ItemException;

	public Item updateItem(Item item) throws ItemException;

//	public Item removeItem(Item item) throws ItemException;

	public Item removeItemById(Integer itemId) throws ItemException;

//	public List<Item> getAllItemByCategory(Category category) throws ItemException, CategoryException;
//
//	public List<Item> getAllItemByCategoryName(String categoryName) throws ItemException, CategoryException;

}
