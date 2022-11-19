package com.gestion_de_vent.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion_de_vent.SpringApplicationContext;
import com.gestion_de_vent.Request.UserLoginRequest;

import com.gestion_de_vent.shered.dto.UserDto;
import com.gestion_de_vent.Services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//utiliser pour verifier est ce  que il exist une utilisateur qui contient email et password envoyer par la requet /login

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String userName = ((User) auth.getPrincipal()).getUsername();

		// UserService userService =
		// (UserService)SpringApplicationContext.getBean("userSeviceImpl");

		String token = Jwts.builder().setSubject(userName)
				// .claim("id", userDto.getUserid())
				// .claim("name", userDto.getFirstname() + " " + userDto.getFirstname())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPERATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.TOKEN_SECRET).compact();
          
		UserService userService = (UserService) SpringApplicationContext.getBaen("userServiceImpl");
             
		UserDto userDto = userService.getUser(userName);
		res.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + token);
		res.addHeader("user_id", userDto.getUserid());

		res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \""+
		userDto.getUserid() + "\"}");

	}
}
