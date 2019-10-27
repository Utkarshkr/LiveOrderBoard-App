package com.silverbarsmarketplace.application.securityconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * The {@code SecurityConfig} class is the  class for handling custom security for this application.
 * @author Utkarsh Kumar
 */


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	            .anyRequest()
	            .permitAll()
	            .and().csrf().disable();
	        
	    }

}
