package com.api.hddrive.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.hddrive.DTO.UserLogOnDTO;
import com.api.hddrive.DTO.converter.UserDTOConverter;
import com.api.hddrive.entity.User;
import com.api.hddrive.service.UserService;
import com.api.hddrive.service.utils.JsonUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/")
@Api(value="HDDrive services for user and file management")
public class UserController {
	
	@Autowired
	Logger logger;
	@Autowired
	private UserService userService;
	@Autowired
	private UserDTOConverter userDTOConverter;
	@Autowired
	private JsonUtils jsonUtils;

	// LOG ON USER
	@ApiOperation(value = "Crear una cuenta de usuario nueva", notes="Provee un mecanismo para registrar un nuevo usuario en la base de datos")
	@PostMapping("/logOnUser")
	public ResponseEntity<String> create(@ApiParam(value="Info del usuario", required = true, type = "UserLogOnDTO.class") @RequestBody UserLogOnDTO userLogOnDto) {
		User user = userDTOConverter.convertToEntity(userLogOnDto);
		SimpleDateFormat dateFor = new SimpleDateFormat("dd/MM/yyyy");
		user.setDate(dateFor.format(new Date()));
		
		user.setPassword(userService.hashPassword(userLogOnDto.getPassword()));
		
		logger.info("[LOG ON] - Check if User already exists");
		// Check if user already exists
		Optional<User> oUser = userService.findByEmailAndUserName(userLogOnDto.getEmail(), userLogOnDto.getName_user());
		
		if(oUser.isPresent()) 
			return ResponseEntity.status(409).body("User already Exists!");
		 
		logger.info("[LOG ON] - Convert User object to Json String");
		
		if(userService.save(user) != null) {
			
			Optional<String> opUser = jsonUtils.convertObjectToJson(userService.getMapOfParameters(
					"id_user", user.getId_user().toString(),
					"name_user", user.getNom_usr()));
			
			if(opUser.isPresent()) 
				return ResponseEntity.status(HttpStatus.CREATED).body(opUser.get());
		}
		
		logger.info("[LOG ON] - User will not be created");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@ApiOperation(value = "Hacer Login de usuario", notes="Se comprueba la existencia de un usuario")
	@GetMapping("/logInUser/{email}/{password}")
	public ResponseEntity<String> logInUser(@ApiParam(value="Email del usuario", required = true, type = "string") @PathVariable String email, @ApiParam(value="Pass del usuario", required = true, type = "string") @PathVariable String password) {
		logger.info("[LOG IN] - Check if User exists");
		
		Optional<User> user = readAll().stream()
										.filter(us -> us.getEmail().equals(email) && userService.verifyHash(password, us.getPassword()))
										.findFirst();
		
		if(user.isPresent()) {
			
			logger.info("[LOG IN] - User exists");
			// Convert user objet to json string
			Optional<String> jsonUser = jsonUtils.convertObjectToJson(userService.getMapOfParameters(
					"id_user", user.get().getId_user().toString(),
					"nom_usr", user.get().getNom_usr(),
					"email", user.get().getEmail(),
					"password", user.get().getPassword()));
			
			if(jsonUser.isPresent()) 
				return ResponseEntity.status(HttpStatus.FOUND).body(jsonUser.get());
		}
		
		logger.info("[LOG IN] - User NOT exists");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	// READ ALL USERS
	@ApiOperation(value = "Recuperar todos los usuarios", notes="Lista todos los usuarios de la base de datos")
	@GetMapping("/readAll")
	public List<User> readAll() {
		return StreamSupport.stream(userService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		
	}
	
}
