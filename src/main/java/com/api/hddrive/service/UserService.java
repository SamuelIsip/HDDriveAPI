package com.api.hddrive.service;
import java.util.Optional;

import com.api.hddrive.entity.User;


public interface UserService {
	
	public Iterable<User> findAll();
	
	public Optional<User> findById(Long id);
	
	public User save(User user);
	
	public void deleteById(Long id);
	
	public Optional<User> findByEmailAndPassword(String email, String nom_usr);
	

}
