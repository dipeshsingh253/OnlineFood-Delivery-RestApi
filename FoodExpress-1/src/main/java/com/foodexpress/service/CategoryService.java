package com.foodexpress.service;

import java.util.List;

import com.foodexpress.exception.CategoryException;
import com.foodexpress.model.Category;

public interface CategoryService {

	public Category addCategory(String category) throws CategoryException;

	public List<Category> viewAllCategories() throws CategoryException;

	public Category updateCategory(Category category) throws CategoryException;

	public Category removeCategoryById(Integer categoryId) throws CategoryException;

	public Category viewCategoryById(Integer categoryId) throws CategoryException;

}
