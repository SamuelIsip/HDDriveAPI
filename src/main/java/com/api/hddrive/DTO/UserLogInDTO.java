package com.api.hddrive.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInDTO {

	private Long id_user;
	private String nom_usr;
	private String email;
	private String password;

}
