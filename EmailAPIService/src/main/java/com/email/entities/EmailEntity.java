package com.email.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class EmailEntity {

	
	private String to;
	private String from;
	private String body;
}
