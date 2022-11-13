package com.foodexpress.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.foodexpress.exception.CategoryException;
import com.foodexpress.model.Category;

public interface CategoryService {

	public Category addCategory(String key,String category) throws CategoryException, LoginException;

	public List<Category> viewAllCategories(String key) throws CategoryException, LoginException;

	public Category updateCategory(String key,Category category) throws CategoryException, LoginException;

	public Category removeCategoryById(String key,Integer categoryId) throws CategoryException, LoginException;

	public Category viewCategoryById(String key,Integer categoryId) throws CategoryException, LoginException;

}
