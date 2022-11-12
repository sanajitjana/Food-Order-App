package com.foodyexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.FoodCartException;
import com.foodyexpress.exception.ItemException;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.CustomerDTO;
import com.foodyexpress.model.FoodCart;
import com.foodyexpress.model.Item;
import com.foodyexpress.model.ItemDTO;
import com.foodyexpress.repository.CustomerRepo;
import com.foodyexpress.repository.FoodCartRepo;
import com.foodyexpress.repository.ItemRepo;

@Service
public class FoodCartServiceImpl implements FoodCartService {

	@Autowired
	private FoodCartRepo foodcartRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private ItemRepo itemRepo;

//	@Override
//	public FoodCart addItemToCart(Integer cartId, Item item) throws FoodCartException {
//		// TODO Auto-generated method stub
//
//		Optional<FoodCart> opt = foodcartRepo.findById(cartId);
//
//		if (opt.isPresent()) {
//			FoodCart foodCart = opt.get();
//			foodCart.getItemList().add(item);
//			foodcartRepo.save(foodCart);
//			return foodCart;
//
//		} else {
//			throw new FoodCartException("Food Cart not found!");
//		}
//	}

	@Override
	public FoodCart addItemToCart(CustomerDTO customerDTO, ItemDTO itemDTO) throws ItemException, CustomerException {
		
		Optional<Customer> opt = customerRepo.findById(customerDTO.getCustomerId());
		if (opt.isEmpty())
			throw new CustomerException("Customer not found!");
		
		Optional<Item> itemOpt = itemRepo.findById(itemDTO.getItemId());
		if (itemOpt.isEmpty())
			throw new ItemException("Item not found!");
		
		Customer customer = opt.get();
		FoodCart foodCart = customer.getCart();
		
		foodCart.getItemList().add(itemOpt.get());
		foodcartRepo.save(foodCart);		
		return foodCart;
		
	}

	@Override
	public FoodCart increaseItemQuantity(Integer cartId, Integer quantity, Integer itemId)
			throws FoodCartException, ItemException {
		// TODO Auto-generated method stub

		Optional<FoodCart> opt = foodcartRepo.findById(cartId);

		if (opt.isPresent()) {
			FoodCart foodCart = opt.get();

			List<Item> itemList = foodCart.getItemList();

			boolean flag = true;

			for (int i = 0; i < itemList.size(); i++) {
				Item ele = itemList.get(i);
				if (ele.getItemId() == itemId) {
					ele.setQuantity(ele.getQuantity() + quantity);
					flag = false;
				}
			}
			if (flag)
				throw new ItemException("Item not found!");
			else {

				foodCart.setItemList(itemList);
				foodcartRepo.save(foodCart);
				return foodCart;
			}
		} else {
			throw new FoodCartException("Food Cart not found!");
		}

	}

	@Override
	public FoodCart decreaseItemQuantity(Integer cartId, Integer quantity, Integer itemId)
			throws FoodCartException, ItemException {
		// TODO Auto-generated method stub

		Optional<FoodCart> opt = foodcartRepo.findById(cartId);

		if (opt.isPresent()) {
			FoodCart foodCart = opt.get();

			List<Item> itemList = foodCart.getItemList();

			boolean flag = true;

			for (int i = 0; i < itemList.size(); i++) {
				Item ele = itemList.get(i);
				if (ele.getItemId() == itemId) {

					if (ele.getQuantity() >= quantity) {
						ele.setQuantity(ele.getQuantity() - quantity);
						flag = false;
					} else {
						throw new ItemException("Insufficient quantity!");
					}
				}
			}
			if (flag)
				throw new ItemException("Item not found!");
			else {

				foodCart.setItemList(itemList);
				foodcartRepo.save(foodCart);
				return foodCart;
			}
		} else {
			throw new FoodCartException("Food Cart not found!");
		}

	}

	@Override
	public FoodCart removeItem(Integer cartId, Integer itemId) throws FoodCartException, ItemException {
		// TODO Auto-generated method stub

		Optional<FoodCart> opt = foodcartRepo.findById(cartId);

		if (opt.isPresent()) {
			FoodCart foodCart = opt.get();

			List<Item> itemList = foodCart.getItemList();

			boolean flag = true;
			Item getItem = null;

			for (int i = 0; i < itemList.size(); i++) {
				Item ele = itemList.get(i);
				if (ele.getItemId() == itemId) {
					flag = false;
					getItem = ele;
				}
			}
			if (flag)
				throw new ItemException("Item not found!");
			else {
				itemList.remove(getItem);
				foodCart.setItemList(itemList);
				foodcartRepo.save(foodCart);
				return foodCart;
			}
		} else {
			throw new FoodCartException("Food Cart not found!");
		}
	}

	@Override
	public FoodCart removeCart(Integer cartId) throws FoodCartException {
		// TODO Auto-generated method stub

		Optional<FoodCart> opt = foodcartRepo.findById(cartId);

		if (opt.isPresent()) {
			FoodCart foodCart = opt.get();
			foodcartRepo.delete(foodCart);
			return foodCart;
		} else {
			throw new FoodCartException("Food Cart not found!");
		}
	}
}
