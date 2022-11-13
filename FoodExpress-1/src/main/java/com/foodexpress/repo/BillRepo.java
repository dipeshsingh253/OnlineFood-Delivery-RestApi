package com.foodexpress.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodexpress.model.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill, Integer>{

}


