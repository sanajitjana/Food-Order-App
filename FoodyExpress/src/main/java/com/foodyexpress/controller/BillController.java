package com.foodyexpress.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.BillException;
import com.foodyexpress.exception.CustomerException;
import com.foodyexpress.exception.LoginException;
import com.foodyexpress.exception.OrderDetailsException;
import com.foodyexpress.model.Bill;
import com.foodyexpress.service.BillService;

@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	BillService billService;

	@PostMapping("/add")
	public ResponseEntity<Bill> generateBill(@RequestParam(required = false) String key,
			@RequestParam Integer customerId, @RequestParam Integer orderId)
			throws BillException, CustomerException, OrderDetailsException, LoginException {
		Bill myBill = billService.generateBill(key, customerId, orderId);
		return new ResponseEntity<Bill>(myBill, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<Bill> updateBill(@RequestParam(required = false) String key, @RequestBody Bill bill)
			throws BillException, LoginException {
		Bill myBill = billService.updateBill(key, bill);
		return new ResponseEntity<Bill>(myBill, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/remove/{billId}")
	public ResponseEntity<Bill> removeBill(@RequestParam(required = false) String key,
			@PathVariable("billId") Integer billId) throws BillException, LoginException {
		Bill removedBill = billService.removeBill(key, billId);
		return new ResponseEntity<Bill>(removedBill, HttpStatus.OK);
	}

	@GetMapping("/view/{billId}")
	public ResponseEntity<Bill> viewBill(@RequestParam(required = false) String key,
			@PathVariable("billId") Integer billId) throws BillException, LoginException {
		Bill bill = billService.viewBill(key, billId);
		return new ResponseEntity<Bill>(bill, HttpStatus.ACCEPTED);
	}

}
