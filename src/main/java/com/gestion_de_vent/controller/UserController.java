package com.gestion_de_vent.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_de_vent.Exception.UserException;
import com.gestion_de_vent.Request.UserRequest;

import com.gestion_de_vent.Services.UserService;
import com.gestion_de_vent.responses.ErrorMessages;
import com.gestion_de_vent.responses.UserResponse;
import com.gestion_de_vent.shered.dto.UserDto;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	@GetMapping(path="/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserResponse getUser( @PathVariable String id ) {
		
		                 
			UserDto userDto = userService.getUserByUserid(id);
			UserResponse userResponse =new UserResponse();
			BeanUtils.copyProperties(userDto, userResponse);
			
			return userResponse;
	}
	@PostMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public  UserResponse creatUser(@RequestBody   UserRequest userRequest) throws Exception {
if(userRequest.getFirstname().isEmpty()) throw new UserException(ErrorMessages .MISSING_REQUIRED_FIELD.getErrorMessage());
		//UserDto userDto = new  UserDto();
   ModelMapper modelMapper =new ModelMapper();
  UserDto userDto = modelMapper.map(userRequest, UserDto.class);
//BeanUtils.copyProperties(userRequest, userDto);
UserDto createUser = userService.createUser(userDto);
UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
//return new ResponseEntity<UserResponse>(HttpStatus.CREATED);
return userResponse;
	}   
	@PutMapping(path = "/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public UserResponse updateUser(@RequestBody UserRequest userRequest,@PathVariable  String id) {
		UserDto userDto = new  UserDto();
              
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto updateUser = userService.UpdateUser(id, userDto);
		UserResponse userResponse = new UserResponse();
		BeanUtils.copyProperties(updateUser, userResponse);
		return userResponse;
	}
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
 		  
		 userService.DeleteUser(id);
		 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		// return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
		 
	}
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<UserResponse>getAllUsers(@RequestParam(value="page",defaultValue = "1") int page,@RequestParam(value="limit",defaultValue ="15") int limit,@RequestParam(value="search",defaultValue = "") String search,@RequestParam(value="status",defaultValue = "1") int status){
		
		List<UserResponse> userResponse = new ArrayList<>();
	List<UserDto> users = userService.getUsers(page,limit,search,status);
	 	
	for(UserDto userDto:users) {
		
		
		ModelMapper modelMapper =new ModelMapper();
		UserResponse user  = modelMapper.map(userDto, UserResponse.class);
		userResponse.add(user);
		}
		return userResponse;
	}

}
