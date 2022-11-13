package com.foodexpress.service;

import java.util.List;

import com.foodexpress.exception.OrderDetailsException;
import com.foodexpress.model.OrderDetails;
import com.foodexpress.model.Customer;
import com.foodexpress.model.Restaurant;

public interface OrderService {

	public OrderDetails addOrder(OrderDetails orderDetails) throws OrderDetailsException;

	public List<OrderDetails> viewAllOrders() throws OrderDetailsException;


	public OrderDetails updateOrder(OrderDetails order) throws OrderDetailsException;

	public OrderDetails removeOrder(Integer orderId) throws OrderDetailsException;

	public OrderDetails viewOrder(Integer orderId) throws OrderDetailsException;

//	public List<OrderDetails> viewAllOrders(Restaurant res) throws OrderDetailsException;

//	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderDetailsException;

}
