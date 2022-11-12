package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.FoodCartException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.OrderDetails;
import com.foodyexpress.model.Restaurant;

public interface OrderService {

	public OrderDetails addOrder(Integer customerId) throws CustomerException, FoodCartException;
	
	public OrderDetails updateOrder(Integer orderId,Integer customerId) throws OrderDetailsException, CustomerException, FoodCartException;
	
	public OrderDetails removeOrder(int orderId)throws OrderDetailsException;

	public OrderDetails viewOrder(int orderId)throws OrderDetailsException;
	
	public List<OrderDetails> viewAllOrders(Restaurant res)throws OrderDetailsException;
	
	public List<OrderDetails> viewAllOrders(Customer customer)throws OrderDetailsException;
	
}
