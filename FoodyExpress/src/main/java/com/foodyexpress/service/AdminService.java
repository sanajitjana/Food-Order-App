package com.foodyexpress.service;

import com.foodyexpress.exception.AdminException;
import com.foodyexpress.model.Admin;

public interface AdminService {

	public String createNewAdmin() throws AdminException;

}
