package com.foodyexpress.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.BillException;
import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.Bill;
import com.foodyexpress.model.CurrentUserSession;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.FoodCart;
import com.foodyexpress.model.Item;
import com.foodyexpress.model.OrderDetails;
import com.foodyexpress.repository.BillRepo;
import com.foodyexpress.repository.CurrentUserSessionRepo;
import com.foodyexpress.repository.CustomerRepo;
import com.foodyexpress.repository.FoodCartRepo;
import com.foodyexpress.repository.OrderDetailsRepo;

import antlr.collections.List;

@Service
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepo billRepo;

	@Autowired
	private OrderDetailsRepo orderDetailRepo;

	@Autowired
	private CustomerRepo cusDAO;

	@Autowired
	private FoodCartRepo foodCartRepo;

	@Autowired
	private CurrentUserSessionRepo currSession;

	@Override
	public Bill generateBill(String key, Integer customerId, Integer orderDetailId)
			throws CustomerException, OrderDetailsException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<OrderDetails> opt = orderDetailRepo.findById(orderDetailId);
		if (opt.isEmpty())
			throw new OrderDetailsException("order details not found ...");
		Optional<Customer> customerOpt = cusDAO.findById(customerId);
		if (customerOpt.isEmpty())
			throw new CustomerException("customer does not exist");
		Bill bill = new Bill();
		OrderDetails orderDetails = opt.get();
		FoodCart foodCart = orderDetails.getFoodCart();
		Double totalCost = 0D;
		Integer totalItems = 0;
		for (int i = 0; i < foodCart.getItemList().size(); i++) {
			Item items = foodCart.getItemList().get(i);
			totalCost = totalCost + (items.getQuantity() * items.getCost());
			totalItems = totalItems + items.getQuantity();
		}
		bill.setTotalCost(totalCost);
		bill.setTotalItem(totalItems);
		bill.setBillDate(LocalDateTime.now());
		bill.setOrder(orderDetails);
		billRepo.save(bill);
		orderDetails.setOrderStatus("completed");
		foodCart.getItemList().clear();
//		foodCartRepo.save(foodCart);

		return bill;

	}

	@Override
	public Bill updateBill(String key, Bill bill) throws BillException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<Bill> opt = billRepo.findById(bill.getBillId());
		if (opt.isPresent()) {
			return billRepo.save(bill);
		} else {
			throw new BillException("Bill doesn't exists..");
		}
	}

	@Override
	public Bill removeBill(String key, Integer billId) throws BillException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

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
	public Bill viewBill(String key, Integer billId) throws BillException, LoginException {

		CurrentUserSession currSess = currSession.findByPrivateKey(key);
		if (currSess == null)
			throw new LoginException("Login required");

		Optional<Bill> opt = billRepo.findById(billId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new BillException("Bill not found with ID: " + billId);
		}
	}

}
