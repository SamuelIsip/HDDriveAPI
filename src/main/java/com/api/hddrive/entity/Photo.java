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
@Table(name = "photos")
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_photo;
	@Column
	private Long id_user;
	@Column(length = 50)
	private String name;
	@Column(length = 250)
	private String path;
	@Column(length = 50)
	private String size;
	@Column(length = 50)
	private String date;
	
}
