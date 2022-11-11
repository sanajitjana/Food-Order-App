package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.AddressException;
import com.foodyexpress.model.Restaurant;

public interface AddressService {
	
	public List<Restaurant> getAllRestaurantsByAddressId(Integer addressId) throws AddressException;

}
