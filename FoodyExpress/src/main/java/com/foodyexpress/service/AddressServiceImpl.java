package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.AddressException;
import com.foodyexpress.exception.RestaurantException;
import com.foodyexpress.model.Address;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.repository.AddressRepo;

@Service
public class AddressServiceImpl implements AddressService{

	@Override
	public List<Restaurant> getAllRestaurantsByAddressId(Integer addressId) throws AddressException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
