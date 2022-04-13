package com.api.hddrive.service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.api.hddrive.entity.User;


public interface UserService {
	
	public Iterable<User> findAll();
	
	public Optional<User> findById(Long id);
	
	public User save(User user);
	
	public void deleteById(Long id);
	
	public Optional<User> findByEmailAndUserName(String email, String nomUsr);
	
	public List<User> findByEmailAndPassword();
	
	public String hashPassword(String password);
	
	public boolean verifyHash(String password, String hash);
	
	public Map<String, String> getMapOfParameters(String... params);
	
}
