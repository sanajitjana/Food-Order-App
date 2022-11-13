package com.foodyexpress.service;

import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.*;


public interface LoginService {

	public String loginAccount(LoginDTO loginDTO) throws LoginException;

	public String logoutAccount(String role, String key) throws LoginException;

}
