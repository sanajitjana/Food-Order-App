package com.foodyexpress.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.FoodCartException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.FoodCart;
import com.foodyexpress.model.Item;
import com.foodyexpress.model.OrderDetails;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.repository.CustomerRepo;
import com.foodyexpress.repository.OrderDetailsRepo;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDetailsRepo orderRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	
	@Override
	public OrderDetails addOrder(Integer customerId) throws CustomerException, FoodCartException{
		Optional<Customer> opt=customerRepo.findById(customerId);
		if(opt.isEmpty())throw new CustomerException("customer does not exist..!");
		Customer customer=opt.get();
		FoodCart foodCart=customer.getCart();
		List<Item>itemList=foodCart.getItemList();
		if(itemList.isEmpty())throw new FoodCartException("cart is empty..!");
		List<OrderDetails> orderDetailsList=orderRepo.findAll();
		boolean flag=true;
		OrderDetails orderDetails=null;
		for(int i=0;i<orderDetailsList.size();i++)
		{
			OrderDetails exOrderDetails=orderDetailsList.get(i);
			if(exOrderDetails.getFoodCart().getCartId()==foodCart.getCartId()&&exOrderDetails.getOrderStatus().equals("Pending"))
			{
				exOrderDetails.setFoodCart(foodCart);
				orderDetails=exOrderDetails;
				flag=false;
			}
		}
		
		if(flag)
		{
		orderDetails=new OrderDetails();
		orderDetails.setFoodCart(foodCart);
		orderDetails.setOrderDate(LocalDateTime.now());
		orderDetails.setOrderStatus("Pending");
		}
		orderRepo.save(orderDetails);
		return orderDetails;
		
			
	}

	@Override
	public OrderDetails updateOrder(Integer orderId,Integer customerId) throws OrderDetailsException, CustomerException, FoodCartException {
		Optional<OrderDetails> opt1=orderRepo.findById(orderId);
		if(opt1.isPresent())
		{
			Optional<Customer> opt=customerRepo.findById(customerId);
			if(opt.isEmpty())throw new CustomerException("customer does not exist..!");
			Customer customer=opt.get();
			FoodCart foodCart=customer.getCart();
			List<Item>itemList=foodCart.getItemList();
			if(itemList.isEmpty())throw new FoodCartException("cart is empty..!");
			List<OrderDetails> orderDetailsList=orderRepo.findAll();
			boolean flag=true;
			OrderDetails orderDetails=null;
			for(int i=0;i<orderDetailsList.size();i++)
			{
				OrderDetails exOrderDetails=orderDetailsList.get(i);
				if(exOrderDetails.getFoodCart().getCartId()==foodCart.getCartId())
				{
					exOrderDetails.setFoodCart(foodCart);
					orderDetails=exOrderDetails;
					flag=false;
				}
			}
			
			if(flag)
			{
			orderDetails=new OrderDetails();
			orderDetails.setFoodCart(foodCart);
			orderDetails.setOrderDate(LocalDateTime.now());
			orderDetails.setOrderStatus("pending..!");
			}
			orderRepo.save(orderDetails);
			return orderDetails;
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
