package com.sms.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.entities.SMSEntity;
import com.sms.repository.SMSRepo;


@Service
public class SmsService {

	@Autowired
	private SMSRepo smsRep;

	public void sendOtp(String message, String numbers) {
		try {
			
			String apiKey = "IWE9hDSMZq0UJmvt5zRXa4FQHnVkgAB6upby17sd8eLYfCicOxiytaxc6D89b72MPfT4qWYXwNQBgFlk";
			String route = "v3";
			String sender_id = "TXTIND";
			String language = "english";
			String flash = "0";
			
			message=URLEncoder.encode(message, "UTF-8");
			String myUrl="https://www.fast2sms.com/dev/bulkV2?authorization="+apiKey+"&sender_id="+sender_id+"&message="+message+"&route="+route+"&numbers="+numbers;
			
			System.setProperty("javax.net.ssl.trustStore","clientTrustStore.key");
			System.setProperty("javax.net.ssl.trustStorePassword","qwerty");
			
			URL url=new URL(myUrl);
			HttpsURLConnection con=(HttpsURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			System.out.println("Wait.................");
			int responseCode=con.getResponseCode();
			System.out.println("Response code "+responseCode);
				
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}

	public SMSEntity saveOtp(SMSEntity smsData) {
		SMSEntity save = this.smsRep.save(smsData);
		return save;
	}

	public List<SMSEntity> getData() {
		return (List<SMSEntity>) this.smsRep.findAll();
	}
}
