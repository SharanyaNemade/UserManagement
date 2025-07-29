package com.usermgm.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.usermgm.model.User;
import com.usermgm.repo.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;
	 
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void saveUser(User user)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		userRepository.save(user);
	}
	
	
	public UserDetails loadUserByUsername(String username) 
	{
		
		User user = userRepository.findByUsername(username);
		if(user==null) throw new UsernameNotFoundException("User Not Found");
		
		return new 
		org.springframework.security.core.userdetails.User
		(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole())) );
		
	}
	
}
