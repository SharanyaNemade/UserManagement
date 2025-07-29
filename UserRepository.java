package com.usermgm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermgm.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

	User findByUsername(String username);
	
	
	
	
}
