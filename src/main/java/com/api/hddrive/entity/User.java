package com.api.hddrive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_user;
	@Column(length = 50)
	private String name;
	@Column(length = 50, unique = true)
	private String email;
	@Column(length = 50)
	private String password;
	@Column(length = 50)
	private String nom_usr;
	@Column(length = 50)
	private String tlf;
	@Column(length = 50)
	private String date;

}
