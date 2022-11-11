package com.foodyexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.OrderDetails;
import com.foodyexpress.service.OrderService;

@RestController
public class OrderDetailsController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orderdetails")
	public ResponseEntity<OrderDetails> saveOrderDetails(@RequestBody OrderDetails order)
	{
		OrderDetails savedOrder=orderService.addOrder(order);
		return new ResponseEntity<OrderDetails>(savedOrder,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/orderdetails")
	public ResponseEntity<OrderDetails> updateOrderDetails(@RequestBody OrderDetails order) throws OrderDetailsException
	{
		OrderDetails updatedOrderDetails=orderService.updateOrder(order);
		return new ResponseEntity<OrderDetails> (updatedOrderDetails,HttpStatus.OK);
	}
	
	@DeleteMapping("/orderdetails/{orderId}")
	public ResponseEntity<OrderDetails> removerOrderDetails(@PathVariable("orderId") Integer orderId) throws OrderDetailsException
	{
		OrderDetails deletedOrderDetails=orderService.removeOrder(orderId);
		return new ResponseEntity<OrderDetails>(deletedOrderDetails,HttpStatus.OK);
	}
	
	@GetMapping("/orderdetails/{orderId}")
	public ResponseEntity<OrderDetails> viewOrderDetails(@PathVariable("orderId") Integer orderId) throws OrderDetailsException
	{
		OrderDetails orderDetails=orderService.viewOrder(orderId);
		return new ResponseEntity<OrderDetails>(orderDetails,HttpStatus.OK);
	}
	
	
	
}
