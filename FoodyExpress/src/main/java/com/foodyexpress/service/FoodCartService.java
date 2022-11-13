package com.foodyexpress.service;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.FoodCartException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.CustomerDTO;
import com.foodyexpress.model.FoodCart;
import com.foodyexpress.model.ItemDTO;

public interface FoodCartService {
	
	public FoodCart addItemToCart(String key, Integer customerId, Integer itemId) throws ItemException, CustomerException, LoginException;
	
	public FoodCart increaseItemQuantity(String key, Integer cartId, Integer quantity, Integer itemId) throws FoodCartException, ItemException, LoginException;

	public FoodCart decreaseItemQuantity(String key, Integer cartId, Integer quantity, Integer itemId) throws FoodCartException, ItemException, LoginException;
	
	public FoodCart removeItem(String key, Integer cartId, Integer itemId) throws FoodCartException, ItemException, LoginException;
	
	public FoodCart removeCart(String key, Integer cartId) throws FoodCartException, LoginException;
}
