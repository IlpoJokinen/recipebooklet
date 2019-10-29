package com.example.Recipebooklet;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.example.Recipebooklet.controller.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
		.and()
		.authorizeRequests().anyRequest().authenticated()
		.and()
	  .formLogin()
	  		.loginPage("/login")
	  	 	.defaultSuccessUrl("/recipebooklet")
	  		.permitAll()
	  		.and()
	  .logout()
	  		.permitAll();
	}
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		
		List<UserDetails> users = new ArrayList();
		
		UserDetails ilpo = User.withDefaultPasswordEncoder()
				.username("Ilpo")
				.password("Ilpo")
				.roles("USER", "ADMIN")
				.build();
			
				
		UserDetails guest = User.withDefaultPasswordEncoder()
				.username("Guest")
				.password("Guest")
				.roles("USER")
				.build();
				
		users.add(ilpo);
		users.add(guest);
		
		return new InMemoryUserDetailsManager(users);
	}
}
