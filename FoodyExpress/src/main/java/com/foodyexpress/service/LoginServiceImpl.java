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

		Optional<Customer> cusOpt = cRepo.findById(login.getUserId());
		if (cusOpt.isEmpty()) {

			Optional<CurrentUserSession> curUserOpt = sRepo.findById(cusOpt.get().getCustomerId());
			if (curUserOpt.isPresent()) {

				throw new LoginException("User already Logged In");
			} else {

				if (cusOpt.get().getPassword().equals(login.getPassword())) {

					String key = RandomString.make(6);
					CurrentUserSession currentUserSession = new CurrentUserSession(cusOpt.get().getCustomerId(), key,
							LocalDateTime.now());

					sRepo.save(currentUserSession);
					return currentUserSession.toString();
				} else

					throw new LoginException("Please Enter a valid password");
			}
		} else {

			throw new LoginException("Please Enter a valid UserId");
		}
	}

	@Override
	public String logoutAccount(String key) throws LoginException {

		CurrentUserSession validCustomerSession = sRepo.findByUuid(key);

		if (validCustomerSession == null) {
			throw new LoginException("User Not Logged In");
		} else {
			sRepo.delete(validCustomerSession);
			return "Logged Out!";
		}
	}
}
