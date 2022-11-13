package com.foodyexpress.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodyexpress.exception.AdminException;
import com.foodyexpress.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/new")
	public ResponseEntity<String> createAdmin() throws AdminException{
		String result=adminService.createNewAdmin();
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}

}
