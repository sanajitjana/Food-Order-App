package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.exception.OrderHistoryException;
import com.foodyexpress.model.OrderHistory;

public interface OrderHistoryService {

	public OrderHistory getOrderHistoryById(String key, Integer orderHisId)
			throws OrderHistoryException, LoginException;

	public List<OrderHistory> getOrderHistoryByCustomerId(String key, Integer customerId)
			throws OrderHistoryException, LoginException, CustomerException;

	public List<OrderHistory> getAllOrderHistory(String key) throws OrderHistoryException, LoginException;

}
