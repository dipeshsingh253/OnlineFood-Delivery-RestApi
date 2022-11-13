package com.foodexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodexpress.exception.LoginException;
import com.foodexpress.model.LoginDto;
import com.foodexpress.service.LoginService;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<String> signIn(@RequestBody LoginDto logindto) throws LoginException {

		String message = loginService.loginToAccount(logindto);

		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> signOut(@RequestParam(required = false) String key) throws LoginException {
		return new ResponseEntity<String>(loginService.logoutFromAccount(key), HttpStatus.ACCEPTED);
	}

}
