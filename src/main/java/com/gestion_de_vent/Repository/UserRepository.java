package com.gestion_de_vent.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gestion_de_vent.Entities.UserEntity;

@Repository
public interface  UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email); 
	UserEntity findByUserid(String userid);
	//@Query(value ="select * from users ",nativeQuery =true)
	//Page<UserEntity>finAllUser(Pageable pageableRequest); 
	@Query( value = "select * from  users ",nativeQuery= true)
	Page<UserEntity>finAllUser(Pageable pageableRequest); 
	//@Query(value ="select * from users  u where (u.firstname = ?1 or u.lastname = ?1) AND  u.emailVerificationStatuse =?2",nativeQuery =true)
	//Page<UserEntity>finAllUserByCriterie(Pageable pageableRequest, String search,int status); 
	@Query(value ="select * from users  u where (u.firstname = :search or u.lastname = :search) AND  u.email_Verification_Statuse = :status",nativeQuery =true)
   Page<UserEntity>finAllUserByCriterie(Pageable pageableRequest, @Param("search")String search,@Param("status" ) int status); 
	
	//@Query(value ="select * from users  u where (u.firstname LIKE %:search or u.lastname LIKE % :search) AND  u.email_Verification_Statuse = :status",nativeQuery =true)
	//Page<UserEntity>(Pageable pageableRequest, @Param("search")String search,@Param("status" ) int status);
	
//	@Query(value="SELECT * FROM users u WHERE (u.firstname LIKE %:search% OR u.lastname LIKE %:search%) AND u.email_verification_status = :status", nativeQuery=true)
	//Page<UserEntity> finAllUserByCriterie(Pageable pageableRequest, @Param("search") String search, @Param("status") int status);

	
	
}
