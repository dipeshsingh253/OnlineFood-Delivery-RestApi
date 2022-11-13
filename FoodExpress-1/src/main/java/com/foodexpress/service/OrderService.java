package com.foodexpress.service;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.foodexpress.exception.OrderDetailsException;
import com.foodexpress.model.OrderDetails;
import com.foodexpress.model.Customer;
import com.foodexpress.model.Restaurant;

public interface OrderService {

	public OrderDetails addOrder(String key, OrderDetails orderDetails) throws OrderDetailsException, LoginException;

	public List<OrderDetails> viewAllOrders(String key) throws OrderDetailsException,LoginException;


	public OrderDetails updateOrder(String key, OrderDetails order) throws OrderDetailsException, LoginException;

	public OrderDetails removeOrder(String key,Integer orderId) throws OrderDetailsException, LoginException;

	public OrderDetails viewOrder(String key,Integer orderId) throws OrderDetailsException, LoginException;

//	public List<OrderDetails> viewAllOrders(Restaurant res) throws OrderDetailsException;

//	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderDetailsException;

}
