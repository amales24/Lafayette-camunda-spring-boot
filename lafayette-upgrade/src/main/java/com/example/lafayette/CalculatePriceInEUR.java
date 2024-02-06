package com.example.lafayette;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CalculatePriceInEUR implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		double money = (double) execution.getVariable("money");
		double exchangeRate = (double) execution.getVariable("exchangeRate");
				
		
		double money_EUR = Math.round(money*(1/exchangeRate)*100.0)/100.0;
		execution.setVariable("money_EUR", money_EUR);
	}

}
