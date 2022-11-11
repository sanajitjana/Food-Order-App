package com.foodyexpress.controller;

import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.Login;
import com.foodyexpress.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class LoginController {

	@Autowired
	private LoginService customerLogin;

	@PostMapping("/login")
	public ResponseEntity<String> logInCustomer(@RequestBody Login logindto) throws LoginException {
		String result = customerLogin.loginAccount(logindto);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping("/logout")
	public String logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
		return customerLogin.logoutAccount(key);
	}

}
