package com.api.hddrive.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.hddrive.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(value = "SELECT * FROM user WHERE user.email = :email AND user.nom_usr = :nom_usr", nativeQuery=true)
	Optional<User> findByEmailAndUserName(@Param("email") String email, @Param("nom_usr") String nomUsr);
	
	@Query(value = "SELECT * FROM user", nativeQuery=true)
	List<User> findByEmailAndPassword();
	
}
