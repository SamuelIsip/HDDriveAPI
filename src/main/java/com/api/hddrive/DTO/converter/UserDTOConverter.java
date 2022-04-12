package com.api.hddrive.DTO.converter;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.hddrive.DTO.UserLogOnDTO;
import com.api.hddrive.entity.User;


@Component
public class UserDTOConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostConstruct
	private void propertyMapperUserLogOn() {
		TypeMap<UserLogOnDTO, User> properyMapper = modelMapper.createTypeMap(UserLogOnDTO.class, User.class);
		properyMapper.addMapping(UserLogOnDTO::getName_user, User::setNom_usr);
		properyMapper.addMapping(UserLogOnDTO::getPhone, User::setTlf);
	}

	public User convertToEntity(UserLogOnDTO userLogOnDTO) {
		return modelMapper.map(userLogOnDTO, User.class);
	}
	
	public UserLogOnDTO convertToLogOnDTO(User user) {
		return modelMapper.map(user, UserLogOnDTO.class);
	}
}
