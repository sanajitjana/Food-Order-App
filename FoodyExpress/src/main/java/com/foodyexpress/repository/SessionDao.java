package com.foodyexpress.repository;

import com.foodyexpress.model.CurrentUserSession;
import org.springframework.data.jpa.repository.JpaRepository;



public interface SessionDao extends JpaRepository<CurrentUserSession, Integer> {

	
	public  CurrentUserSession  findByUuid(String uuid);
}
