package com.foodexpress.service;

import com.foodexpress.exception.LoginException;
import com.foodexpress.model.LoginDto;

public interface LoginService {
	
	public String loginToAccount (LoginDto logindto) throws LoginException;
	
	public String logoutFromAccount (String key) throws LoginException;

}
