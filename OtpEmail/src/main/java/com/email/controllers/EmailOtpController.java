package com.email.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.entities.EmailOtp;
import com.email.services.EmailOtpService;

@RestController
public class EmailOtpController {

	@Autowired
	private EmailOtpService eos;
	
	@Autowired
	private EmailOtp eotp;
	private String email="dk.v679@gmail.com";

	@PostMapping("/sendotp")
	public EmailOtp sendOtpToMail() throws MessagingException {
		int otp=(int)(Math.random()*9000000)+1000000;
		String subject = "Opl Project Login OTP Verification";
		String toEmail = email;
		int body = otp;
		this.eos.sendOtp(toEmail, subject, body);
		this.eotp.setOtp(otp);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		this.eotp.setMailDate(dateFormat.format(date));
		return this.eos.saveOtp(eotp);
	}
	
	@GetMapping("/getData")
	public List<EmailOtp> getAlData()
	{
		List<EmailOtp> allData = eos.getAllData();
		return allData;
	}
}
