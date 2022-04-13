package com.api.hddrive.service.utils;

import java.util.Optional;

public interface JsonUtils {

	public <T> Optional<String> convertObjectToJson(T object);
	
}
