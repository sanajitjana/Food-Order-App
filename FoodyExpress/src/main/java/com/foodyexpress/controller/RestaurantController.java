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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.RestaurantException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Restaurant;
import com.foodyexpress.service.RestaurantService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService resService;

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

}
