package com.foodexpress.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodexpress.model.Admin;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.LoginDto;
import com.foodexpress.repo.*;

import net.bytebuddy.utility.RandomString;
@RestController
public class AdminSessionController {
	
	@Autowired
	private CurrentUserSessionRepo sessionRepo;
	
	@Autowired 
	private AdminRepo adminRepo;
	
	@PostMapping("/signup/admin")
	public ResponseEntity<String> addAdmin(){
		
		Admin admin = new Admin();
		admin.setMail("admin@mail.com");
		admin.setPassword("admin");
		
		Admin saved = adminRepo.save(admin);
		
//		CurrentUserSession session = new CurrentUserSession();
//		session.setKey(RandomString.make(6));
//		session.setLocalDateTime(LocalDateTime.now());
//		session.setRole("admin");
//		session.setUserId(saved.getId());
//		CurrentUserSession savedSession = sessionRepo.save(session);
		return new ResponseEntity<String>(saved.toString(),HttpStatus.ACCEPTED);
		
		
		
	}
	
}
