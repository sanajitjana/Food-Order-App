package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.OrderDetails;
import com.foodyexpress.model.Restaurant;

public interface OrderService {

	public OrderDetails addOrder(OrderDetails order);
	
	public OrderDetails updateOrder(OrderDetails order)throws OrderDetailsException;
	
	public OrderDetails removeOrder(int orderId)throws OrderDetailsException;

	public OrderDetails viewOrder(int orderId)throws OrderDetailsException;
	
	public List<OrderDetails> viewAllOrders(Restaurant res)throws OrderDetailsException;
	
	public List<OrderDetails> viewAllOrders(Customer customer)throws OrderDetailsException;
	
}
