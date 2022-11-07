package com.email.services;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.email.entities.EmailOtp;
import com.email.repository.EmailOtpRepository;

@Service
public class EmailOtpService {

	@Autowired
	private JavaMailSender javaMail;
	@Autowired
	private EmailOtpRepository otprep;

	public String sendOtp(String toEmail, String subject, int body) throws MessagingException {
		/*
		 * SimpleMailMessage sm = new SimpleMailMessage();
		 * sm.setFrom("dkv679.2010@gmail.com"); sm.setTo(toEmail);
		 * sm.setSubject(subject); sm.setText("'<h1>'"+body+"'</h1>'");
		 * javaMail.send(sm);
		 */
		String msg="<div style='border:1px solid black'>"+body+"</div>";
		MimeMessage mailMsg = javaMail.createMimeMessage();
	    MimeMessageHelper mailhelper = new MimeMessageHelper(mailMsg, "utf-8");
	    mailhelper.setFrom("dkv679.2010@gmail.com");
	    mailhelper.setTo(toEmail);
	    mailhelper.setSubject(subject);
	    mailhelper.setText("<div style='border:1px solid black;padding:10px'> Otp for login verification is : <h1>"+body+"</h1></div>",true);
	    javaMail.send(mailMsg);
		return "Otp Sent Successfully";
	}
	
	public EmailOtp saveOtp(EmailOtp otp)
	{
		EmailOtp save = this.otprep.save(otp);
		return save;
	}
	
	public List<EmailOtp> getAllData()
	{
	 List<EmailOtp> getData = (List<EmailOtp>) otprep.findAll();
	 return getData;
		
	}
}
