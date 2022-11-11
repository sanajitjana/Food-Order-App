package com.foodyexpress.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.AddressException;
import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.exception.RestaurantException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.service.AddressService;
import com.foodyexpress.service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService resService;

	@Autowired
	private AddressService addresService;

	@PostMapping("/add")
	public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant res) throws RestaurantException {
		Restaurant restaurant = resService.addRestaurant(res);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update")
	public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant res) throws RestaurantException {
		Restaurant restaurant = resService.updateRestaurant(res);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Restaurant> deleteRestaurant(Integer restaurantId) throws RestaurantException {
		Restaurant restaurant = resService.removeRestaurant(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
	}

	@GetMapping("/view/{id}")
	public ResponseEntity<Restaurant> viewRestaurant(@PathVariable("id") Integer restaurantId)
			throws RestaurantException {
		Restaurant restaurant = resService.viewRestaurantById(restaurantId);
		return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<List<Restaurant>> viewAllRestaurant() throws RestaurantException {
		List<Restaurant> restaurant = resService.getAllRestaurants();
		return new ResponseEntity<List<Restaurant>>(restaurant, HttpStatus.OK);
	}

//	@GetMapping("/getRestaurantByAddressId/{id}")
//	public ResponseEntity<List<Restaurant>> findAllRestaurantByAddresId(@PathVariable("id") Integer addressId)
//			throws RestaurantException, AddressException {
//		List<Restaurant> restaurantList = addresService.getAllRestaurantsByAddressId(addressId);
//		return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
//	}
	
	@GetMapping("/findNearByRestaurantByCity/{city}")
	public ResponseEntity<List<Restaurant>> findNearByRestaurantByCityHandler(@PathVariable("city") String city)
			throws RestaurantException, AddressException {
		List<Restaurant> restaurantList=resService.viewNearByRestaurant(city);		
		return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
	}
	
	@GetMapping("/findNearByRestaurantByItemName/{item}")
	public ResponseEntity<List<Restaurant>> viewRestaurantByItemNameHandler(@PathVariable("item") String item)
			throws RestaurantException, ItemException {
		List<Restaurant> restaurantList=resService.viewRestaurantByItemName(item);		
		return new ResponseEntity<List<Restaurant>>(restaurantList, HttpStatus.OK);
	}

}
