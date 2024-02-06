package com.example.lafayette;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendEmail implements JavaDelegate{
	
	@Autowired
	private EmailService emailService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		double money_EUR = (double) execution.getVariable("money_EUR");
		String country = (String) execution.getVariable("country");
		String to = (String) execution.getVariable("emailAddress");
		
		String subject = "Lafayette departure info";
		String body = "Lafayette is going to " + country + " with " + money_EUR + " EUR";
		
		emailService.sendEmail(to, subject, body);
	}

}
