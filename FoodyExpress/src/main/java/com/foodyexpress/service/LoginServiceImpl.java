package com.foodyexpress.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.CurrentUserSession;
import com.foodyexpress.model.Customer;
import com.foodyexpress.model.Login;
import com.foodyexpress.repository.CustomerRepo;
import com.foodyexpress.repository.CurrentUserSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepo cRepo;

	@Autowired
	private CurrentUserSessionRepo sRepo;

	@Override
	public String loginAccount(Login login) throws LoginException {

		Customer customer = cRepo.findByEmail(login.getUserName());
		if (customer != null) {

			if (customer.getPassword().equals(login.getPassword())) {

				CurrentUserSession cuurSession = sRepo.findByEmail(login.getUserName());

				if (cuurSession != null) {
					throw new LoginException("User already Logged In!");
				} else {
					CurrentUserSession currentUserSession = new CurrentUserSession();
					currentUserSession.setEmail(login.getUserName());
					currentUserSession.setLoginDateTime(LocalDateTime.now());

					String key = RandomString.make(6);
					currentUserSession.setPrivateKey(key);

					sRepo.save(currentUserSession);
					return "Login Sucessufull!";
				}
			} else {
				throw new LoginException("Please Enter a valid password");
			}

		} else {
			throw new LoginException("Please Enter a valid username");
		}
	}

	@Override
	public String logoutAccount(String key) throws LoginException {

		CurrentUserSession currSession = sRepo.findByPrivateKey(key);
		if (currSession != null) {
			sRepo.delete(currSession);
			return "Logged Out!";
		} else {
			throw new LoginException("This User not-Logged In");
		}
	}
}
