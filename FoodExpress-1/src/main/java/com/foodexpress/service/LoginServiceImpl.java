package com.foodexpress.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.LoginException;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.Customer;
import com.foodexpress.model.LoginDto;
import com.foodexpress.repo.CurrentUserSessionRepo;
import com.foodexpress.repo.CustomerRepo;

import net.bytebuddy.utility.RandomString;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentUserSessionRepo currentUserSessionRepo;

	@Override
	public String loginToAccount(LoginDto logindto) throws LoginException {

		Customer customer = customerRepo.findByEmail(logindto.getEmail());

		if (customer == null) {
			throw new LoginException("enter proper email");
		}

		Optional<CurrentUserSession> validateSession = currentUserSessionRepo.findById(customer.getCustomerId());

		if (validateSession.isPresent()) {
			throw new LoginException("user already logged in");
		}

		if (customer.getPassword().equals(logindto.getPassword())) {

			String key = RandomString.make(6);
			CurrentUserSession currentUserSession = new CurrentUserSession(customer.getCustomerId(), key,
					LocalDateTime.now());

			currentUserSessionRepo.save(currentUserSession);

			return currentUserSession.toString();
		} else {
			throw new LoginException("Enter valid password");
		}
	}

	@Override
	public String logoutFromAccount(String key) throws LoginException {
		
		CurrentUserSession currentUserSession = currentUserSessionRepo.findByKey(key);	
		
		if (currentUserSession==null) {
			throw new LoginException("user not logged in");
		}
		
		currentUserSessionRepo.delete(currentUserSession);
		
		return "Logged out ...!";
	}


}
