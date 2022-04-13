package com.api.hddrive.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.hddrive.entity.User;
import com.api.hddrive.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findByEmailAndUserName(String email, String nomUsr) {
		return userRepository.findByEmailAndUserName(email, nomUsr);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByEmailAndPassword() {
		return userRepository.findByEmailAndPassword();
	}

	@Override
	public String hashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}

	@Override
	public boolean verifyHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }

	@Override
	public Map<String, String> getMapOfParameters(String... params) {
		Map<String, String> userResponse = new HashMap<>();
		for (int i = 0; i < params.length; i++) {
			userResponse.put(params[i], params[i+1]);
			i++;
		}
		return userResponse;
	}


	
}
