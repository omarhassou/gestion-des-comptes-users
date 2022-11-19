package com.gestion_de_vent.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gestion_de_vent.Services.UserService;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter  {
	
	private final UserService userService;
	   
	 private final BCryptPasswordEncoder  bCryptPasswordEncoder;
	 public  WebSecurity(UserService userService,BCryptPasswordEncoder  bCryptPasswordEncoder) {
		 this.bCryptPasswordEncoder=  bCryptPasswordEncoder;
		 this.userService=userService;
	 }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
             
		http   
		.cors()
		.and()  
		.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConstant.SIGN_UP_URL)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.addFilter( getAuthenticationFilter() )
		.addFilter(new AuthorizationFilter(authenticationManager()))
		.sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}   
	//login avec  email et password 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	  	auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}  
	//pour changer URL 
	protected AuthenticationFilter getAuthenticationFilter() throws Exception {
	    final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
	    filter.setFilterProcessesUrl("/users/login");
	    return filter;
	}
       
 
}
