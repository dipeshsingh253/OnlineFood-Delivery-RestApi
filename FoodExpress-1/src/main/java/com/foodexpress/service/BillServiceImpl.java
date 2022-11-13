package com.foodexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodexpress.model.Bill;
import com.foodexpress.model.CurrentUserSession;
import com.foodexpress.model.OrderDetails;
import com.foodexpress.repo.BillRepo;
import com.foodexpress.repo.OrderDetailsRepo;
import com.foodexpress.exception.*;

import com.foodexpress.repo.*;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepo billRepo;

	@Autowired
	private OrderDetailsRepo orderDetailsRepo;
	
	
	@Autowired 
	private CurrentUserSessionRepo sessionRepo;

	@Override
	public Bill addBill(String key,Bill bill) throws BillException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		
		Optional<Bill> optional = billRepo.findById(bill.getBillId());

		if (optional.isPresent()) {
			throw new BillException("Bill already exists");
		}

		return billRepo.save(bill);
	}

	@Override
	public List<Bill> viewAllBills(String key) throws BillException {

		List<Bill> bills = billRepo.findAll();

		if (bills.size() == 0) {
			throw new BillException("No Bills Found");
		}

		return bills;
	}

	@Override
	public Bill updateBill(String key,Bill bill) throws BillException, OrderDetailsException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		Optional<Bill> optionalBill = billRepo.findById(bill.getBillId());

		if (optionalBill.isEmpty()) {
			throw new BillException("Bill does not exist");
		}
		Bill checkBill = optionalBill.get();

//		Optional<OrderDetails> checkOrder = orderDetailsRepo.findById(checkBill.getOrder().getOrderId());
//		
//		if (checkOrder.isEmpty()) {
//			throw new OrderDetailsException("No Orders available");
//		}
//		
		return billRepo.save(checkBill);
	}

	@Override
	public Bill removeBill(String key,Integer billId) throws BillException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}

		
		Optional<Bill> optionalBill = billRepo.findById(billId);

		if (optionalBill.isEmpty()) {
			throw new BillException("Bill does not exist");
		}
		
		Bill delete = optionalBill.get();

		billRepo.delete(delete);
		
		return delete;
	}

	@Override
	public Bill viewBill(String key,Integer billId) throws BillException, LoginException {
		
		CurrentUserSession user = sessionRepo.findByKey(key);

		if (user == null) {
			throw new LoginException("Login Required");
		}
		
		Optional<Bill> optionalBill = billRepo.findById(billId);

		if (optionalBill.isEmpty()) {
			throw new BillException("Bill does not exist");
		}
		
		return optionalBill.get();
	}

}
