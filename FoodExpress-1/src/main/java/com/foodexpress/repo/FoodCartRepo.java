package com.foodexpress.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodexpress.model.FoodCart;

public interface FoodCartRepo extends JpaRepository<FoodCart, Integer>{

}
