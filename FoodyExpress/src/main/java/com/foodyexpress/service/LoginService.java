package com.foodyexpress.service;

import com.foodyexpress.exception.LoginException;
import com.foodyexpress.model.*;


public interface LoginService {

	public String loginAccount(Login dto) throws LoginException;

	public String logoutAccount(String key) throws LoginException;

}
