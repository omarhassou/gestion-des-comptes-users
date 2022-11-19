package com.gestion_de_vent.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gestion_de_vent.Request.AddressRequest;
import com.gestion_de_vent.Services.AddressService;
import com.gestion_de_vent.responses.AddressResponse;
import com.gestion_de_vent.shered.dto.AddressDto;

@RestController
@RequestMapping("/Addreses")
public class AddressController {

	@Autowired
	AddressService addressService;

	@GetMapping
	public List<AddressResponse> getAddress(Principal principal) {
		List<AddressResponse> addressResponse = new ArrayList<>();
		List<AddressDto> addressDto = addressService.getAllAddress(principal.getName());
		for (AddressDto address : addressDto) {

			ModelMapper modelMapper = new ModelMapper();
			AddressResponse addressRespons = modelMapper.map(address, AddressResponse.class);
			addressResponse.add(addressRespons);
		}

		return addressResponse;

	}

	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public AddressResponse createAddress(@RequestBody AddressRequest addressRequest, Principal principal) {
		ModelMapper modelMapper = new ModelMapper();
		AddressDto addressDto = modelMapper.map(addressRequest, AddressDto.class);

		AddressDto address = (AddressDto) addressService.createAddress(addressDto, principal.getName());

		AddressResponse addressRespons = modelMapper.map(address, AddressResponse.class);

		return addressRespons;
	}
	@GetMapping(path ="/{addresid}")
	public AddressResponse getOneAddress(@PathVariable String addresid) {
		ModelMapper modelMapper = new ModelMapper();
	AddressDto addressDto = addressService.getOneAddress(addresid);
	AddressResponse addressRespoonse = modelMapper.map(addressDto, AddressResponse.class);
		return addressRespoonse;
	}
	@DeleteMapping("/{addressId}")
	public void DeleteOneAddress(@PathVariable String addressId  ) {
		
		
		 addressService.deleteOneAddress(addressId);
		
	}
 	@PutMapping("/{id}")
 	public AddressResponse updateAddress(@PathVariable String id,@RequestBody AddressRequest addressRequest) {
 		 AddressDto  addressDto =new AddressDto();
 		AddressResponse addressResponse =new AddressResponse();
 		BeanUtils.copyProperties(addressRequest,addressDto);
 		
 		AddressDto address  = addressService.updateAddress( id,addressDto);
 		BeanUtils.copyProperties(address,addressResponse);
 		//AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
 		
 		return addressResponse;
 	}
}
