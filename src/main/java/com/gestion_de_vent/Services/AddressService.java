package com.gestion_de_vent.Services;

import java.util.List;


import com.gestion_de_vent.shered.dto.AddressDto;

public interface AddressService {
	List<AddressDto> getAllAddress(String email);
	AddressDto createAddress(AddressDto addressDto,String email);
	AddressDto getOneAddress(String AddressId);
	 void deleteOneAddress(String addressId);
	 AddressDto updateAddress(String addressId,AddressDto addressDto);
}
