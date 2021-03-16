package com.co.sendgrid.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
public class SendGridConfig {
	
	@Value("${AmwayApiKey}")
	private String amwayApiKey;
	
	@Bean
	public SendGrid getSendGridId() {
		return new SendGrid(amwayApiKey);
	}
}
