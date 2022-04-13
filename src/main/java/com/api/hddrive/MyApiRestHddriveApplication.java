package com.api.hddrive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MyApiRestHddriveApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyApiRestHddriveApplication.class, args);
	}

}
