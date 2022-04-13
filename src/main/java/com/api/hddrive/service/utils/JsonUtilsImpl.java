package com.api.hddrive.service.utils;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Service
public class JsonUtilsImpl implements JsonUtils {
	
	@Autowired
	Logger logger;

	@Override
	public <T> Optional<String> convertObjectToJson(T object) {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		
		try {
			return Optional.of(ow.writeValueAsString(object));
		} catch (JsonProcessingException e) {
			logger.log(Level.WARNING, "[JSON UTILS] - Can not convert object to Json String");
		}
		
		return Optional.empty();
		
	}

}
