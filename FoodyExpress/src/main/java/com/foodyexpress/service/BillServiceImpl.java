package com.foodyexpress.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.foodyexpress.exception.BillException;
import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Bill;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Item;
import com.foodyexpress.repository.BillDao;
import com.foodyexpress.repository.CustomerRepo;

import antlr.collections.List;

public class BillServiceImpl implements BillService {

	@Autowired
	private BillDao billRepo;

	@Autowired
	CustomerRepo cusDAO;

	@Override
	public Bill addBill(Bill bill) throws BillException {
		Optional<Bill> opt = billRepo.findById(bill.getBillId());
		if (opt.isPresent()) {
			throw new BillException("Bill already exists..");
		} else {
			return billRepo.save(bill);
		}
	}

	@Override
	public Bill updateBill(Bill bill) throws BillException {
		Optional<Bill> opt = billRepo.findById(bill.getBillId());
		if (opt.isPresent()) {
			return billRepo.save(bill);
		} else {
			throw new BillException("Bill doesn't exists..");
		}
	}

	@Override
	public Bill removeBill(Integer billId) throws BillException {
		Optional<Bill> opt = billRepo.findById(billId);
		if (opt.isPresent()) {
			Bill bill = opt.get();
			billRepo.delete(bill);
			return bill;
		} else {
			throw new BillException("Bill not found with ID: " + billId);
		}

	}

	@Override
	public Bill viewBill(Integer billId) throws BillException {
		Optional<Bill> opt = billRepo.findById(billId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new BillException("Bill not found with ID: " + billId);
		}
	}

}
