package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.AddressException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.RestaurantException;
import com.foodyexpress.model.Address;
import com.foodyexpress.model.Item;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.repository.AddressRepo;
import com.foodyexpress.repository.ItemRepo;
import com.foodyexpress.repository.RestaurantRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private RestaurantRepo resRepo;

	@Autowired
	private AddressRepo addressRepo;
	
	@Autowired
	private ItemRepo itemRepo;

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {

		Address address = restaurant.getAddress();
		address.getRestaurantList().add(restaurant);

		List<Item> itemList = restaurant.getItemList();

		for (Item ele : itemList) {
			ele.getRestaurants().add(restaurant);
		}
		return resRepo.save(restaurant);
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
	public List<Restaurant> viewNearByRestaurant(String city) throws RestaurantException, AddressException {

		Address address = addressRepo.findByCity(city);
		if (address != null) {
			List<Restaurant> restaurantList = address.getRestaurantList();
			if (!restaurantList.isEmpty()) {
				return restaurantList;
			} else {
				throw new RestaurantException("No restaurant found!");
			}
		} else {
			throw new AddressException("No address found!");
		}
	}

	@Override
	public List<Restaurant> viewRestaurantByItemName(String itemName) throws RestaurantException, ItemException {
		
			Item item=itemRepo.findByItemName(itemName);
			if(item==null) {
				throw new ItemException("Item not found!");
			}else {
				List<Restaurant> restaurantList= item.getRestaurants();
				if(!restaurantList.isEmpty()) {
					return restaurantList;
				}else {
					throw new RestaurantException("Restaurant not found!");
				}
			}
	}

}
