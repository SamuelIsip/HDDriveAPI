package com.api.hddrive.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hddrive.DTO.UserLogOnDTO;
import com.api.hddrive.DTO.converter.UserDTOConverter;
import com.api.hddrive.entity.User;
import com.api.hddrive.service.UserService;

@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	private UserService userService;
	@Autowired
	UserDTOConverter userDTOConverter;

	// LOG ON USER
	@PostMapping("/logOnUser")
	public ResponseEntity<?> create(@RequestBody UserLogOnDTO userLogOnDto) {
		User user = userDTOConverter.convertToEntity(userLogOnDto);
		SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
		user.setDate(dateFor.format(new Date()));
		
		// Check if user already exists
		Optional<User> oUser = userService.findByEmailAndPassword(userLogOnDto.getEmail(), userLogOnDto.getName_user());
		
		if(oUser.isPresent()) 
			return ResponseEntity.status(409).body("User already Exists!");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
	}
	
	// READ ALL USERS
	@GetMapping("/readAll")
	public List<User> readAll() {
		List<User> users = StreamSupport.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
		return users;
	}
	
}
