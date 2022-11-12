package com.foodyexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.foodyexpress.model.CustomerDTO;
import com.foodyexpress.model.FoodCart;

import com.foodyexpress.model.ItemDTO;
import com.foodyexpress.service.FoodCartService;

@RestController
@RequestMapping("/foodcart")
public class FoodCartController {

	@Autowired
	private FoodCartService foodCartService;

	@PostMapping("/addtocart/{customerId}")
	public ResponseEntity<FoodCart> addItemToCartHandler(@PathVariable("customerId") Integer customerId,
			@RequestBody ItemDTO itemDTO) throws ItemException, CustomerException {
		FoodCart foodCart = foodCartService.addItemToCart(customerId, itemDTO);
		return new ResponseEntity<FoodCart>(foodCart, HttpStatus.OK);
	}

	@PutMapping("/increaseQuantity")
	public ResponseEntity<FoodCart> increaseItemQuantityHandler(@RequestParam Integer cartId,
			@RequestParam Integer quantity, @RequestParam Integer itemId)
			throws ItemException, CustomerException, FoodCartException {
		FoodCart foodCart = foodCartService.increaseItemQuantity(cartId, quantity, itemId);
		return new ResponseEntity<FoodCart>(foodCart, HttpStatus.OK);
	}

	@PutMapping("/decreaseQuantity")
	public ResponseEntity<FoodCart> decreaseItemQuantityHandler(@RequestParam Integer cartId,
			@RequestParam Integer quantity, @RequestParam Integer itemId)
			throws ItemException, CustomerException, FoodCartException {
		FoodCart foodCart = foodCartService.decreaseItemQuantity(cartId, quantity, itemId);
		return new ResponseEntity<FoodCart>(foodCart, HttpStatus.OK);
	}

	@DeleteMapping("/item")
	public ResponseEntity<FoodCart> removeItemHandler(@RequestParam Integer cartId, @RequestParam Integer itemId)
			throws ItemException, CustomerException, FoodCartException {
		FoodCart foodCart = foodCartService.removeItem(cartId, itemId);
		return new ResponseEntity<FoodCart>(foodCart, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<FoodCart> clearCartHandler(@RequestParam Integer cartId)
			throws ItemException, CustomerException, FoodCartException {
		FoodCart foodCart = foodCartService.removeCart(cartId);
		return new ResponseEntity<FoodCart>(foodCart, HttpStatus.OK);
	}

}
