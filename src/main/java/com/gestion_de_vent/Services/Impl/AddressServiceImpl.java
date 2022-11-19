package com.gestion_de_vent.Services.Impl;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gestion_de_vent.Entities.AddressEntity;
import com.gestion_de_vent.Entities.UserEntity;
import com.gestion_de_vent.Repository.AddressRepository;
import com.gestion_de_vent.Repository.UserRepository;
import com.gestion_de_vent.Services.AddressService;
import com.gestion_de_vent.shered.Utils;
import com.gestion_de_vent.shered.dto.AddressDto;
import com.gestion_de_vent.shered.dto.UserDto;



@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	AddressRepository addressRepository;
    @Autowired
	UserRepository userRepository;
    @Autowired
    Utils utils;
	@Override
	public List<AddressDto> getAllAddress(String email) {
		UserEntity cuurentUser = userRepository.findByEmail(email);
		List<AddressDto> addressDto = new ArrayList<>();
		
		List<AddressEntity> addressEntity = cuurentUser.getAdmin() == true ?  (List<AddressEntity>) addressRepository.findAll() :  addressRepository.findByUser(cuurentUser);

		//List<AddressEntity> addressEntity = cuurentUser.getAdmin() == true ? (List<AddressEntity>) addressRepository.findAll(): (List<AddressEntity>) addressRepository.findByUser(cuurentUser);
		for (AddressEntity address : addressEntity) {

			ModelMapper modelMapper = new ModelMapper();
			AddressDto address1 = modelMapper.map(address, AddressDto.class);
			addressDto.add(address1);
		}

		return addressDto;
	}
	@Override
	public AddressDto createAddress(AddressDto address,String email) {
	  UserEntity currentUser = userRepository.findByEmail(email);
	  ModelMapper modelMapper = new ModelMapper();
	 UserDto  userDto = modelMapper.map(currentUser, UserDto.class);
	 address.setAddressId(utils.generateStringId(30));
	 address.setUser(userDto);
			AddressEntity addressEntity = modelMapper.map(address, AddressEntity.class);
			
			AddressEntity addressEn  =  addressRepository.save(addressEntity);
			AddressDto addressDto = modelMapper.map(addressEn, AddressDto.class);

		
		return addressDto;
	}
	@Override
	public AddressDto getOneAddress(String addressId) {
		
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		  ModelMapper modelMapper = new ModelMapper();
		  AddressDto  addressDto = modelMapper.map(addressEntity, AddressDto.class);
		  return addressDto;
		
	}
	@Override
	public void deleteOneAddress(String addressId) {
		
		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		if (addressEntity ==null ) throw new RuntimeException("addres not found");
		addressRepository.delete(addressEntity);
		
	}
	@Override
	public AddressDto updateAddress(String addressId,AddressDto addressDto) {

		AddressEntity addressEntity = addressRepository.findByAddressId(addressId);
		if (addressEntity ==null) throw new RuntimeException("address not found");
		addressEntity.setCity(addressDto.getCity());
		addressEntity.setCountry(addressDto.getCountry());
		addressEntity.setPostal(addressDto.getPostal());
		addressEntity.setStreet(addressDto.getStreet());
		addressEntity.setType(addressDto.getType());
		AddressDto address =new AddressDto();
		BeanUtils.copyProperties(addressEntity,address);
	
		
		return address;
	}


}
