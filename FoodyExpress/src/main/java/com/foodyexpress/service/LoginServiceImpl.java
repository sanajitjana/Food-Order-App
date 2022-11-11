package com.foodyexpress.service;



import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.CurrentUserSession;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Login;
import com.foodyexpress.repository.SessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private Customer cDao;
	
	@Autowired
	private SessionDao sDao;
	
	
	
	@Override
	public String loginAccount(Login logindto)throws LoginException{
		
		
		Customer existingCustomer= cDao.findByUserId(logindto.getUserId());
		
		if(existingCustomer == null) {
			
			throw new LoginException("Please Enter a valid UserId");
			
			 
		}
		
		
		
		
		Optional<CurrentUserSession> validCustomerSessionOpt =  sDao.findById(existingCustomer.getCustomerId());
		
		
		
		
		
		if(validCustomerSessionOpt.isPresent()) {
			
			throw new LoginException("User already Logged In with this UserId");
			
		}
		
		if(existingCustomer.getPassword().equals(logindto.getPassword())) {
			
			String key= RandomString.make(6);
			
			
			
			CurrentUserSession currentUserSession = new CurrentUserSession(existingCustomer.getCustomerId(),key,LocalDateTime.now());
			
			sDao.save(currentUserSession);

			return currentUserSession.toString();
		}
		else
			throw new LoginException("Please Enter a valid password");
		
		
	}


	@Override
	public String logoutAccount(String key)throws LoginException {
		
		CurrentUserSession validCustomerSession = sDao.findByUuid(key);
		
		
		if(validCustomerSession == null) {
			throw new LoginException("User Not Logged In with this UserId");
			
		}
		
		
		sDao.delete(validCustomerSession);
		
		
		return "Logged Out !";
		
		
	}


	

}
