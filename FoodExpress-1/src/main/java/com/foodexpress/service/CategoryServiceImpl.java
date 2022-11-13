package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.CategoryException;
import com.foodexpress.model.Category;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.repo.CategoryRepo;
import com.foodexpress.repo.*;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private CurrentUserSessionRepo sessionRepo;

	@Override
	public Category addCategory(String key, String category) throws CategoryException, LoginException {
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}

		if (user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}

		Category save = categoryRepo.findByCategoryName(category);

		if (save != null) {
			throw new CategoryException("Category already exists");
		}

		Category addCategory = new Category();
		addCategory.setCategoryName(category);

		return categoryRepo.save(addCategory);
	}

	@Override
	public List<Category> viewAllCategories(String key) throws CategoryException, LoginException {
		
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		List<Category> categories = categoryRepo.findAll();

		if (categories.size() == 0) {
			throw new CategoryException("No categories available");
		}

		return categories;
	}

	@Override
	public Category updateCategory(String key, Category category) throws CategoryException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		Optional<Category> optional = categoryRepo.findById(category.getCategoryId());

		if (optional.isEmpty()) {
			throw new CategoryException("No categories available  with given id");
		}

		return categoryRepo.save(category);
	}

	@Override
	public Category removeCategoryById(String key, Integer categoryId) throws CategoryException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		Optional<Category> optional = categoryRepo.findById(categoryId);

		if (optional.isEmpty()) {
			throw new CategoryException("No categories available  with given id");
		}

		Category delete = optional.get();

		categoryRepo.delete(delete);
		return delete;
	}

	@Override
	public Category viewCategoryById(String key, Integer categoryId) throws CategoryException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		if(user.getRole().equalsIgnoreCase("customer")) {
			throw new LoginException("You are logged in as customer, You can not perfom this action");
		}
		
		Optional<Category> optional = categoryRepo.findById(categoryId);

		if (optional.isEmpty()) {
			throw new CategoryException("No categories available  with given id");
		}

		return optional.get();
	}

}
