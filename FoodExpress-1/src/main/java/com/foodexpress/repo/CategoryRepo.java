package com.foodexpress.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodexpress.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	
	public Category findByCategoryName(String name);
	
}
