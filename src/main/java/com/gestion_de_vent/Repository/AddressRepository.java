package com.gestion_de_vent.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestion_de_vent.Entities.AddressEntity;
import com.gestion_de_vent.Entities.UserEntity;


@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

	
	List<AddressEntity> findByUser(UserEntity curentUser);
	AddressEntity findByAddressId(String addressId);
}
