package com.foodexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodexpress.exception.CategoryException;
import com.foodexpress.model.Category;
import com.foodexpress.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping("/category")
	public ResponseEntity<Category> add(@RequestParam String category) throws CategoryException {

		Category saved = categoryService.addCategory(category);

		return new ResponseEntity<Category>(saved, HttpStatus.ACCEPTED);

	}

	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAll() throws CategoryException {
		List<Category> categories = categoryService.viewAllCategories();

		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@PutMapping("/category")
	public ResponseEntity<Category> update(@RequestBody Category category) throws CategoryException {

		Category updated = categoryService.updateCategory(category);

		return new ResponseEntity<Category>(updated, HttpStatus.OK);
	}

	@DeleteMapping("/categories/{id}")
	public ResponseEntity<Category> delete(@PathVariable("id") Integer id) throws CategoryException {

		Category deleted = categoryService.removeCategoryById(id);

		return new ResponseEntity<Category>(deleted, HttpStatus.OK);
	}

	@GetMapping("/categories/{id}")
	public ResponseEntity<Category> getById(@PathVariable("id") Integer id) throws CategoryException {
		Category ans = categoryService.viewCategoryById(id);

		return new ResponseEntity<Category>(ans, HttpStatus.OK);
	}
}
