package com.foodyexpress.service;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.FoodCartException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.CustomerDTO;
import com.foodyexpress.model.FoodCart;
import com.foodyexpress.model.ItemDTO;

public interface FoodCartService {
	
	public FoodCart addItemToCart(CustomerDTO customerDTO, ItemDTO itemDTO) throws ItemException, CustomerException;
	
	public FoodCart increaseItemQuantity(Integer cartId, Integer quantity, Integer itemId) throws FoodCartException, ItemException;

	public FoodCart decreaseItemQuantity(Integer cartId, Integer quantity, Integer itemId) throws FoodCartException, ItemException;
	
	public FoodCart removeItem(Integer cartId, Integer itemId) throws FoodCartException, ItemException;
	
	public FoodCart removeCart(Integer cartId) throws FoodCartException;
}
