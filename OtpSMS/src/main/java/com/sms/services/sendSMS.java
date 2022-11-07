package com.sms.services;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class sendSMS {
	public void sendSms(int message, long number) {
		String apiKey = "IWE9hDSMZq0UJmvt5zRXa4FQHnVkgAB6upby17sd8eLYfCicOxiytaxc6D89b72MPfT4qWYXwNQBgFlk";
		String route = "otp";
		String api = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&variables_values=" + message
				+ "&route=" + route + "&numbers=" + number;
//			System.out.println(api);
		try {
			URL url = new URL(api);
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");
			int code=con.getResponseCode();
			System.out.println("Response code :"+code);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
