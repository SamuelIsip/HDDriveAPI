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
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_task;
	@Column
	private Long id_user;
	@Column(length = 100)
	private String modified;
	@Column(length = 1000)
	private String text;
	@Column(length = 100)
	private String title;
	@Column(length = 50)
	private String date;
	
}
