package com.foodyexpress.service;

import com.foodyexpress.exception.BillException;
import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.Bill;

public interface BillService {

	public Bill generateBill(Integer customerId,Integer orderDetailId) throws BillException, CustomerException, OrderDetailsException;

	public Bill updateBill(Bill bill) throws BillException;

	public Bill removeBill(Integer billId) throws BillException;

	public Bill viewBill(Integer billId) throws BillException;

}
