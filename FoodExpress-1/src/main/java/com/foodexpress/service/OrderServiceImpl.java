package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.OrderDetailsException;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.Item;
import com.foodexpress.model.OrderDetails;
import com.foodexpress.repo.OrderDetailsRepo;
import com.foodexpress.repo.*;
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailsRepo orderDetailsRepo;
	
	
	@Autowired 
	private CurrentUserSessionRepo sessionRepo;
	
	@Override
	public OrderDetails addOrder(String key,OrderDetails orderDetails) throws OrderDetailsException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		Optional<OrderDetails> optional = orderDetailsRepo.findById(orderDetails.getOrderId());

		if (optional.isPresent()) {
			throw new OrderDetailsException("order already exists");
		}

		return orderDetailsRepo.save(orderDetails);
	}

	@Override
	public List<OrderDetails> viewAllOrders(String key) throws OrderDetailsException, LoginException {
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
	
		List<OrderDetails> orderDetails = orderDetailsRepo.findAll();

		if (orderDetails.size() == 0) {
			throw new OrderDetailsException("no orders availale");
		}

		return orderDetails;
	}

	@Override
	public OrderDetails updateOrder(String key,OrderDetails order) throws OrderDetailsException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		

		
		Optional<OrderDetails> optional = orderDetailsRepo.findById(order.getOrderId());

		if (optional.isEmpty()) {
			throw new OrderDetailsException("no order availale with given id");
		}

		return orderDetailsRepo.save(optional.get());
	}

	@Override
	public OrderDetails removeOrder(String key,Integer orderId) throws OrderDetailsException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}

		
		Optional<OrderDetails> optional = orderDetailsRepo.findById(orderId);

		if (optional.isEmpty()) {
			throw new OrderDetailsException("no order availale with given id");
		}

		OrderDetails delete = optional.get();

		orderDetailsRepo.delete(delete);

		return delete;
	}

	@Override
	public OrderDetails viewOrder(String key,Integer orderId) throws OrderDetailsException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}

		Optional<OrderDetails> optional = orderDetailsRepo.findById(orderId);

		if (optional.isEmpty()) {
			throw new OrderDetailsException("no order availale with given id");
		}

		return optional.get();
	}

}
