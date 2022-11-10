package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.RestaurantException;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.repository.RestaurantRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepo resRepo;

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
		// TODO Auto-generated method stub

		Optional<Restaurant> opt = resRepo.findById(restaurant.getRestaurantId());
		if (opt.isPresent()) {
			throw new RestaurantException("Restaurant already exists");
		} else {
			return resRepo.save(restaurant);
		}
	}

	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantException {
		// TODO Auto-generated method stub

		Optional<Restaurant> opt = resRepo.findById(restaurant.getRestaurantId());
		if (opt.isPresent()) {
			return resRepo.save(restaurant);
		} else {
			throw new RestaurantException("Restaurant id not found!");
		}
	}

	@Override
	public Restaurant removeRestaurant(Integer restaurantId) throws RestaurantException {
		// TODO Auto-generated method stub

		Optional<Restaurant> opt = resRepo.findById(restaurantId);
		if (opt.isPresent()) {
			Restaurant restaurant = opt.get();
			resRepo.delete(restaurant);
			return restaurant;
		} else {
			throw new RestaurantException("Restaurant id not found!");
		}
	}

	@Override
	public Restaurant viewRestaurantById(Integer restaurantId) throws RestaurantException {
		// TODO Auto-generated method stub

		Optional<Restaurant> opt = resRepo.findById(restaurantId);
		if (opt.isPresent()) {
			Restaurant restaurant = opt.get();
			return restaurant;
		} else {
			throw new RestaurantException("Restaurant id not found!");
		}
	}

	@Override
	public List<Restaurant> getAllRestaurants() throws RestaurantException {
		// TODO Auto-generated method stub

		List<Restaurant> restaurantList = resRepo.findAll();
		if (!restaurantList.isEmpty()) {
			return restaurantList;
		} else {
			throw new RestaurantException("Empty!");
		}
	}

	@Override
	public List<Restaurant> viewRestaurantByLocation(String location) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String itemName) throws RestaurantException {
		// TODO Auto-generated method stub
		return null;
	}

}
