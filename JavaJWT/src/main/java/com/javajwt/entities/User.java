package com.javajwt.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "UserData")
@Table(name = "JwtUser")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sid;
	private String userName;
	private String passWord;
	private String address;
	private String name;
	private Long mobile;
	private int age;
}
