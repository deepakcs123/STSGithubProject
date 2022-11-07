package com.sms.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.services.SmsService;
import com.sms.services.sendSMS;

@RestController
public class SmsController {

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private sendSMS SendSMS;
	
	@GetMapping("/sendOtp")
	public void sendOtpSMS()
	{
		int otp=(int)(Math.random()*900000)+100000;
		long num=8770811244l;
		//this.smsService.sendOtp("Hello the OTP for Login is "+otp, "8770811244");
		this.SendSMS.sendSms(otp, num);
	}
	
}
