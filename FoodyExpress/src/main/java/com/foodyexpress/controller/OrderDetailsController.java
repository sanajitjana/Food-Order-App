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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.FoodCartException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.OrderDetails;
import com.foodyexpress.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderDetailsController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/add/{customerId}")
	public ResponseEntity<OrderDetails> saveOrderDetails(@PathVariable("customerId")Integer customerId) throws CustomerException, FoodCartException {
		OrderDetails savedOrder = orderService.addOrder(customerId);
		return new ResponseEntity<OrderDetails>(savedOrder, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	public ResponseEntity<OrderDetails> updateOrderDetails(@RequestParam Integer orderId,@RequestParam Integer customerId)
			throws OrderDetailsException, CustomerException, FoodCartException {
		OrderDetails updatedOrderDetails = orderService.updateOrder(orderId,customerId);
		return new ResponseEntity<OrderDetails>(updatedOrderDetails, HttpStatus.OK);
	}

	@DeleteMapping("/remove/{orderId}")
	public ResponseEntity<OrderDetails> removerOrderDetails(@PathVariable("orderId") Integer orderId)
			throws OrderDetailsException {
		OrderDetails deletedOrderDetails = orderService.removeOrder(orderId);
		return new ResponseEntity<OrderDetails>(deletedOrderDetails, HttpStatus.OK);
	}

	@GetMapping("/view/{orderId}")
	public ResponseEntity<OrderDetails> viewOrderDetails(@PathVariable("orderId") Integer orderId)
			throws OrderDetailsException {
		OrderDetails orderDetails = orderService.viewOrder(orderId);
		return new ResponseEntity<OrderDetails>(orderDetails, HttpStatus.OK);
	}

}
