package com.api.hddrive.config;

import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean Logger getLogger() {
		return Logger.getLogger("MyLog");
	}
	
}
