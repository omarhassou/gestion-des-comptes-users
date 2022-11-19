package com.gestion_de_vent.Services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gestion_de_vent.Entities.UserEntity;
import com.gestion_de_vent.Repository.UserRepository;
import com.gestion_de_vent.Services.UserService;
import com.gestion_de_vent.shered.Utils;
import com.gestion_de_vent.shered.dto.AddressDto;

import com.gestion_de_vent.shered.dto.UserDto;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.lang.RuntimeException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		UserEntity check = userRepository.findByEmail(user.getEmail());
		if (check != null)  
			throw new RuntimeException("User already exist");
                  
		for (int i = 0; i < user.getAddreses().size(); i++) {
			AddressDto address = user.getAddreses().get(i);
			address.setUser(user);     
			address.setAddressId(utils.generateStringId(30));   
			user.getAddreses().set(i, address);   
		}         
           user.getContact().setContactId(utils.generateStringId(30));
             
          user.getContact().setUserDto(user);
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);
		userEntity.setEncryptedpassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserid(utils.generateStringId(30));
		UserEntity newUser = userRepository.save(userEntity);
		UserDto userDto = modelMapper.map(newUser, UserDto.class);
		return userDto;
	}

	// permet de recuperer user authentifie via son email
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		return new User(userEntity.getEmail(), userEntity.getEncryptedpassword(), new ArrayList<>());
	}

	// permet recuperer user authentifie via son email
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto getUserByUserid(String userid) {
		UserEntity userEntity = userRepository.findByUserid(userid);
		if (userEntity == null)
			throw new UsernameNotFoundException(userid);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);

		return userDto;
	}

	@Override
	public UserDto UpdateUser(String id, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserid(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());
		UserEntity userUpdate = userRepository.save(userEntity);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(userUpdate, user);

		return user;
	}

	@Override
	public void DeleteUser(String id) {
		UserEntity userEntity = userRepository.findByUserid(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);
		userRepository.delete(userEntity);

	}

	@Override
	public List<UserDto> getUsers(int page, int limit,String search,int status) {
		if (page > 0) {
			page = page - 1;
		}
		List<UserDto> userDto = new ArrayList<>();
		Pageable pageablegRequest = PageRequest.of(page, limit);
		Page<UserEntity> userPage;
		if(search.isEmpty()) {
			 userPage = userRepository.finAllUser(pageablegRequest);
		}
		else {
	          userPage = userRepository.finAllUserByCriterie(pageablegRequest,search,status);
		}

		List<UserEntity> users = userPage.getContent();
		for (UserEntity userEntity : users) {
		
			ModelMapper modelMapper = new ModelMapper();
			 UserDto user = modelMapper.map(userEntity, UserDto.class);
			
			userDto.add(user);

		}
		return userDto;
	}



}
