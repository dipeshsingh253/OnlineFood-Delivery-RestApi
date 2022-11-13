package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.exception.OrderDetailsException;
import com.foodexpress.model.Item;
import com.foodexpress.model.OrderDetails;
import com.foodexpress.repo.OrderDetailsRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailsRepo orderDetailsRepo;

	@Override
	public OrderDetails addOrder(OrderDetails orderDetails) throws OrderDetailsException {
		Optional<OrderDetails> optional = orderDetailsRepo.findById(orderDetails.getOrderId());

		if (optional.isPresent()) {
			throw new OrderDetailsException("order already exists");
		}

		return orderDetailsRepo.save(orderDetails);
	}

	@Override
	public List<OrderDetails> viewAllOrders() throws OrderDetailsException {
		List<OrderDetails> orderDetails = orderDetailsRepo.findAll();

		if (orderDetails.size() == 0) {
			throw new OrderDetailsException("no orders availale");
		}

		return orderDetails;
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) throws OrderDetailsException {

		Optional<OrderDetails> optional = orderDetailsRepo.findById(order.getOrderId());

		if (optional.isEmpty()) {
			throw new OrderDetailsException("no order availale with given id");
		}

		return orderDetailsRepo.save(optional.get());
	}

	@Override
	public OrderDetails removeOrder(Integer orderId) throws OrderDetailsException {

		Optional<OrderDetails> optional = orderDetailsRepo.findById(orderId);

		if (optional.isEmpty()) {
			throw new OrderDetailsException("no order availale with given id");
		}

		OrderDetails delete = optional.get();

		orderDetailsRepo.delete(delete);

		return delete;
	}

	@Override
	public OrderDetails viewOrder(Integer orderId) throws OrderDetailsException {

		Optional<OrderDetails> optional = orderDetailsRepo.findById(orderId);

		if (optional.isEmpty()) {
			throw new OrderDetailsException("no order availale with given id");
		}

		return optional.get();
	}

}
