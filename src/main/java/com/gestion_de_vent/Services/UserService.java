package com.gestion_de_vent.Services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.gestion_de_vent.Entities.UserEntity;
import com.gestion_de_vent.shered.dto.UserDto;

public interface UserService extends UserDetailsService{
   
	 UserDto createUser( UserDto userDto);
	 UserDto getUser(String email);   
	 UserDto getUserByUserid(String userid); 
 	 UserDto UpdateUser( String id,UserDto userDto);
	 void  DeleteUser(String userid);
        
	 List<UserDto> getUsers(int page,int limit,String search,int status );
}
