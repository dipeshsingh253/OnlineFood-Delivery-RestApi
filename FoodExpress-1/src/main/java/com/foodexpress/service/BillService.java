package com.foodexpress.service;

import java.util.List;

import com.foodexpress.exception.BillException;
import com.foodexpress.model.Bill;
import com.foodexpress.exception.CustomerException;
import com.foodexpress.exception.LoginException;
import com.foodexpress.exception.OrderDetailsException;

public interface BillService {
	
	public Bill addBill(Bill bill) throws BillException, CustomerException, OrderDetailsException, LoginException;
	
	public List<Bill> viewAllBills() throws BillException;

	public Bill updateBill( Bill bill) throws BillException, OrderDetailsException, LoginException;

	public Bill removeBill( Integer billId) throws BillException, LoginException;

	public Bill viewBill( Integer billId) throws BillException, LoginException;
	
}
