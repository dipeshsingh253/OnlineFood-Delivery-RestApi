package com.foodexpress.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

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
	public ResponseEntity<Category> add(@RequestParam String key,@RequestParam String category) throws CategoryException, LoginException {

		Category saved = categoryService.addCategory(key,category);

		return new ResponseEntity<Category>(saved, HttpStatus.ACCEPTED);

	}

	@GetMapping("/category")
	public ResponseEntity<List<Category>> getAll(@RequestParam String key) throws CategoryException, LoginException {
		List<Category> categories = categoryService.viewAllCategories(key);

		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}

	@PutMapping("/category")
	public ResponseEntity<Category> update(@RequestParam String key,@RequestBody Category category) throws CategoryException, LoginException {

		Category updated = categoryService.updateCategory(key,category);

		return new ResponseEntity<Category>(updated, HttpStatus.OK);
	}

	@DeleteMapping("/categories")
	public ResponseEntity<Category> delete(@RequestParam String key,@RequestParam Integer id) throws CategoryException, LoginException {

		Category deleted = categoryService.removeCategoryById(key,id);

		return new ResponseEntity<Category>(deleted, HttpStatus.OK);
	}

	@GetMapping("/categories")
	public ResponseEntity<Category> getById(@RequestParam String key,@RequestParam Integer id) throws CategoryException, LoginException {
		Category ans = categoryService.viewCategoryById(key,id);

		return new ResponseEntity<Category>(ans, HttpStatus.OK);
	}
}
