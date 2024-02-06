package com.example.lafayette;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
    private String from;
	
	public void sendEmail(String to, String subject, String body) {
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(from);
			message.setTo(to);
			message.setSubject(subject);
			message.setText(body);
			
			mailSender.send(message);
			
			System.out.println("Message sent successfully!");
		}
		catch (Exception e){
			
			System.out.println("There was a problem with sending the message!");
		}
	}
}
