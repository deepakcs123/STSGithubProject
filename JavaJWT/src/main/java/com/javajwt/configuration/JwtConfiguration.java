package com.javajwt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.javajwt.services.CustomUserdetailsService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class JwtConfiguration extends WebSecurityConfigurerAdapter {
	//https://www.codejava.net/frameworks/spring-boot/fix-websecurityconfigureradapter-deprecated
	
	
	
	@Autowired
	private CustomUserdetailsService customUserdetailsService;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(customUserdetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
	}

	
}
