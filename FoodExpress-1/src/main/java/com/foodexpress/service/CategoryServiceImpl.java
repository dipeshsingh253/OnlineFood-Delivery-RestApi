package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.CategoryException;
import com.foodexpress.model.Category;
import com.foodexpress.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category addCategory(String category) throws CategoryException {

		Category save = categoryRepo.findByCategoryName(category);

		if (save != null) {
			throw new CategoryException("Category already exists");
		}

		Category addCategory = new Category();
		addCategory.setCategoryName(category);

		return categoryRepo.save(addCategory);
	}

	@Override
	public List<Category> viewAllCategories() throws CategoryException {

		List<Category> categories = categoryRepo.findAll();

		if (categories.size() == 0) {
			throw new CategoryException("No categories available");
		}

		return categories;
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {

		Optional<Category> optional = categoryRepo.findById(category.getCategoryId());

		if (optional.isEmpty()) {
			throw new CategoryException("No categories available  with given id");
		}

		return categoryRepo.save(optional.get());
	}

	@Override
	public Category removeCategoryById(Integer categoryId) throws CategoryException {

		Optional<Category> optional = categoryRepo.findById(categoryId);

		if (optional.isEmpty()) {
			throw new CategoryException("No categories available  with given id");
		}

		Category delete = optional.get();

		categoryRepo.delete(delete);
		return delete;
	}

	@Override
	public Category viewCategoryById(Integer categoryId) throws CategoryException {

		Optional<Category> optional = categoryRepo.findById(categoryId);

		if (optional.isEmpty()) {
			throw new CategoryException("No categories available  with given id");
		}

		return optional.get();
	}

}
