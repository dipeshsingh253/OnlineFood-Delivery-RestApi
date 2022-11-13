package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.ItemException;
import com.foodexpress.model.Item;
import com.foodexpress.repo.ItemRepo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	@Override
	public Item addItem(Item item) throws ItemException {
		Optional<Item> optional = itemRepo.findById(item.getItemId());

		if (optional.isPresent()) {
			throw new ItemException("Item already exists");
		}

		return itemRepo.save(item);

	}

	@Override
	public List<Item> viewAllItems() throws ItemException {

		List<Item> items = itemRepo.findAll();

		if (items.size() == 0) {
			throw new ItemException("No Items Available");
		}

		return items;
	}

	@Override
	public Item updateItem(Item item) throws ItemException {

		Optional<Item> optional = itemRepo.findById(item.getItemId());

		if (optional.isEmpty()) {
			throw new ItemException("No Item Available with given id");
		}

		return itemRepo.save(item);
	}

	@Override
	public Item removeItemById(Integer itemId) throws ItemException {
		Optional<Item> optional = itemRepo.findById(itemId);

		if (optional.isEmpty()) {
			throw new ItemException("No Item Available with given id");
		}

		Item delete = optional.get();

		itemRepo.delete(delete);

		return delete;
	}

}
