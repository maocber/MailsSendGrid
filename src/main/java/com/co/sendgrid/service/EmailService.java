package com.co.sendgrid.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;

@Service
public class EmailService {

	@Value("${templateId}")
	private String templateId;

	@Autowired
	SendGrid sendGrid;

	public String sendEmail(String email) {
		try {
			Email from = new Email("notadeentrega.producto@gmail.com");
			Email to = new Email("maocber@gmail.com");
			String subject = "Probando SendGrid";
			Content content = new Content("text/html","Esto es un correo de prueba desde senGrid");
			
			Mail mail = new Mail(from, subject, to, content);
		
			Request request = new Request();

			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());

			Response response = sendGrid.api(request);
			if(response!=null) {
				System.out.println("response code from sendgrid: "+response.getStatusCode());
				System.out.println("response code from sendgrid: "+response.getBody());
			}

		} catch (IOException e) {
			e.printStackTrace();
			return "error al enviar el correo!";
		}

		return "Correo enviado. Revisar la carpeta de recibidos (Inbox)!";	
	}

}
