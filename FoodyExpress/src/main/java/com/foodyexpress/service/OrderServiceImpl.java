package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.OrderDetails;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.repository.OrderDetailsRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailsRepo orderRepo;
	
	
	@Override
	public OrderDetails addOrder(OrderDetails order){
		
		OrderDetails savedOrder=orderRepo.save(order);
		return savedOrder;	
	}

	@Override
	public OrderDetails updateOrder(OrderDetails order) throws OrderDetailsException {
		Optional<OrderDetails> opt=orderRepo.findById(order.getOrderId());
		if(opt.isPresent())
		{
			OrderDetails updatedOrder=orderRepo.save(order);
			return updatedOrder;
		}
		else
		{
			throw new OrderDetailsException("order does not exist...!");
		}
	}

	@Override
	public OrderDetails removeOrder(int orderId) throws OrderDetailsException {
		Optional<OrderDetails> opt=orderRepo.findById(orderId);
		if(opt.isPresent())
		{
			OrderDetails deletedOrder=opt.get();
			orderRepo.delete(deletedOrder);
			return deletedOrder;
		}
		else
		{
			throw new OrderDetailsException("order does not exist...!");
		}
	}
	
	@Override
	public OrderDetails viewOrder(int orderId) throws OrderDetailsException {
		Optional<OrderDetails> opt=orderRepo.findById(orderId);
		if(opt.isPresent())
		{
			OrderDetails order=opt.get();
			return order;
		}
		else
		{
			throw new OrderDetailsException("order does not exist...!");
		}
	}

	

	@Override
	public List<OrderDetails> viewAllOrders(Restaurant res) throws OrderDetailsException {
		
		return null;
	}

	@Override
	public List<OrderDetails> viewAllOrders(Customer customer) throws OrderDetailsException {
		
		return null;
	}

	

	

}
