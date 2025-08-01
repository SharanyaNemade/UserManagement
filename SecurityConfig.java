package com.usermgm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();		//	Uses BCrypt for encoding
	}
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.authorizeRequests(auth-> auth 
				.requestMatchers("/register", "/login", "/css/**").permitAll()
				.anyRequest().authenticated())
				.formLogin(form->form.loginPage("/login")
						.defaultSuccessUrl("/",true).permitAll())
				.logout(logout->logout.logoutSuccessUrl("/login?logout").permitAll());
				
				return http.build();
	}
}
