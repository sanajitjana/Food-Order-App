package com.foodyexpress.service;

import java.util.List;

import com.foodyexpress.exception.AddressException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.RestaurantException;
import com.foodyexpress.model.Restaurant;

public interface RestaurantService {

	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException;

	public Restaurant updateRestaurant(Restaurant restaurant) throws RestaurantException;

	public Restaurant removeRestaurant(Integer restaurantId) throws RestaurantException;

	public Restaurant viewRestaurantById(Integer restaurantId) throws RestaurantException;

	public List<Restaurant> getAllRestaurants() throws RestaurantException;

	public List<Restaurant> viewNearByRestaurant(String city) throws RestaurantException, AddressException;

	public List<Restaurant> viewRestaurantByItemName(String itemName) throws RestaurantException, ItemException;


}
