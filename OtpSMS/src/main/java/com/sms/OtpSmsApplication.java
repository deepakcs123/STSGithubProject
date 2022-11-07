package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sms.services.SmsService;

@SpringBootApplication
public class OtpSmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OtpSmsApplication.class, args);
	}
	
	
}
