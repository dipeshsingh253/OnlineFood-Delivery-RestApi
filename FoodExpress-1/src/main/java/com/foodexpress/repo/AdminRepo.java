package com.foodexpress.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodexpress.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>{
	public Admin findByMail(String mail);
}
